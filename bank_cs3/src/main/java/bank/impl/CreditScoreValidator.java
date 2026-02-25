package bank.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import bank.validator.LoanValidator;

@Component
@Primary     // default validator
public class CreditScoreValidator implements LoanValidator {

    @Override
    public void validateLoan(double amount) {
        System.out.println("CreditScore validation for amount: " + amount);
        if (amount > 1000000) {
            System.out.println("High value loan — credit score must be above 750");
        } else {
            System.out.println("Standard loan — credit score must be above 650");
        }
    }
}