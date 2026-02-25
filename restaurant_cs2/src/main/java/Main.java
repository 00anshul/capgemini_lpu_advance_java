import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import food.config.FoodAppConfig;
import food.impl.EmailNotification;
import food.impl.SmsNotification;
import food.order.OrderService;

public class Main {

    public static void main(String[] args) {

        System.out.println("--- Starting Spring Container ---");
        AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(FoodAppConfig.class);

        System.out.println("\n--- Placing Order (uses SMS via @Qualifier) ---");
        OrderService orderService = context.getBean(OrderService.class);
        orderService.placeOrder("2x Burger, 1x Fries");

        System.out.println("\n--- Testing Default Notification (@Primary = Email) ---");
        EmailNotification email = context.getBean(EmailNotification.class);
        email.sendNotification("Welcome to Foodify!");

        System.out.println("\n--- Testing Lazy Bean (SmsNotification) ---");
        SmsNotification sms1 = context.getBean(SmsNotification.class);
        SmsNotification sms2 = context.getBean(SmsNotification.class);
        System.out.println("Same SMS instance? " + (sms1 == sms2));
        // true — SmsNotification is singleton (lazy but still singleton)

        System.out.println("\n--- Closing Context ---");
        context.close();
    }
}