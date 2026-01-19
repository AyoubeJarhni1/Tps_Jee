package com.ensa.tp8;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ensa.tp8.services.ProductService;

@SpringBootApplication
public class Tp8Application {

	public static void main(String[] args) {
		SpringApplication.run(Tp8Application.class, args);
	}

	@Bean
	public CommandLineRunner demo(ProductService productService) {
		return (args) -> {
			System.out.println("Application started successfully!");
		};
	}

}
