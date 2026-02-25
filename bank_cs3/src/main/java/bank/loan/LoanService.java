package bank.loan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import bank.audit.AuditService;
import bank.validator.LoanValidator;

@Component
public class LoanService {

    private LoanValidator loanValidator;
    private AuditService auditService;

    // constructor injection with @Qualifier
    // explicitly using IncomeValidator instead of primary CreditScoreValidator
    @Autowired
    public LoanService(@Qualifier("incomeValidator") LoanValidator loanValidator) {
        this.loanValidator = loanValidator;
        System.out.println("LoanService created with: " + 
                           loanValidator.getClass().getSimpleName());
    }

    // setter injection for AuditService
    @Autowired
    public void setAuditService(AuditService auditService) {
        this.auditService = auditService;
        System.out.println("AuditService injected into LoanService via Setter");
    }

    public void applyLoan(double amount) {
        System.out.println("\n--- Loan Application for: " + amount + " ---");
        loanValidator.validateLoan(amount);
        auditService.audit(amount, loanValidator.getClass().getSimpleName());
    }
}