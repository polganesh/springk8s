package com.poc.springk8s;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Springk8sApplication {
  Logger logger = LoggerFactory.getLogger(Springk8sApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(Springk8sApplication.class, args);
	}

}
