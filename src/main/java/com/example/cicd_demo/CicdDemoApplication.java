package com.example.cicd_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController // Add this annotation to make it a web controller
public class CicdDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CicdDemoApplication.class, args);
	}

	// Add this method to handle web requests
	@GetMapping("/")
	public String hello() {
		return "Hello from our CI/CD Pipeline! This project is live.";
	}
}