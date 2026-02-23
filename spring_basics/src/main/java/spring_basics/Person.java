package spring_basics;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Person {
	@Autowired
	private Mobile m ;
	@Autowired
	private Scanner scan;
	
public Scanner getScan() {
		return scan;
	}

	public void setScan(Scanner scan) {
		this.scan = scan;
	}

public Mobile getM() {
		return m;
	}

	public void setM(Mobile m) {
		this.m = m;
	}

void meassage() {
	System.out.println("hi");
}
}
