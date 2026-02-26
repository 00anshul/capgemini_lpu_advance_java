package com.capgemini.springboot_basic;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity

public class Car {
	@Id
    private String brand;       // e.g. "Toyota"
    private String model;       // e.g. "Camry"
    private int year;     // nested Engine object

    // Getters and Setters
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
  
}