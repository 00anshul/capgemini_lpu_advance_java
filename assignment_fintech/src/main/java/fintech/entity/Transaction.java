package fintech.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    private String txnType;        // CREDIT or DEBIT
    private BigDecimal amount;
    private LocalDateTime txnDate;
    private String description;
    private String referenceNumber;

    // N:1 OWNING side — has @JoinColumn, creates account_id FK
    @ManyToOne
    @JoinColumn(name = "account_id")
    private BankAccount bankAccount;

    public Transaction() {}

    public Transaction(String txnType, BigDecimal amount, 
                       String description, String referenceNumber) {
        this.txnType = txnType;
        this.amount = amount;
        this.description = description;
        this.referenceNumber = referenceNumber;
        this.txnDate = LocalDateTime.now();
    }

    public Long getTransactionId() { return transactionId; }

    public String getTxnType() { return txnType; }
    public void setTxnType(String txnType) { this.txnType = txnType; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public LocalDateTime getTxnDate() { return txnDate; }
    public void setTxnDate(LocalDateTime txnDate) { this.txnDate = txnDate; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getReferenceNumber() { return referenceNumber; }
    public void setReferenceNumber(String referenceNumber) { this.referenceNumber = referenceNumber; }

    public BankAccount getBankAccount() { return bankAccount; }
    public void setBankAccount(BankAccount bankAccount) { this.bankAccount = bankAccount; }

    @Override
    public String toString() {
        return "Transaction [id=" + transactionId + ", type=" + txnType +
               ", amount=" + amount + ", date=" + txnDate + 
               ", desc=" + description + "]";
    }
}