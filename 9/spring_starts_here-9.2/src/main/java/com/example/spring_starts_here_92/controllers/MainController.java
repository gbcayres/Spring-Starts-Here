package com.example.spring_starts_here_92.controllers;

import com.example.spring_starts_here_92.services.LoggedUserManagementService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    private final LoggedUserManagementService loggedUserManagementService;

    public MainController(LoggedUserManagementService loggedUserManagementService) {
        this.loggedUserManagementService = loggedUserManagementService;
    }

    @GetMapping("/main")
    public String home(
            final Model model,
            @RequestParam(required = false) String logout
    ) {
        if (logout != null) loggedUserManagementService.setUsername(null);

        String username = loggedUserManagementService.getUsername();

        if (username == null) return "redirect:/";

        model.addAttribute("username", username);
        return "main.html";
    }
}
