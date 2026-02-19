package casestudy1;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO = new UserDAOImpl();
    private OrderDAO orderDAO = new OrderDAOImpl();

    @Override
    public void registerUser(User user, Profile profile) {

        ProfileDAO profileDAO = new ProfileDAOImpl();

        profileDAO.saveProfile(profile); // save first
        user.setProfile(profile);

        userDAO.saveUser(user);
    }


    @Override
    public void addOrdersToUser(Long userId, List<PurchaseOrder> orders) {

        User user = userDAO.findUser(userId);

        if (user != null) {
            user.setOrders(orders);

            for (PurchaseOrder o : orders) {
                orderDAO.saveOrder(o);
            }

            userDAO.updateUser(user);
        }
    }

    @Override
    public User fetchUserWithOrders(Long userId) {

        return userDAO.findUser(userId);
    }

    @Override
    public void deleteUser(Long userId) {

        userDAO.deleteUser(userId);
    }
}
