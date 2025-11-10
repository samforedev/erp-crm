package com.sam.insuranceservice;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InsuranceServiceApplication {

    public static void main(String[] args) {
        LoadConfiguration();
        SpringApplication.run(InsuranceServiceApplication.class, args);
    }

    public static void LoadConfiguration() {
        Dotenv dotenv = Dotenv.load();
        dotenv.entries().forEach(x -> System.setProperty(x.getKey(), x.getValue()));
    }

}
