package food.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import food.restaurant.RestaurantService;
import food.service.NotificationService;

@Component
public class OrderService {

    private NotificationService notificationService;

    @Autowired                           // field injection
    private RestaurantService restaurantService;

    // constructor injection with @Qualifier
    // explicitly using SMS instead of primary Email
    @Autowired
    public OrderService(@Qualifier("smsNotification") NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void placeOrder(String orderDetails) {
        System.out.println("\nOrder placed: " + orderDetails);
        restaurantService.prepareOrder(orderDetails);
        notificationService.sendNotification("Your order is confirmed: " + orderDetails);
    }
}