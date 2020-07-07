package com.htp.controller;

import com.htp.controller.request.UserUpdateRequest;
import com.htp.domain.User;
import com.htp.service.UserService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @ApiOperation(value = "Get all users")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading users"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Finding user by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading user"),
            @ApiResponse(code = 500, message = "Server error, something wrong"),
            @ApiResponse(code = 502, message = "Wrong user id")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "id", value = "User database identifier", example = "1", required = true, dataType = "long", paramType = "path")
    })
    @GetMapping("/{id}")
    public User findById(@PathVariable("id")Long userId) {
        return userService.findOne(userId);
    }

    @ApiOperation(value = "Search users by login")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading user"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "query", value = "Search query - free text", example = "tatsianam", required = true, dataType = "string", paramType = "query")
    })
    @GetMapping("/search")
    public List<User> searchUser(@RequestParam("query") String query) {
        return userService.search(query);
    }

    @ApiOperation(value = "Update user")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Successful update user"),
            @ApiResponse(code = 422, message = "Failed user update properties validation"),
            @ApiResponse(code = 500, message = "Server error, something wrong"),
            @ApiResponse(code = 502, message = "Wrong user id")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @PutMapping
    public User update(@Valid @RequestBody UserUpdateRequest updateRequest) {
        User userToUpdate = userService.findOne(updateRequest.getId());
        userToUpdate.setFirstName(updateRequest.getFirstName());
        userToUpdate.setLastName(updateRequest.getLastName());
        userToUpdate.setPhoneNumber(updateRequest.getPhoneNumber());
        userToUpdate.setLogin(updateRequest.getLogin());
        userToUpdate.setChanged(new Timestamp(new Date().getTime()));
        return userService.update(userToUpdate);
    }

    @ApiOperation(value = "Mark user as deleted")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Successful delete user"),
            @ApiResponse(code = 500, message = "Server error, something wrong"),
            @ApiResponse(code = 502, message = "Wrong user id")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "id", value = "User database identifier", example = "1", required = true, dataType = "long", paramType = "path")
    })
    @PutMapping("/{id}")
    public User deleteUser(@PathVariable("id") Long userId) {
        User userToDelete = userService.findOne(userId);
        userService.delete(userToDelete.getId());
        return userService.findOne(userId);
    }


//    @GetMapping
//    public String findAll(ModelMap modelMap) {
//        modelMap.addAttribute("users", userService.findAll());
//        return "user/users";
//    }
//
//    @GetMapping("/find/{id}")
//    public String findById(@PathVariable("id") Long userId, ModelMap modelMap) {
//        modelMap.addAttribute("user", userService.findOne(userId));
//        return "user/user";
//    }
//
//    @GetMapping("/save/{first_name}")
//    public String saveUser(@PathVariable("first_name") String firstName, ModelMap modelMap) {
//        User user = new User(firstName, "", "", "", "", "", new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime()), 4L);
//        modelMap.addAttribute("user", userService.save(user));
//        return "user/users";
//    }
//
//    @GetMapping("/update")
//    public String updateUser(@RequestParam("id") Long userId, @RequestParam("name") String firstName, ModelMap modelMap) {
//        User user = userService.findOne(userId);
//        user.setFirstName(firstName);
//        userService.update(user);
//        return "user/users";
//    }
//
//    @GetMapping("/delete/{id}")
//    public String deleteUser(@PathVariable("id") Long userId, ModelMap modelMap) {
//        modelMap.addAttribute("user", userService.delete(userId));
//        return "user/users";
//    }
}