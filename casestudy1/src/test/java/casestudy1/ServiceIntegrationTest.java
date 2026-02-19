package casestudy1;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ServiceIntegrationTest {

    UserService userService = new UserServiceImpl();
    PaymentService paymentService = new PaymentServiceImpl();

    @Test
    void testFullWorkflow() {

        Profile profile = new Profile();
        profile.setId(10L);
        profile.setPhone("8888888888");
        profile.setAddress("Punjab");

        User user = new User();
        user.setId(10L);
        user.setName("Anshul");
        user.setEmail("anshul@mail.com");

        userService.registerUser(user, profile);

        PurchaseOrder o1 = new PurchaseOrder();
        o1.setId(1001L);
        o1.setOrderDate(LocalDate.now());
        o1.setTotalAmount(3000);

        PurchaseOrder o2 = new PurchaseOrder();
        o2.setId(1002L);
        o2.setOrderDate(LocalDate.now());
        o2.setTotalAmount(7000);

        List<PurchaseOrder> orders = new ArrayList<>();
        orders.add(o1);
        orders.add(o2);

        userService.addOrdersToUser(10L, orders);

        Payment payment = new Payment();
        payment.setId(5001L);
        payment.setMode("CARD");
        payment.setAmount(3000);

        paymentService.addPaymentToOrder(1001L, payment);

        User fetched = userService.fetchUserWithOrders(10L);

        assertNotNull(fetched);
        assertEquals(2, fetched.getOrders().size());
    }
}
