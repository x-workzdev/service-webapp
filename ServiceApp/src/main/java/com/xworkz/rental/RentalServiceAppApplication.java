package com.xworkz.rental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class RentalServiceAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentalServiceAppApplication.class, args);
	} 
}
