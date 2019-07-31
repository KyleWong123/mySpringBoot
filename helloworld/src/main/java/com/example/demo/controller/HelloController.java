package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Value("${com.smart.name}")
    private String name;
    @Value("${com.smart.age}")
    private int age;
    @RequestMapping("/")
    public String index() {

        return "Hello Spring Boot"+name+age;
    }
}
