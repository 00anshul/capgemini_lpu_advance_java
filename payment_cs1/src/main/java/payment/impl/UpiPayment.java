package payment.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import payment.logger.TransactionLogger;
import payment.service.PaymentService;

@Component
@Scope("prototype")   // new instance created every time it is requested
public class UpiPayment implements PaymentService {

    private TransactionLogger logger;

    // constructor injection
    @Autowired
    public UpiPayment(TransactionLogger logger) {
        this.logger = logger;
        System.out.println("UpiPayment bean created");
    }

    @Override
    public void processPayment(double amount) {
        System.out.println("Processing UPI payment of: " + amount);
        logger.log("UPI transaction: " + amount);
    }
}