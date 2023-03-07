package com.superhero.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class SuperHeroApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuperHeroApplication.class, args);
	}

}
