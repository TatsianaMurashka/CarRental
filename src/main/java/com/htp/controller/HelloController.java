package com.htp.controller;

import com.htp.aspect.LogAspect;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/hello")
    public String hello(ModelMap modelMap) {
        modelMap.addAttribute("stat", LogAspect.showStatistics());

        return "hello";
    }

}
