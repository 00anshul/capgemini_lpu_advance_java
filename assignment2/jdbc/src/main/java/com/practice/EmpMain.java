package com.practice;

import java.util.ArrayList;
import java.util.List;

public class EmpMain {
public static void  main(String[] args) {
//	ArrayList<Employee> list = new ArrayList<>();
//	list.add(new Employee(123,"Roy",23,"DEV"));
//	list.add(new Employee(163,"Joe",32,"MANAGER"));
//	list.add(new Employee(143,"Bob",34,"SALES"));
//	list.add(new Employee(134,"Cole",33,"SALES"));
//	list.add(new Employee(178,"Tom",21,"DEV"));
//	
//	for(Employee l : list) {
//	System.out.println(l);
//	}
//	System.out.println(list.isEmpty());
	
}
public static Emplist createEmployees() {
	Emplist list = new Emplist();
	list.add(new Employee(123,"Roy",23,"DEV"));
	list.add(new Employee(163,"Joe",32,"MANAGER"));
	list.add(new Employee(143,"Bob",34,"SALES"));
	list.add(new Employee(134,"Cole",33,"SALES"));
//	list.add(new Employee(178,"Tom",21,"DEV"));
	return list;
	 }
}
