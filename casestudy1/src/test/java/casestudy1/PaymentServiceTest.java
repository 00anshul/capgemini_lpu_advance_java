package casestudy1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PaymentServiceTest {

    PaymentService paymentService = new PaymentServiceImpl();
    OrderDAO orderDAO = new OrderDAOImpl();
    UserService userService = new UserServiceImpl();

    @Test
    void testManyToOnePersist() {

        Profile profile = new Profile();
        profile.setId(1L);
        profile.setPhone("9999999999");
        profile.setAddress("Delhi");

        User user = new User();
        user.setId(1L);
        user.setName("Rahul");
        user.setEmail("rahul@mail.com");

        userService.registerUser(user, profile);

        PurchaseOrder order = new PurchaseOrder();
        order.setId(101L);
        order.setOrderDate(LocalDate.now());
        order.setTotalAmount(5000);

        List<PurchaseOrder> orders = new ArrayList<>();
        orders.add(order);

        userService.addOrdersToUser(1L, orders);

        Payment payment = new Payment();
        payment.setId(201L);
        payment.setMode("UPI");
        payment.setAmount(5000);

        paymentService.addPaymentToOrder(101L, payment);

        Payment fetched = paymentService.findPayment(201L);

        assertNotNull(fetched);
        assertEquals(5000, fetched.getAmount());
    }

}
