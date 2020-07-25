package com.htp.controller;

import com.htp.controller.request.RentCreateRequest;
import com.htp.dao.HibernateRentDao;
import com.htp.domain.hibernate.HibernateRent;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/hibernate/rent")
public class HibernateRentController {

    private HibernateRentDao hibernateRentDao;

    public HibernateRentController(HibernateRentDao hibernateRentDao) {
        this.hibernateRentDao = hibernateRentDao;
    }

    @ApiOperation(value = "Finding all rents")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading rents"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @GetMapping
    public ResponseEntity<List<HibernateRent>> findAll() {
        return new ResponseEntity<>(hibernateRentDao.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Finding rent by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading rents"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Rent database id", example = "1", required = true, dataType = "long", paramType = "path")
    })
    @GetMapping("/{id}")
    public ResponseEntity<HibernateRent> getRentById(@PathVariable Long id) {
        return new ResponseEntity<>(hibernateRentDao.findOne(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Add rent")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Successful adding rent"),
            @ApiResponse(code = 422, message = "Failed rent creation properties validation"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @PostMapping
    public HibernateRent create(@Valid @RequestBody RentCreateRequest createRequest) {

        HibernateRent rent = new HibernateRent();
        rent.setCarId(createRequest.getCarId());
        rent.setRentStartDate(createRequest.getRentStartDate());
        rent.setRentEndDate(createRequest.getRentEndDate());
        rent.setCreated(new Timestamp(new Date().getTime()));
        rent.setChanged(new Timestamp(new Date().getTime()));
        rent.setRentPrice(createRequest.getRentPrice());

        return hibernateRentDao.save(rent);
    }
}
