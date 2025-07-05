package com.kubenesia.sample_app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // This annotation marks the class as a REST controller
public class HelloController {

    @GetMapping("/") // This annotation maps HTTP GET requests to the root URL ("/") to this method
    public String hello() {
        return "Hello from Spring Boot Web Application!";
    }

    @GetMapping("/greeting") // Another endpoint for demonstration
    public String greeting() {
        return "Greetings from KubenesiApp!";
    }
}
