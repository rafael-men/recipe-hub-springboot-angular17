package com.rafa.recipehub.controller;


import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {

    @GetMapping
    public String homeController() {
        return "Welcome back to code with zosh";
    }

    //@PostMapping
    //@PutMapping
    //@DeleteMapping
}
