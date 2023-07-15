package com.example.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(origins = "http://10.20.5.34:8080")

public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}
	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		return builder.routes()

				.route(p -> p
						.path("/merchant/deletemerchant/{merchantId}")
					.uri("http://localhost:8081"))
				.route(p -> p
					.path("/merchant/insertorupdate")
					.uri("http://localhost:8081"))
				.route(p -> p
						.path("/merchant/login/{email}/{password}")
						.uri("http://localhost:8081"))
				.route (p -> p
						.path("/merchant/getMerchantName/{merchantId}")
						.uri("http://localhost:8081"))
				.route(p -> p
						.path("/product/findOneProduct/{productId}")
						.uri("http://localhost:8082"))
				.route(p -> p
					.path("/product/addmerchantproduct/{productId}")
					.uri("http://localhost:8082"))
				.route(p -> p
					.path("/product/delProduct/{productId}/{merchantId}")
					.uri("http://localhost:8082"))
				.route(p -> p
					.path("/product/getProductsByCategory/{category}")
					.uri("http://localhost:8082"))
				.route(p -> p
						.path("/product/addOrUpdate")
						.uri("http://localhost:8082"))
				.route(p -> p
						.path("/product/editmerchantproduct/{productId}")
						.uri("http://localhost:8082"))
				.route(p -> p
						.path("/product/getProducts")
						.uri("http://localhost:8082"))
				.route(p -> p
						.path("/product/findMerchantProduct/{productId}/{merchantId}")
						.uri("http://localhost:8082"))
				.route(p -> p
						.path("/product/getProductBySearchTerm/{searchTerm}")
						.uri("http://localhost:8082"))
				.route(p -> p
						.path("/user/viewCart/{userId}")
						.uri("http://localhost:8083"))
				.route(p ->p
						.path("/user/checkOut/{userId}")
						.uri("http://localhost:8083"))
				.route(p -> p
						.path("/user/deleteCart/{userId}")
						.uri("http://localhost:8083"))
				.route(p -> p
						.path("/user/removeFromCart/{userId}")
						.uri("http://localhost:8083"))
				.route(p -> p
						.path("/user/viewHistory/{userId}")
						.uri("http://localhost:8083"))
				.route(p -> p
						.path("/user/deleteFromCart/{productId}/{merchantId}/{userId}")
						.uri("http://localhost:8083"))
				.route(p -> p
						.path("/user/addToCart/{userId}")
						.uri("http://localhost:8083"))
				.route(p -> p
						.path("/user/register")
						.uri("http://localhost:8083"))

				.route(p -> p
						.path("/user/login")
						.uri("http://localhost:8083"))
				.build();


	}
}
