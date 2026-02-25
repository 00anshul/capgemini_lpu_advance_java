package food.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import food.delivery.DeliveryService;

@Component
public class RestaurantService {

    private DeliveryService deliveryService;

    // setter injection — Spring calls this method to inject DeliveryService
    @Autowired
    public void setDeliveryService(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
        System.out.println("DeliveryService injected into RestaurantService via Setter");
    }

    public void prepareOrder(String orderDetails) {
        System.out.println("Restaurant preparing: " + orderDetails);
        deliveryService.assignDelivery(orderDetails);
    }
}