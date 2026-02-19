package assignment2hibernate;

public interface StudentDAO {

    void saveStudent(Student student);

    Student findStudent(int id);

    void deleteStudent(int id);
}
