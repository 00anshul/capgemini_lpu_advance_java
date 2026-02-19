package onetoonemapping;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Car {
@Id	
private int id;
private String brand;
private String model;
private String year;

@OneToOne
private Engine engine;


public Engine getEngine() {
	return engine;
}
public void setEngine(Engine engine) {
	this.engine = engine;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getBrand() {
	return brand;
}
public void setBrand(String brand) {
	this.brand = brand;
}
public String getModel() {
	return model;
}
public void setModel(String model) {
	this.model = model;
}
public String getYear() {
	return year;
}
public void setYear(String year) {
	this.year = year;
}
public int getPrice() {
	return price;
}
public void setPrice(int price) {
	this.price = price;
}
private  int price;

@Override
public String toString() {
	return "Car [id=" + id + ", brand=" + brand + ", model=" + model + ", year=" + year + ", engine=" + engine
			+ ", price=" + price + "]";
}
}
