package com.example.unittest;

import com.example.unittest.exception.BadRequestException;
import org.springframework.stereotype.Component;

@Component
public class SampleServiceImpl implements SampleService {


    @Override
    public String getName(SamplePojo samplePojo) {

        if (samplePojo.getName() == null || samplePojo.getName().trim().length() == 0) {
            throw new BadRequestException("Name is empty or null");
        }

        return samplePojo.getName();
    }
}
