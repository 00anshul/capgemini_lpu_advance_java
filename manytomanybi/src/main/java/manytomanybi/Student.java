package manytomanybi;                 

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Student {
	@Id
	
private int id;
private String name;
private String branch;
private String gender;
@ManyToMany(cascade=CascadeType.ALL)                          // owning side
@JoinTable
private List<Subject> subjects  = new ArrayList<>();

public List<Subject> getSubjects() {
	return subjects;
}
public void setSubjects(List<Subject> subjects) {
	this.subjects = subjects;
}
@Override
public String toString() {
	return "Student [id=" + id + ", name=" + name + ", branch=" + branch + ", gender=" + gender + ", subjects="
			+ subjects + "]";
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
public String getBranch() {
	return branch;
}
public void setBranch(String branch) {
	this.branch = branch;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
}
