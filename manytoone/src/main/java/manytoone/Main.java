package manytoone;

public class Main {
public static void main(String[] args) {
	
	//create dept
//	DepartmentDao ddao = new DepartmentDao();
//
//	Department d = new Department();
//	d.setName("IT");
//	d.setMgrname("Raj");
//	d.setNumofemp(10);
//
//	ddao.saveDepartment(d);

	//create emp
	EmployeeDao edao = new EmployeeDao();
	Department d1 = ddao.findDepartment(1);

	Employee e = new Employee();
	e.setName("Rock");
	e.setSalary(55000);
	e.setDesignation("Engineer");

	e.setDepartment(d1);

	edao.saveEmployee(e);

	//delete employee
//	edao.deleteEmployee(101);
	
	//delete department
	//ddao.deleteDepartment(1);
}
}
