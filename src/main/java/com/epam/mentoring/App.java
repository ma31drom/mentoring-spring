package com.epam.mentoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

	{
		System.setProperty("spring.profiles.active", "DEV");
	}

	public static void main(final String[] args) {
		SpringApplication.run(App.class, args);

	}

}
