package com.quickboard.resourceprofile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ResourceProfileApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResourceProfileApplication.class, args);
	}

}
