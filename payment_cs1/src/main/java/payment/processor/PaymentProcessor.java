package payment.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import payment.logger.TransactionLogger;
import payment.service.PaymentService;

@Component
public class PaymentProcessor {

    private PaymentService paymentService;

    @Autowired                        // field injection
    private TransactionLogger logger;

    // constructor injection with @Qualifier
    // explicitly asking for UpiPayment instead of primary CreditCardPayment
    @Autowired
    public PaymentProcessor(@Qualifier("upiPayment") PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void process(double amount) {
        logger.log("PaymentProcessor starting transaction");
        paymentService.processPayment(amount);
    }
}