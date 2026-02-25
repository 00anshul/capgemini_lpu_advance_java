package spring_basics;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "spring_basics.prac")
public class Config {
	
	@Bean
	public Scanner getScanner() {
		return new Scanner(System.in);
	}

	@Bean
	public List<String> getList() {
		return new ArrayList<String>();
		
		
	}
}
