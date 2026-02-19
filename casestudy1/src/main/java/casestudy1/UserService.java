package casestudy1;

import java.util.List;

public interface UserService {

    void registerUser(User user, Profile profile);

    void addOrdersToUser(Long userId, List<PurchaseOrder> orders);

    User fetchUserWithOrders(Long userId);

    void deleteUser(Long userId);
}
