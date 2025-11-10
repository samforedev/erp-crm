package com.sam.authservice;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthServiceApplication {

    public static void main(String[] args) {
        LoadConfiguration();
        SpringApplication.run(AuthServiceApplication.class, args);
    }

    public static void LoadConfiguration() {
        Dotenv dotenv = Dotenv.load();
        dotenv.entries().forEach(x -> System.setProperty(x.getKey(), x.getValue()));
    }

}
