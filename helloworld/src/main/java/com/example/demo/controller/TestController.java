package com.example.demo.controller;

import com.example.demo.entity.TestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableConfigurationProperties({TestEntity.class})
public class TestController {
    @Autowired
    private TestEntity testEntity;

    @GetMapping("/test")
    public String testInfo() {
        return testEntity.getNumber() + ":" + testEntity.getUuid().replaceAll("\\-", "") + ":" + testEntity.getMax();
    }
}
