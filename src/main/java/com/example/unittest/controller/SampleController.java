package com.example.unittest.controller;

import com.example.unittest.pojo.SamplePojo;
import com.example.unittest.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sample")
public class SampleController {

    @Autowired
    private SampleService sampleService;

    @GetMapping("/ping")
    public ResponseEntity<?> ping() {

        return new ResponseEntity("pong", HttpStatus.OK);
    }

    @PostMapping("/hello")
    public ResponseEntity<?> sayHello(@RequestBody @NonNull SamplePojo samplePojo) {

        return new ResponseEntity(sampleService.getName(samplePojo), HttpStatus.OK);
    }

}
