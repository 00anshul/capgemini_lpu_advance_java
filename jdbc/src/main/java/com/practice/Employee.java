package com.practice;

import java.util.ArrayList;

public class Employee  {
private int id;
private String name;
private int age;
private String dept;

public Employee(int id, String name, int age, String dept) {
	this.id = id;
	this.name = name;
	this.age = age;
	this.dept = dept;
}
public boolean isValidName() {
    return name != null && !name.trim().isEmpty()
    		&& name.matches("[A-Za-z'\\- ]+");
}
public boolean isValidAge() {
    return age>=18;
}
@Override
public String toString() {
	return "Employee [id=" + id + ", name=" + name + ", age=" + age + ", dept=" + dept + "]";
}
}

