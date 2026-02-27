package com.spring_prac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class SpringbootBasicApplication {

	public static void main(String[] args) {
		ApplicationContext ioc =  SpringApplication.run(SpringbootBasicApplication.class, args);
		
		Doctor doc = ioc.getBean(Doctor.class);
		doc.check();
	
		
	}
	
	
}
