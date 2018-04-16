package com.example.unittest.service;

import com.example.unittest.exception.BadRequestException;
import com.example.unittest.pojo.SamplePojo;
import com.example.unittest.service.SampleServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class SampleServiceTest {

    @InjectMocks
    private SampleServiceImpl sampleService;

    @Test(expected = BadRequestException.class)
    public void testGetNameWithEmptyName() throws Exception {

        String result = sampleService.getName(SamplePojo.builder()
                .name("")
                .build());

    }

    @Test(expected = BadRequestException.class)
    public void testGetNameWithNullName() throws Exception {

        String result = sampleService.getName(SamplePojo.builder()
                .name(null)
                .build());

    }

    @Test
    public void testGetNameWithCorrectName() throws Exception {

        final String expectedResponse = "Abc";

        String result = sampleService.getName(SamplePojo.builder()
                .name("Abc")
                .build());

        assertEquals(expectedResponse, result);

    }

}
