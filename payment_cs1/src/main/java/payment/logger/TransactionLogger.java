package payment.logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class TransactionLogger {

    @PostConstruct
    public void init() {
        System.out.println("Logger initialized");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Logger destroyed");
    }

    public void log(String message) {
        System.out.println("LOG: " + message);
    }
}