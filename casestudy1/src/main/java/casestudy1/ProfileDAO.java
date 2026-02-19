package casestudy1;

public interface ProfileDAO {

    void saveProfile(Profile profile);

    Profile findProfile(Long id);

    void updateProfile(Profile profile);

    void deleteProfile(Long id);
}
