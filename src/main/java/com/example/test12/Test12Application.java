package com.example.test12;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class Test12Application {

    public static void main(String[] args) {
        SpringApplication.run(Test12Application.class, args);
    }

}
