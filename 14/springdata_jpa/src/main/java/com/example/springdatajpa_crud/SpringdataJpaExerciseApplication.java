package com.example.springdatajpa_crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringdataJpaExerciseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringdataJpaExerciseApplication.class, args);
	}
}
