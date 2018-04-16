package com.example.unittest;

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

    @PostMapping("/hello")
    public ResponseEntity<?> sayHello(@RequestBody @NonNull SamplePojo samplePojo) {

        return new ResponseEntity(sampleService.getName(samplePojo), HttpStatus.OK);
    }

    @GetMapping("/ping")
    public ResponseEntity<?> ping() {

        return new ResponseEntity("pong", HttpStatus.OK);
    }

}
