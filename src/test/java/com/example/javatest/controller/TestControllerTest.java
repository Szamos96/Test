package com.example.javatest.controller;


import com.example.javatest.common.MapperService;
import com.example.javatest.entities.DTO.TestDTO;
import com.example.javatest.redis.messaging.MessagePublisher;
import com.example.javatest.repositories.ITestEntityRepository;
import com.example.javatest.rest.request.TestRequestBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class TestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ITestEntityRepository repository;

    @MockBean
    private MessagePublisher messagePublisher;

    @Test
    void getTestObject() throws Exception {

        when(repository.findAll()).thenReturn(List.of());
        mockMvc.perform(MockMvcRequestBuilders.get("/test")).andExpect(status().is2xxSuccessful());
        verify(repository,times(1)).findAll();
    }

    @Test
    void postTestObject() throws Exception {

        String body = "{\n" +
                "\"content\": \"abrakadabra\",\n" +
                "\"timestamp\": \"2018-10-09 00:12:12+0100\"\n" +
                "}";

        TestRequestBody testRequestBody = new ObjectMapper().readValue(body, TestRequestBody.class);
        TestDTO testDTO = MapperService.mapTestRequestBodyToTestDTO(testRequestBody);

        doNothing().when(messagePublisher).publish(testDTO);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/test")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().is2xxSuccessful());

        verify(messagePublisher,times(1)).publish(testDTO);
    }

    @Test
    void postTestObjectBlankField() throws Exception {

        String body = "{\n" +
                "\"content\": \"\",\n" +
                "\"timestamp\": \"2018-10-09 00:12:12+0100\"\n" +
                "}";

        doNothing().when(messagePublisher).publish(any());

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/test")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void postTestObjectWrongFormattedTimestamp() throws Exception {

        String body = "{\n" +
                "\"content\": \"abrakadabra\",\n" +
                "\"timestamp\": \"2018-10-0900:12:12+0100389\"\n" +
                "}";

        doNothing().when(messagePublisher).publish(any());

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/test")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().is4xxClientError());
    }


}
