package payment.impl;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import payment.logger.TransactionLogger;
import payment.service.PaymentService;

@Component
@Primary     // default implementation when no @Qualifier specified
@Lazy        // only created when first requested — not at startup
public class CreditCardPayment implements PaymentService {

    private TransactionLogger logger;

    // constructor injection
    @Autowired
    public CreditCardPayment(TransactionLogger logger) {
        this.logger = logger;
        System.out.println("CreditCardPayment bean created");
    }

    @Override
    public void processPayment(double amount) {
        System.out.println("Processing Credit Card payment of: " + amount);
        logger.log("CreditCard transaction: " + amount);
    }
}