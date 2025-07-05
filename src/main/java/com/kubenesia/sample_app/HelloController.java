package com.kubenesia.sample_app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class HelloController {

    // NEW: Inject custom message from application.properties or environment variable
    @Value("${app.message:Default message if not found}")
    private String appMessage;

    // NEW: Simple in-memory request counter
    private final AtomicLong requestCount = new AtomicLong();

    @GetMapping("/")
    public String hello() {
        // Increment the counter for each request to the root
        long currentCount = requestCount.incrementAndGet();
        return "Hello from Spring Boot Web Application! (Requests served by this instance: " + currentCount + ")";
    }

    @GetMapping("/greeting")
    public String greeting() {
        return "Greetings from KubenesiApp!";
    }

    @GetMapping("/hostname")
    public String showHostname() {
        String hostname = "Unknown Host";
        try {
            InetAddress ip = InetAddress.getLocalHost();
            hostname = ip.getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.err.println("Could not determine hostname: " + e.getMessage());
        }
        return "This app is running on host: " + hostname;
    }

    // NEW: Endpoint to display the custom message
    @GetMapping("/message")
    public String showMessage() {
        return "Configured Message: " + appMessage;
    }

    // NEW: Endpoint to display the current request count for this instance
    @GetMapping("/count")
    public String showCount() {
        return "This instance has served " + requestCount.get() + " requests.";
    }
}
