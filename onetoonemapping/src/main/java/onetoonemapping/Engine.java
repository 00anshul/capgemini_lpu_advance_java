package onetoonemapping;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Engine {
	@Id
private int id;
private String type;
private int mileage;
private int cc;
@Override
public String toString() {
	return "Engine [id=" + id + ", type=" + type + ", mileage=" + mileage + ", cc=" + cc + "]";
}

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public int getMileage() {
	return mileage;
}
public void setMileage(int mileage) {
	this.mileage = mileage;
}
public int getCc() {
	return cc;
}
public void setCc(int cc) {
	this.cc = cc;
}

}
