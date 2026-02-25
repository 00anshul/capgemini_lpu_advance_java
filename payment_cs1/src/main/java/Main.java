import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import payment.config.AppConfig;
import payment.impl.CreditCardPayment;
import payment.impl.UpiPayment;
import payment.processor.PaymentProcessor;

public class Main {

    public static void main(String[] args) {

        // start Spring container
        AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);

        System.out.println("\n--- Using PaymentProcessor (uses UPI via @Qualifier) ---");
        PaymentProcessor processor = context.getBean(PaymentProcessor.class);
        processor.process(1500.0);

        System.out.println("\n--- Getting CreditCardPayment directly (@Primary, @Lazy) ---");
        CreditCardPayment creditCard = context.getBean(CreditCardPayment.class);
        creditCard.processPayment(2000.0);

        System.out.println("\n--- Prototype scope demo (UpiPayment) ---");
        UpiPayment upi1 = context.getBean(UpiPayment.class);
        UpiPayment upi2 = context.getBean(UpiPayment.class);
        System.out.println("Same instance? " + (upi1 == upi2));
        // false — prototype creates new instance each time

        // close context — triggers @PreDestroy
        context.close();
    }
}         