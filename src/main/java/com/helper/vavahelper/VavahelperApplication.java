package com.helper.vavahelper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class VavahelperApplication {

	public static void main(String[] args) {
		SpringApplication.run(VavahelperApplication.class, args);
	}

}
