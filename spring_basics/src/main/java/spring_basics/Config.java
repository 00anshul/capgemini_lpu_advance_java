package spring_basics;

import java.util.Scanner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan (basePackages ="spring_basics")
public class Config {
	@Bean
public Scanner getScanner() {
	return new Scanner(System.in);
	
}
}
