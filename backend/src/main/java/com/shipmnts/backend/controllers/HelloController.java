package com.shipmnts.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shipmnts.backend.entities.Hello;
import com.shipmnts.backend.repositories.HelloRepository;

@RestController
public class HelloController {

    @Autowired
    private HelloRepository helloRepository;

    @GetMapping("/hello")
    public List<Hello> sayHello() {
        return helloRepository.findAll();
    }
}
