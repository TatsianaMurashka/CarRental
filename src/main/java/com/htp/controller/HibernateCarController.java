package com.htp.controller;

import com.htp.controller.request.CarCreateRequest;
import com.htp.dao.HibernateCarDao;
import com.htp.domain.hibernate.HibernateCar;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/hibernate/cars")
public class HibernateCarController {

    private HibernateCarDao hibernateCarDao;

    public HibernateCarController(HibernateCarDao hibernateCarDao) {
        this.hibernateCarDao = hibernateCarDao;
    }

    @ApiOperation(value = "Finding all cars")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading cars"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @GetMapping
    public ResponseEntity<List<HibernateCar>> findAll() {
        return new ResponseEntity<>(hibernateCarDao.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Finding car by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading cars"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Car database id", example = "1", required = true, dataType = "long", paramType = "path")
    })
    @GetMapping("/{id}")
    public ResponseEntity<HibernateCar> getCarById(@PathVariable Long id) {
        return new ResponseEntity<>(hibernateCarDao.findOne(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Create car")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Successful creation car"),
            @ApiResponse(code = 422, message = "Failed car creation properties validation"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @PostMapping
    public HibernateCar create(@Valid @RequestBody CarCreateRequest createRequest) {

        HibernateCar car = new HibernateCar();
        car.setRegistrationNumber(createRequest.getRegistrationNumber());
        car.setModelId(createRequest.getModelId());
        car.setPricePerDay(createRequest.getPricePerDay());
        car.setOfficeId(createRequest.getOfficeId());

        return hibernateCarDao.save(car);
    }
}
