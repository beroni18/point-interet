package com.sji.group9.pointInteret;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PointInteretApplication {

	public static void main(String[] args) {

		SpringApplication.run(PointInteretApplication.class, args);
		System.out.println("Point d'interet is running");
	}

}
