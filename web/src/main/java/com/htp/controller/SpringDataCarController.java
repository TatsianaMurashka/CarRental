package com.htp.controller;

import com.htp.controller.request.CarCreateRequest;
import com.htp.controller.request.CarUpdateRequest;
import com.htp.dao.springdata.CarRepository;
import com.htp.domain.CarAvailability;
import com.htp.domain.hibernate.HibernateCar;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/springdata/cars")
public class SpringDataCarController {

    private CarRepository carRepository;

    private ConversionService conversionService;

    public SpringDataCarController(CarRepository carRepository, ConversionService conversionService) {
        this.carRepository = carRepository;
        this.conversionService = conversionService;
    }

    @ApiOperation(value = "Finding car by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading car"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Car database id", example = "1", required = true, dataType = "long", paramType = "path"),
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @GetMapping("/{id}")
    public ResponseEntity<HibernateCar> getCarById(@PathVariable Long id) {
        return new ResponseEntity<>(carRepository.findById(id).get(), HttpStatus.OK);
    }

    @ApiOperation(value = "Finding all cars")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading cars"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @GetMapping
    public ResponseEntity<List<HibernateCar>> findAll() {
        return new ResponseEntity<>(carRepository.findAllAvailableCars(), HttpStatus.OK);
    }

    @ApiOperation(value = "Create car")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Successful creation car"),
            @ApiResponse(code = 422, message = "Failed user creation properties validation"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @PostMapping
    public HibernateCar create(@Valid @RequestBody CarCreateRequest createRequest) {
        HibernateCar car = conversionService.convert(createRequest, HibernateCar.class);

        return carRepository.save(car);
    }

    @ApiOperation(value = "Update car")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Successful creation user"),
            @ApiResponse(code = 422, message = "Failed user creation properties validation"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    @PutMapping
    public ResponseEntity<HibernateCar> update(@Valid @RequestBody CarUpdateRequest updateRequest) {
        HibernateCar car = conversionService.convert(updateRequest, HibernateCar.class);

        carRepository.updateCar(car.getId(), car.getPricePerDay(), car.getRegistrationNumber(), car.getOffice(), car.getModel());
        car = carRepository.findById(car.getId()).get();
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public void updateCarStatus(Long id, CarAvailability status) {
        carRepository.updateCarAvailability(id, status);
    }

    public List<HibernateCar> getCarsWithRentCountInternal(Long rentCount) {
        return carRepository.findAllCarsWithRentCount(rentCount);
    }

    public HibernateCar getCarByIdInternal(Long id) {
        return carRepository.findById(id).get();
    }

    public List<HibernateCar> findAllInternal() {
        return carRepository.findAll();
    }
}
