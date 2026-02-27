package com.spring_prac;


public class Engine {
    private String type;        // e.g. "V8", "V6"
    private int horsepower;
    private String fuelType;    // e.g. "Petrol", "Diesel", "Electric"

    // Getters and Setters
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public int getHorsepower() { return horsepower; }
    public void setHorsepower(int horsepower) { this.horsepower = horsepower; }

    public String getFuelType() { return fuelType; }
    public void setFuelType(String fuelType) { this.fuelType = fuelType; }
}