package com.htp.controller;

import com.htp.domain.User;
import com.htp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.Date;

@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String findAll(ModelMap modelMap) {
        modelMap.addAttribute("users", userService.findAll());
        return "user/users";
    }

    @GetMapping("/find/{id}")
    public String findById(@PathVariable("id") Long userId, ModelMap modelMap) {
        modelMap.addAttribute("user", userService.findOne(userId));
        return "user/user";
    }

    @GetMapping("/save/{first_name}")
    public String saveUser(@PathVariable("first_name") String firstName, ModelMap modelMap) {
        User user = new User(firstName, "", "", "", "", "", new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime()), 4L);
        modelMap.addAttribute("user", userService.save(user));
        return "user/users";
    }

    @GetMapping("/update")
    public String updateUser(@RequestParam("id") Long userId, @RequestParam("name") String firstName, ModelMap modelMap) {
        User user = userService.findOne(userId);
        user.setFirstName(firstName);
        userService.update(user);
        return "user/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long userId, ModelMap modelMap) {
        modelMap.addAttribute("user", userService.delete(userId));
        return "user/users";
    }
}
