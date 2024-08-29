package com.example.symu_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class SymuApiApplication {

    public static void main(String[] args) {
        //deploy
        SpringApplication.run(SymuApiApplication.class, args);
    }
}
