package food.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import food.service.NotificationService;

@Component
@Primary     // default notification — used unless @Qualifier overrides
public class EmailNotification implements NotificationService {

    @Override
    public void sendNotification(String message) {
        System.out.println("EMAIL NOTIFICATION: " + message);
    }
}