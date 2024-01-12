package com.fiap.postech.techchallenge.fastfoodordermanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;

@SpringBootApplication
public class FastfoodOrderManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(FastfoodOrderManagementApplication.class, args);
	}

}
