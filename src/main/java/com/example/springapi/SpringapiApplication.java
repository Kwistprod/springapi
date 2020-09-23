package com.example.springapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringapiApplication.class, args);
    }

}
