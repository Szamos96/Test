package com.example.javatest.entities;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.ZonedDateTime;

@Entity
@Data
@RedisHash("Test")
public class TestEntity {

    @Id
    private String id;

    private String content;

    private ZonedDateTime timestamp;

    private int longestPalindromeSize;

}
