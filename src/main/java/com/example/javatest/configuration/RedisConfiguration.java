package com.example.javatest.configuration;

import com.example.javatest.entities.DTO.TestDTO;
import com.example.javatest.redis.messaging.MessagePublisher;
import com.example.javatest.redis.messaging.MessageSubscriber;
import com.example.javatest.redis.serializers.TestDTOSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import java.util.Collections;


@Configuration
@ComponentScan("com.example.javatest")
@EnableRedisRepositories(basePackages = "com.example.javatest.repositories")
@PropertySource("classpath:application.properties")
public class RedisConfiguration {

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean
    public RedisTemplate<String, TestDTO> redisTemplate() {
        final RedisTemplate<String, TestDTO> template = new RedisTemplate<String, TestDTO>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setValueSerializer(new TestDTOSerializer());
        return template;
    }

    @Bean
    MessageSubscriber messageListener() {
        return new MessageSubscriber();
    }

    @Bean
    RedisMessageListenerContainer redisContainer() {
        final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(jedisConnectionFactory());
        container.addMessageListener(messageListener(), Collections.singleton(topic()));
        return container;
    }

    @Bean
    MessagePublisher redisPublisher() {
        return new MessagePublisher(redisTemplate(), topic());
    }

    @Bean
    ChannelTopic topic() {
        return new ChannelTopic("pubsub:queue");
    }
}

