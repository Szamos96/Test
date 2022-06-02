package com.example.javatest.redis.messaging;

import com.example.javatest.common.LongestPalindromeCalculator;
import com.example.javatest.common.MapperService;
import com.example.javatest.entities.DTO.TestDTO;
import com.example.javatest.entities.TestEntity;
import com.example.javatest.repositories.ITestEntityRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MessageSubscriber implements MessageListener {

    @Autowired
    private ITestEntityRepository repository;

    private ObjectMapper mapper = new ObjectMapper();

    public void onMessage(final Message message, final byte[] pattern) {

        try {
            saveTestEntityAndPopulateWithLongestPalindromeSize(mapper.readValue(message.getBody(), TestDTO.class));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void saveTestEntityAndPopulateWithLongestPalindromeSize(TestDTO testDTO){

        TestEntity entity = MapperService.mapTestDTOToTestEntity(testDTO);
        entity.setLongestPalindromeSize(LongestPalindromeCalculator.calculateLongestPalindrome(entity.getContent()));
        repository.save(entity);
    }
}
