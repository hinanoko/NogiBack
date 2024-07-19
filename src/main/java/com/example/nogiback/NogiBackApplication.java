package com.example.nogiback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NogiBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(NogiBackApplication.class, args);
    }

}
