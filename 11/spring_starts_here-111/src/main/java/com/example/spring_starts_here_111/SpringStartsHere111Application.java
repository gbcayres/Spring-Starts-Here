package com.example.spring_starts_here_111;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.example.spring_starts_here_111")
public class SpringStartsHere111Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringStartsHere111Application.class, args);
	}

}
