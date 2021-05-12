package com.raminq.abohava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AbohavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AbohavaApplication.class, args);
	}

}
