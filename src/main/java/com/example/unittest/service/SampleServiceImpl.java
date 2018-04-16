package com.example.unittest.service;

import com.example.unittest.exception.BadRequestException;
import com.example.unittest.pojo.SamplePojo;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class SampleServiceImpl implements SampleService {

    @Override
    public String getName(@NonNull final SamplePojo samplePojo) {

        if (samplePojo.getName() == null || samplePojo.getName().trim().length() == 0) {
            throw new BadRequestException("Name is empty or null");
        }

        return samplePojo.getName();
    }
}
