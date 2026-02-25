package bank.audit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy     // only created when first needed
public class AuditService {

    @PostConstruct
    public void init() {
        System.out.println("AuditService initialized");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("AuditService destroyed");
    }

    public void audit(double amount, String validatorUsed) {
        System.out.println("AUDIT LOG — Amount: " + amount + 
                           " | Validator: " + validatorUsed);
    }
}