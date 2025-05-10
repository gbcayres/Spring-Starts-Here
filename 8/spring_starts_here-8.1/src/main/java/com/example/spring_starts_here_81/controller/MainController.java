package com.example.spring_starts_here_81.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @RequestMapping("/home")
    public String home(Model page) {
        page.addAttribute("username", "lv.tolstoi");
        page.addAttribute("color", "red");
        return "home.html";
    }
}
