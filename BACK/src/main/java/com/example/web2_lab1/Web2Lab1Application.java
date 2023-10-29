package com.example.web2_lab1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class Web2Lab1Application {

	public static void main(String[] args) {
		SpringApplication.run(Web2Lab1Application.class, args);
	}

}
