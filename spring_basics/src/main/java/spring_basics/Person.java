package spring_basics;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Person {
	
	//Dependency injection
	private Mobile m ;
	@Autowired
	private Scanner scan;
	
	//Constructor Injection
	Person(Mobile m){
		this.m = m;
	}
	
public Scanner getScan() {
		return scan;
	}

	public void setScan(Scanner scan) {
		this.scan = scan;
	}

public Mobile getM() {
		return m;
	}

//Setter Injection
//@Autowired
//	public void setM(Mobile m) {
//		this.m = m;
//	}

void meassage() {
	System.out.println("hi");
}
}
