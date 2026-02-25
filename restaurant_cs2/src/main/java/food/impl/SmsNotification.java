package food.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import food.service.NotificationService;

@Component
@Lazy     // only created when first requested
public class SmsNotification implements NotificationService {

    @Override
    public void sendNotification(String message) {
        System.out.println("SMS NOTIFICATION: " + message);
    }
}