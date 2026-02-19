package casestudy1;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    UserService userService = new UserServiceImpl();

    @Test
    void testOneToOnePersist() {

        Profile profile = new Profile();
        profile.setId(1L);
        profile.setPhone("9999999999");
        profile.setAddress("Delhi");

        User user = new User();
        user.setId(1L);
        user.setName("Rahul");
        user.setEmail("rahul@mail.com");

        userService.registerUser(user, profile);

        User fetched = userService.fetchUserWithOrders(1L);

        assertNotNull(fetched);
        assertNotNull(fetched.getProfile());
        assertEquals("Delhi", fetched.getProfile().getAddress());
    }

    @Test
    void testOneToManyPersist() {

        Profile profile = new Profile();
        profile.setId(1L);
        profile.setPhone("9999999999");
        profile.setAddress("Delhi");

        User user = new User();
        user.setId(1L);
        user.setName("Rahul");
        user.setEmail("rahul@mail.com");

        userService.registerUser(user, profile);

        PurchaseOrder o1 = new PurchaseOrder();
        o1.setId(101L);
        o1.setOrderDate(LocalDate.now());
        o1.setTotalAmount(5000);

        PurchaseOrder o2 = new PurchaseOrder();
        o2.setId(102L);
        o2.setOrderDate(LocalDate.now());
        o2.setTotalAmount(8000);

        List<PurchaseOrder> orders = new ArrayList<>();
        orders.add(o1);
        orders.add(o2);

        userService.addOrdersToUser(1L, orders);

        User fetched = userService.fetchUserWithOrders(1L);

        assertEquals(2, fetched.getOrders().size());
    }

    @Test
    void testDeleteUser() {

        userService.deleteUser(1L);

        User fetched = userService.fetchUserWithOrders(1L);

        assertNull(fetched);
    }
}
