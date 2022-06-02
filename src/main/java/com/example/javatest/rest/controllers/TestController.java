package com.example.javatest.rest.controllers;


import com.example.javatest.common.MapperService;
import com.example.javatest.redis.messaging.MessagePublisher;
import com.example.javatest.repositories.ITestEntityRepository;
import com.example.javatest.rest.request.TestRequestBody;
import com.example.javatest.rest.response.TestResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/test")
@RestController
public class TestController {

    @Autowired
    private ITestEntityRepository repository;
    @Autowired
    private MessagePublisher messagePublisher;

    @PostMapping
    private void saveTestEntity(@Valid @RequestBody TestRequestBody body) {
        messagePublisher.publish(MapperService.mapTestRequestBodyToTestDTO(body));
    }

    @GetMapping
    private List<TestResponseBody> getAllTestEntity() {

        return repository.findAll().stream()
                .map(MapperService::mapTestEntityToTestResponseBody)
                .collect(Collectors.toList());
    }

}
