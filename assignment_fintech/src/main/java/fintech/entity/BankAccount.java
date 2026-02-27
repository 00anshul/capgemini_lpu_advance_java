package fintech.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bank_account")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    private String accountNumber;
    private String accountType;
    private BigDecimal balance;

    // 1:1 OWNING side — has @JoinColumn, creates customer_id FK
    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    // 1:N INVERSE side — mappedBy points to "bankAccount" field in Transaction
    @OneToMany(mappedBy = "bankAccount", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions = new ArrayList<>();

    public BankAccount() {}

    public BankAccount(String accountNumber, String accountType, BigDecimal balance) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
    }

    // helper method — syncs both sides of bidirectional 1:N
    public void addTransaction(Transaction txn) {
        transactions.add(txn);
        txn.setBankAccount(this);
    }

    public Long getAccountId() { return accountId; }

    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public String getAccountType() { return accountType; }
    public void setAccountType(String accountType) { this.accountType = accountType; }

    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public List<Transaction> getTransactions() { return transactions; }
    public void setTransactions(List<Transaction> transactions) { this.transactions = transactions; }

    @Override
    public String toString() {
        return "BankAccount [id=" + accountId + ", number=" + accountNumber +
               ", type=" + accountType + ", balance=" + balance + "]";
    }
}