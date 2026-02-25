package food.delivery;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")   // same instance reused every time — this is actually default
public class DeliveryService {

    @PostConstruct
    public void init() {
        System.out.println("Delivery Service Ready");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Delivery Service Closed");
    }

    public void assignDelivery(String orderDetails) {
        System.out.println("Delivery assigned for: " + orderDetails);
    }
}