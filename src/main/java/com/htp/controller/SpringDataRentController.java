package com.htp.controller;

import com.htp.dao.springdata.RentRepository;
import com.htp.domain.RentStatus;
import com.htp.domain.hibernate.HibernateRent;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/springdata/rents")
public class SpringDataRentController {

    private RentRepository rentRepository;

    public SpringDataRentController(RentRepository rentRepository) {
        this.rentRepository = rentRepository;
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
}
