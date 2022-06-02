package com.example.javatest.redis.messaging;

import com.example.javatest.entities.DTO.TestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Service
@Primary
public class MessagePublisher implements IMessagePublisher{

    @Autowired
    private RedisTemplate<String, TestDTO> redisTemplate;
    @Autowired
    private ChannelTopic topic;

    public MessagePublisher(){

    }
    public MessagePublisher(RedisTemplate<String, TestDTO> redisTemplate, ChannelTopic topic) {
        this.redisTemplate = redisTemplate;
        this.topic = topic;
    }

    @Override
    public void publish(Object message) {
        redisTemplate.convertAndSend(topic.getTopic(), message);
    }
}
