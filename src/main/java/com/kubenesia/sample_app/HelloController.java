package com.kubenesia.sample_app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.net.InetAddress;
import java.net.UnknownHostException;

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

    @GetMapping("/hostname") // New endpoint to display the hostname
    public String showHostname() {
        String hostname = "Unknown Host";
        try {
            // Get the local host address and then its hostname
            InetAddress ip = InetAddress.getLocalHost();
            hostname = ip.getHostName();
        } catch (UnknownHostException e) {
            // Log the exception if hostname cannot be determined
            e.printStackTrace();
            System.err.println("Could not determine hostname: " + e.getMessage());
        }
        return "This app is running on host: " + hostname;
    }
}
