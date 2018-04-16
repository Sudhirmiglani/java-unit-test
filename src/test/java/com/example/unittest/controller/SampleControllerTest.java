package com.example.unittest.controller;

import com.example.unittest.controller.SampleController;
import com.example.unittest.exception.BadRequestException;
import com.example.unittest.service.SampleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.NestedServletException;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(value = SampleController.class, secure = false)
public class SampleControllerTest {

    @MockBean
    private SampleService sampleService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testPing() throws Exception {

        final String expectedResponse = "pong";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/sample/ping")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());

        assertEquals(expectedResponse, result.getResponse()
                .getContentAsString());
    }

    @Test
    public void testSayHello() throws Exception {

        final String expectedName = "Name1";

        final String exampleSamplePojo = "{\"name\":\"Name1\"}";

        Mockito.when(
                sampleService.getName(Mockito.any())).thenReturn(expectedName);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/sample/hello")
                .accept(MediaType.APPLICATION_JSON)
                .content(exampleSamplePojo)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());

        assertEquals(expectedName, result.getResponse()
                .getContentAsString());
    }

    @Test(expected = NestedServletException.class)
    public void testSayHelloWithEmptyName() throws Exception {

        final String exampleSamplePojo = "{\"name\":\"\"}";

        Mockito.when(
                sampleService.getName(Mockito.any())).thenThrow(BadRequestException.class);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/sample/hello")
                .accept(MediaType.APPLICATION_JSON)
                .content(exampleSamplePojo)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    }

    @Test(expected = NestedServletException.class)
    public void testSayHelloWithNullName() throws Exception {

        final String exampleSamplePojo = "{}";

        Mockito.when(
                sampleService.getName(Mockito.any())).thenThrow(BadRequestException.class);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/sample/hello")
                .accept(MediaType.APPLICATION_JSON)
                .content(exampleSamplePojo)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    }


}
