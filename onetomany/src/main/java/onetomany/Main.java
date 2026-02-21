package onetomany;

public class Main {
	public static void main(String[] args) {

		//insert data to tables
		StudentDao sdao = new StudentDao();
		CollegeDao cdao = new CollegeDao();

//		Student s = new Student();
//		s.setSid(6);
//		s.setName("Mark");
//		s.setBrancch("CSE");
//
//		College c = new College();
//		c.setId(1);
//		c.setName("ABC");
//		c.setLocation("Delhi");
//		c.setPincode("823898");
//		
//		College c1 = cdao.findCollege(1);
//
//		c.getStudent().add(s);
//
//		sdao.saveStudent(s);
//		cdao.saveCollege(c);
//		
//		cdao.updateCollege(c1);
		
		//delete data
//		StudentDao sdao = new StudentDao();
//		sdao.deleteStudent(3, 1);
		
		
		//update student
//		 StudentDao sdao = new StudentDao();
//	        Student s = sdao.findStudent(4);
//
//	        s.setName("John Updated");
//	        s.setBrancch("CSE");
//
//	        sdao.updateStudent(s);
		
		
		//print student
//		StudentDAO sdao = new StudentDAO();
//
		Student s = sdao.findStudent(1);
		System.out.println(s);


	}
}
