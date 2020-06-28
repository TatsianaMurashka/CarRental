package com.htp.controller;

import com.htp.controller.request.UserCreateRequest;
import com.htp.domain.Location;
import com.htp.domain.User;
import com.htp.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger log = Logger.getLogger(UserController.class);

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable("id")Long userId) {
        return userService.findOne(userId);
    }

    @PostMapping
    public User create(@RequestBody UserCreateRequest createRequest) {
        User user = new User();
        user.setFirstName(createRequest.getFirstName());
        user.setLastName(createRequest.getLastName());
        user.setPhoneNumber(createRequest.getPhoneNumber());
        user.setPassportData(createRequest.getPassportData());
        user.setLogin(createRequest.getLogin());
        user.setPassword(createRequest.getPassword());
        user.setCreated(new Timestamp(new Date().getTime()));
        user.setChanged(new Timestamp(new Date().getTime()));
//        user.setLocationId();

        return userService.save(user);
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