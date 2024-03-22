package com.ecommerce.userService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@Slf4j
public class UserServiceSpringBootApp {
    public static void main(String[] args) {
        log.info("Application started");
        SpringApplication.run(UserServiceSpringBootApp.class);

    }
}
