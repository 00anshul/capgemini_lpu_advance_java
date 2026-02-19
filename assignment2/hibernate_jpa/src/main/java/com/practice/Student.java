package com.practice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "student_info") // to specify table name
public class Student {

	@Id
	@Column(name = "Sid")
	private int id;
	@Column(name = "Sname")
	private String name;
	private double percentage;
	private String dob;

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	@Override
	public String toString() {
	    return "Student [Sid=" + id +
	           ", name=" + name +
	           ", dob=" + dob +
	           ", percentage=" + percentage + "]";
	}
}
