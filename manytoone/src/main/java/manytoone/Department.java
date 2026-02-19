package manytoone;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "number_sequence")
	@SequenceGenerator(name="number",sequenceName = "number_sequence", initialValue = 1,allocationSize = 1)
	private int id;
	private String name;
	private String mgrname;
	private int numofemp;
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
	public String getMgrname() {
		return mgrname;
	}
	public void setMgrname(String mgrname) {
		this.mgrname = mgrname;
	}
	public int getNumofemp() {
		return numofemp;
	}
	public void setNumofemp(int numofemp) {
		this.numofemp = numofemp;
	}
	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", mgrname=" + mgrname + ", numofemp=" + numofemp + "]";
	}
	
	

}
