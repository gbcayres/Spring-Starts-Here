package org.example.main;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class Parrot {
    private String name;

    @PostConstruct
    public void init() {
        this.name = "kiki";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
