package com.example.Search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SearchApplication {

	public static void main(String[] args) {
		System.out.println("Starting..");
		SpringApplication.run(SearchApplication.class, args);
		System.out.println("Finishing..");
	}

}
