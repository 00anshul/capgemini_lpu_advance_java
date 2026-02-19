package assignment2hibernate;

public interface StudentService {

    void registerStudent(Student student, AadharCard aadhar, HostelRoom room);

    Student fetchStudent(int id);

    void deleteStudent(int id);
}
