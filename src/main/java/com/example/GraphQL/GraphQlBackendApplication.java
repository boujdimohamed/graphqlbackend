package com.example.GraphQL;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient // Eureka Client
public class GraphQlBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraphQlBackendApplication.class, args); // Startet die Spring-Boot-Anwendung
	}

}
