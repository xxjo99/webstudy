package com.webstudy.webstudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class WebstudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebstudyApplication.class, args);
	}

}
