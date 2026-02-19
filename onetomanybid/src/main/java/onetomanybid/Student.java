package onetomanybid;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Student {
	@Id
private int sid;
private String name;
private String brancch;
public int getSid() {
	return sid;
}
public void setSid(int sid) {
	this.sid = sid;
}
@Override
public String toString() {
	return "Student [sid=" + sid + ", name=" + name + ", brancch=" + brancch + "]";
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getBrancch() {
	return brancch;
}
public void setBrancch(String brancch) {
	this.brancch = brancch;
}

}
