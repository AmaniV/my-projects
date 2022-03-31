package org.com.extraction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ExtractionApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExtractionApplication.class, args);
	}

}
