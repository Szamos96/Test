package com.example.javatest.redis.messaging;

public interface IMessagePublisher {

    void publish(final Object message);
}
