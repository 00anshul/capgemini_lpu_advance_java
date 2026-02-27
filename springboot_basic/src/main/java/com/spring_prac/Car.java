package com.spring_prac;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity  // ← You forgot this! Marks class as a DB table
public class Car {

    @Id  // ← Marks primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // ← Auto-increment ID
    private int id;

    private String brand;
    private String model;
    private int year;
    @Column(nullable = true) 
    private double price;

    public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	// Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    @Override
	public String toString() {
		return "Car [id=" + id + ", brand=" + brand + ", model=" + model + ", year=" + year + ", price=" + price + "]";
	}
}