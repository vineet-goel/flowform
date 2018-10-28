package com.example.webflow.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestApi {

    @RequestMapping("/api/")
    public ResponseEntity<String> hello() {
        return new ResponseEntity<>("hey", HttpStatus.OK);
    }
}
