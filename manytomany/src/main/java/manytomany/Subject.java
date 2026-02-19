package manytomany;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Subject {
	@Id
private int id;
private String name;
private int numofdays;

@Override
public String toString() {
	return "Subject [id=" + id + ", name=" + name + ", numofdays=" + numofdays + "]";
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
public int getNumofdays() {
	return numofdays;
}
public void setNumofdays(int numofdays) {
	this.numofdays = numofdays;
}
}
