package manytomanybi;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Subject {
	@Id
private int id;
private String name;
private int numofdays;
@ManyToMany(mappedBy = "subjects") 
private List<Student> students  = new ArrayList<>();

public List<Student> getStudents() {
	return students;
}
public void setStudents(List<Student> students) {
	this.students = students;
}
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
