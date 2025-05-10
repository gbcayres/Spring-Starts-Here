package com.example.spring_starts_here_72.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MainController {
    @RequestMapping("/home")
    public String home() {
        return "home.html";
    }
}
