package casestudy1;

public interface UserDAO {

    void saveUser(User u);

    User findUser(Long id);

    void updateUser(User u);

    void deleteUser(Long id);
}
