package com.example.spring_starts_here_111;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SpringStartsHere112Application {

	public static void main(String[] args) {SpringApplication.run(SpringStartsHere112Application.class, args);}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
