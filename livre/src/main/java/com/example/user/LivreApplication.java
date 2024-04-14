package com.example.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration;


@SpringBootApplication()
public class LivreApplication {

    public static void main(String[] args) {
        SpringApplication.run(LivreApplication.class, args);
    }

}
