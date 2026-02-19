package assignment2hibernate;

public interface AadharCardDAO {

    void saveAadhar(AadharCard aadhar);

    AadharCard findAadhar(int id);
}
