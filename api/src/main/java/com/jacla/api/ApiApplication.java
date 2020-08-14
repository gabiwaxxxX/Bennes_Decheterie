package com.jacla.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiApplication {

    public static void main(String[] args) {
        UI app = new UI();
        SpringApplication.run(ApiApplication.class, args);
    }

}
