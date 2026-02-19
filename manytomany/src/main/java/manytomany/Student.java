package manytomany;                 

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "number_sequence")
	@SequenceGenerator(name="number_sequence",sequenceName = "number_sequence", initialValue = 100,allocationSize = 1)
private int id;
private String name;
private String branch;
private String gender;
@ManyToMany
private List<Subject> subjects;

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
