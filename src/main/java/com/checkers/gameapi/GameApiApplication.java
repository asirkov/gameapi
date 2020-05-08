package com.checkers.gameapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.checkers.gameapi.dao")
@SpringBootApplication
public class GameApiApplication {

    public static void main(String[] args) {

        SpringApplication.run(GameApiApplication.class, args);
    }

}
