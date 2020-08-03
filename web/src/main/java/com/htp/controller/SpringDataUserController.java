package com.htp.controller;

import com.htp.controller.request.UserCreateRequest;
import com.htp.controller.request.UserUpdateRequest;
import com.htp.dao.springdata.UserRepository;
import com.htp.domain.hibernate.HibernateUser;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/springdata/users")
@Transactional
public class SpringDataUserController {

    private UserRepository userRepository;

    private ConversionService conversionService;

    public SpringDataUserController(UserRepository userRepository, ConversionService conversionService) {
        this.userRepository = userRepository;
        this.conversionService = conversionService;
    }

    @ApiOperation(value = "Finding all users")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading users"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @GetMapping
    public ResponseEntity<List<HibernateUser>> findAll() {
        return new ResponseEntity<>(userRepository.findAllActiveUsers(), HttpStatus.OK);
    }

    @ApiOperation(value = "Search with pagination")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading users"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "Page number", example = "0", defaultValue = "0", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "Items per page", example = "3", defaultValue = "3", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "sort", value = "Field to sort", example = "id", defaultValue = "id", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @GetMapping("/searchPage")
    public ResponseEntity<Page<HibernateUser>> searchWithPagination(@ApiIgnore Pageable pageable) {
        Page<HibernateUser> usersPage = userRepository.findAll(pageable);
        return new ResponseEntity<>(usersPage, HttpStatus.OK);
    }

    @ApiOperation(value = "Finding user by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading user"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "User database id", required = true, dataType = "long", paramType = "path"),
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @GetMapping("/{id}")
    public ResponseEntity<HibernateUser> getUserById(@PathVariable Long id) {
        HibernateUser user = userRepository.findById(id).get();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @ApiOperation(value = "Search user by login")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading user"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @GetMapping("/search")
    public ResponseEntity<List<HibernateUser>> searchUsersByLogin(@RequestParam("login") String login) {
        return new ResponseEntity<>(userRepository.findUsersByLogin(login), HttpStatus.OK);
    }

    @ApiOperation(value = "Create user")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Successful creation user"),
            @ApiResponse(code = 422, message = "Failed user creation properties validation"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @PostMapping
    public HibernateUser create(@Valid @RequestBody UserCreateRequest createRequest) {
        HibernateUser user = conversionService.convert(createRequest, HibernateUser.class);

        return userRepository.save(user);
    }

    @ApiOperation(value = "Update user")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Successful creation user"),
            @ApiResponse(code = 422, message = "Failed user creation properties validation"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @PutMapping
    public ResponseEntity<HibernateUser> update(@Valid @RequestBody UserUpdateRequest updateRequest) {
        HibernateUser user = conversionService.convert(updateRequest, HibernateUser.class);

        userRepository.updateUser(user.getId(), user.getFirstName(), user.getLastName(), user.getPhoneNumber(), user.getLogin(), user.getPassword());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete user")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Successful delete user"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @PutMapping("/{id}")
    public ResponseEntity<HibernateUser> deleteUser(@PathVariable Long id) {
        userRepository.deleteUser(id);
        HibernateUser user = userRepository.findById(id).get();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
