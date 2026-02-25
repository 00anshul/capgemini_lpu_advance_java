package com.capgemini;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.capgemini")
public class ProjectConfig {
//	@Bean
//	public EntityManager getEntityManager() {
//		return Persistence.createEntityManagerFactory("postgres").createEntityManager();
//	}
}
