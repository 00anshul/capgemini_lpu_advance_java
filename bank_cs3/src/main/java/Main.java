import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import bank.config.BankAppConfig;
import bank.impl.CreditScoreValidator;
import bank.impl.IncomeValidator;
import bank.loan.LoanService;
import bank.validator.LoanValidator;

public class Main {

    public static void main(String[] args) {

        System.out.println("--- Starting Spring Container ---");
        AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(BankAppConfig.class);

        System.out.println("\n--- Applying Loan (uses IncomeValidator via @Qualifier) ---");
        LoanService loanService = context.getBean(LoanService.class);
        loanService.applyLoan(750000);

        System.out.println("\n--- Applying Another Loan ---");
        loanService.applyLoan(200000);

        System.out.println("\n--- Testing @Primary (CreditScoreValidator) ---");
        LoanValidator defaultValidator = context.getBean(LoanValidator.class);
        System.out.println("Default validator: " + 
                           defaultValidator.getClass().getSimpleName());
        defaultValidator.validateLoan(500000);

        System.out.println("\n--- Prototype Scope Demo (IncomeValidator) ---");
        IncomeValidator iv1 = context.getBean(IncomeValidator.class);
        IncomeValidator iv2 = context.getBean(IncomeValidator.class);
        System.out.println("Same instance? " + (iv1 == iv2));

        System.out.println("\n--- Closing Context ---");
        context.close();
    }
}