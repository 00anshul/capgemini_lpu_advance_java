package assignment2hibernate;

public class StudentServiceImpl implements StudentService {

    private StudentDAO studentDAO = new StudentDAOImpl();
    private AadharCardDAO aadharDAO = new AadharCardDAOImpl();
    private HostelRoomDAO roomDAO = new HostelRoomDAOImpl();

    @Override
    public void registerStudent(Student student,
                                AadharCard aadhar,
                                HostelRoom room) {

        aadharDAO.saveAadhar(aadhar);
        roomDAO.saveRoom(room);

        student.setAadharCard(aadhar);
        student.setHostelRoom(room);

        studentDAO.saveStudent(student);
    }

    @Override
    public Student fetchStudent(int id) {

        return studentDAO.findStudent(id);
    }

    @Override
    public void deleteStudent(int id) {

        studentDAO.deleteStudent(id);
    }
}
