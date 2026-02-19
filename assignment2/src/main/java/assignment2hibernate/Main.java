package assignment2hibernate;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        StudentService service = new StudentServiceImpl();

        AadharCard aadhar = new AadharCard();
        aadhar.setAadharId(1);
        aadhar.setAadharNumber("1234-5678-9012");
        aadhar.setAddress("Delhi");
        aadhar.setIssueDate(LocalDate.now());

        HostelRoom room = new HostelRoom();
        room.setRoomId(101);
        room.setRoomNumber("A-101");
        room.setBlockName("Block-A");
        room.setFloorNumber(1);

        Student student = new Student();
        student.setStudentId(1);
        student.setName("Anshul");
        student.setEmail("anshul@mail.com");
        student.setBranch("CSE");

        service.registerStudent(student, aadhar, room);

        Student fetched = service.fetchStudent(1);
        System.out.println(fetched.getName());

        service.deleteStudent(1);
    }
}
