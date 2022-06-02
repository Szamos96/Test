package com.example.javatest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EntityScan(basePackages = {"com.example.javatest.entities"} )
@EnableJpaRepositories(basePackages = {"com.example.javatest.repositories"})
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class JavaTestApplication {




    public static void main(String[] args){
        SpringApplication.run(JavaTestApplication.class, args);
    }

}
