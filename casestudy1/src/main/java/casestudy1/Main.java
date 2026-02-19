package casestudy1;

public class Main {

    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();

        Profile profile = new Profile();
        profile.setId(1L);
        profile.setPhone("9999999999");
        profile.setAddress("Delhi");

        User user = new User();
        user.setId(1L);
        user.setName("Rahul");
        user.setEmail("rahul@mail.com");

        userService.registerUser(user, profile);

        System.out.println("Data inserted successfully");
    }
}
