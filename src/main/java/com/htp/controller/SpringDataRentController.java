package com.htp.controller;

import com.htp.controller.request.RentCreateRequest;
import com.htp.dao.springdata.CarRepository;
import com.htp.dao.springdata.RentRepository;
import com.htp.domain.RentStatus;
import com.htp.domain.hibernate.HibernateCar;
import com.htp.domain.hibernate.HibernateRent;
import io.swagger.annotations.*;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Random;

import static java.time.temporal.ChronoUnit.DAYS;

@RestController
@RequestMapping("/springdata/rents")
public class SpringDataRentController {

    private RentRepository rentRepository;

    private ConversionService conversionService;

    private CarRepository carRepository;

    private int createRentErrorsCount = 0;
    private final int CAR_CREATE_ERROR_LIMIT = 2;

    public SpringDataRentController(RentRepository rentRepository, ConversionService conversionService, CarRepository carRepository) {
        this.rentRepository = rentRepository;
        this.conversionService = conversionService;
        this.carRepository = carRepository;
    }

    @ApiOperation(value = "Finding rent by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading rent"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Rent database id", example = "1", required = true, dataType = "long", paramType = "path")
    })
    @GetMapping("/{id}")
    public ResponseEntity<HibernateRent> getRentById(@PathVariable Long id) {
        return new ResponseEntity<>(rentRepository.findById(id).get(), HttpStatus.OK);
    }


    @ApiOperation(value = "Finding all rents")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading rents"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @GetMapping
    public ResponseEntity<List<HibernateRent>> findAll() {
        return new ResponseEntity<>(rentRepository.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Create rent")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Successful creation rent"),
            @ApiResponse(code = 422, message = "Failed user creation properties validation"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @PostMapping
    public HibernateRent create(@Valid @RequestBody RentCreateRequest createRequest) {

        HibernateRent rent = conversionService.convert(createRequest, HibernateRent.class);

        List<HibernateCar> availableCars = carRepository.findAllAvailableCars();
        if (availableCars.size() != 0)
        {
            return createNewRent(rent, availableCars.get(0));
        }
        createRentErrorsCount++;
        if (createRentErrorsCount > CAR_CREATE_ERROR_LIMIT)
        {
            return handleNotEnoughCars(createRequest);
        }
        HibernateRent invalidRent = new HibernateRent();
        invalidRent.setId(0L);
        return invalidRent;
    }

    private HibernateRent createNewRent(HibernateRent rent, HibernateCar car)
    {
        rent.setCarId(car.getId());
        createRentErrorsCount = 0;
        rent.setRentPrice(DAYS.between(rent.getRentStartDate(), rent.getRentEndDate()) * car.getPricePerDay());
        return rentRepository.save(rent);
    }

    private HibernateRent handleNotEnoughCars(RentCreateRequest createRequest)
    {
        createNewCar();
        createRentErrorsCount = 0;
        return create(createRequest);
    }

    private void createNewCar()
    {
        HibernateCar carToAdd = new HibernateCar();
        carToAdd.setRegistrationNumber(String.valueOf ((new Random()).nextLong()));
        carToAdd.setModel(1l);
        carToAdd.setPricePerDay(5.);
        carToAdd.setOffice(1l);
        carRepository.save(carToAdd);
    }

    public List<HibernateRent> findAllInternal() {
        return rentRepository.findAll();
    }

    public HibernateRent getRentByIdInternal(Long id) {
        return rentRepository.findById(id).get();
    }

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public void updateRentStatusInternal(Long id, RentStatus status) {
        HibernateRent rent = rentRepository.findById(id).get();
        if (rent != null) {
            rentRepository.updateRent(rent.getId(), status);
        }
    }

    public List<HibernateRent> findRentsWithStatusInternal(RentStatus rentStatus) {
        return rentRepository.findRentsWithStatus(rentStatus);
    }
}
