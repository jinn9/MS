package com.github.jinn9.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}


//	Config for custom routing
//	@Bean
//	public RouteLocator routeConfig(RouteLocatorBuilder routeLocatorBuilder) {
//		return routeLocatorBuilder.routes()
//				.route(p -> p
//						.path("/amazonna/member/**")
//						.filters( f -> f.rewritePath("/amazonna/member/(?<segment>.*)","/${segment}")
//								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
//						.uri("lb://MEMBER"))
//				.route(p -> p
//						.path("/amazonna/product/**")
//						.filters( f -> f.rewritePath("/amazonna/product/(?<segment>.*)","/${segment}")
//								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
//						.uri("lb://PRODUCT"))
//				.route(p -> p
//						.path("/amazonna/order/**")
//						.filters( f -> f.rewritePath("/amazonna/order/(?<segment>.*)","/${segment}")
//								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
//						.uri("lb://ORDER")).build();
//	}

}
