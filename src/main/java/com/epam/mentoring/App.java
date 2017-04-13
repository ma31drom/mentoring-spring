package com.epam.mentoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

	public static void main(final String[] args) {
		System.setProperty("spring.profiles.active", "DEV");
		SpringApplication.run(App.class, args);
	}

}
