package com.a14.emart.backendbchr.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestDeployController{
    @GetMapping("/")
    public String index() {
        return "Hello World";
    }
}