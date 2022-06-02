package com.example.javatest.redis.serializers;

import com.example.javatest.common.ErrorMessages;
import com.example.javatest.entities.DTO.TestDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.io.IOException;

public class TestDTOSerializer implements RedisSerializer<TestDTO> {

    @Override
    public byte[] serialize(TestDTO testDTO) throws SerializationException {

        try {
            return new ObjectMapper().writeValueAsBytes(testDTO);
        } catch (JsonProcessingException e) {
            throw new SerializationException(ErrorMessages.SERIALIZE_EXCEPTION, e);
        }
    }

    @Override
    public TestDTO deserialize(byte[] bytes) throws SerializationException {
        try {
            return new ObjectMapper().readValue(bytes, TestDTO.class);
        } catch (IOException e) {
            throw new SerializationException(ErrorMessages.DESERIALIZE_EXCEPTION, e);
        }
    }
}
