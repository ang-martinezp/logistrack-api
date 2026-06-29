package com.logistrack.dispatch_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
public class DispatchServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DispatchServiceApplication.class, args);
	}

}
