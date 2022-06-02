package com.example.javatest.common;

import com.example.javatest.entities.DTO.TestDTO;
import com.example.javatest.entities.TestEntity;
import com.example.javatest.rest.request.TestRequestBody;
import com.example.javatest.rest.response.TestResponseBody;

public class MapperService {


    public static TestEntity mapTestDTOToTestEntity(TestDTO testDTO) {

        TestEntity testEntity = new TestEntity();
        testEntity.setContent(testDTO.getContent());
        testEntity.setTimestamp(testDTO.getTimestamp());
        return testEntity;
    }

    public static TestResponseBody mapTestEntityToTestResponseBody(TestEntity testEntity) {

        return TestResponseBody.builder()
                .content(testEntity.getContent())
                .timestamp(testEntity.getTimestamp())
                .longestPalindromeSize(testEntity.getLongestPalindromeSize())
                .build();
    }

    public static TestEntity mapTestRequestBodyToTestEntity(TestRequestBody testRequestBody) {

        TestEntity testEntity = new TestEntity();
        testEntity.setContent(testRequestBody.getContent());
        testEntity.setTimestamp(testRequestBody.getTimestamp());
        return testEntity;
    }

    public static TestDTO mapTestRequestBodyToTestDTO(TestRequestBody testRequestBody) {

        return TestDTO.builder().content(testRequestBody.getContent()).timestamp(testRequestBody.getTimestamp()).build();
    }

}
