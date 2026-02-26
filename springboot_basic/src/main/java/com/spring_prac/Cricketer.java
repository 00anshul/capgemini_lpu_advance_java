package com.capgemini.springboot_basic;

public class Cricketer {
private String name;
private String role;
private int run; 
private int num_matches;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}
public int getRun() {
	return run;
}
public void setRun(int run) {
	this.run = run;
}
public int getNum_matches() {
	return num_matches;
}
@Override
public String toString() {
	return "Cricketer [name=" + name + ", role=" + role + ", run=" + run + ", num_matches=" + num_matches + "]";
}
public void setNum_matches(int num_matches) {
	this.num_matches = num_matches;
}
}
