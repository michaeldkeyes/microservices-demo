package com.example.product;

import org.springframework.boot.SpringApplication;

public class TestMicroservicesApplication {

	public static void main(String[] args) {
		SpringApplication.from(ProductServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
