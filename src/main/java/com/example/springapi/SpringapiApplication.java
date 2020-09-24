package com.example.springapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableJpaAuditing
@Controller
@RequestMapping("/api")
public class SpringapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringapiApplication.class, args);
    }

}
