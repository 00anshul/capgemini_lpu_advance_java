package bank.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import bank.validator.LoanValidator;

@Component
@Scope("prototype")   // new instance every time
public class IncomeValidator implements LoanValidator {

    @Override
    public void validateLoan(double amount) {
        System.out.println("Income validation for amount: " + amount);
        if (amount > 500000) {
            System.out.println("High value loan — monthly income must be above 50000");
        } else {
            System.out.println("Standard loan — monthly income must be above 25000");
        }
    }
}