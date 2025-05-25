package com.example.spring_starts_here_101.controller;

import com.example.spring_starts_here_101.models.Country;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CountryController {
    @GetMapping("/all")
    public List<Country> countries() {
        Country c1 =  new Country("Brazil", 200);
        Country c2 = new Country("China", 1000);

        return List.of(c1, c2);
    }

    @GetMapping("/france")
    public Country of(){
        return new Country("France", 67);
    }
}
