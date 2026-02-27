package fintech.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;

    private String cardNumber;
    private String cardType;       // DEBIT, CREDIT, PREPAID
    private String cardNetwork;    // VISA, MASTERCARD, RUPAY
    private LocalDate expiryDate;
    private Boolean isActive;

    // N:1 UNIDIRECTIONAL — Card knows account, account doesn't know cards
    @ManyToOne
    @JoinColumn(name = "account_id")
    private BankAccount bankAccount;

    // M:N INVERSE side — mappedBy points to "cards" field in Customer
    @ManyToMany(mappedBy = "cards")
    private List<Customer> customers = new ArrayList<>();

    public Card() {}

    public Card(String cardNumber, String cardType, 
                String cardNetwork, LocalDate expiryDate) {
        this.cardNumber = cardNumber;
        this.cardType = cardType;
        this.cardNetwork = cardNetwork;
        this.expiryDate = expiryDate;
        this.isActive = true;   // active by default
    }

    public Long getCardId() { return cardId; }

    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }

    public String getCardType() { return cardType; }
    public void setCardType(String cardType) { this.cardType = cardType; }

    public String getCardNetwork() { return cardNetwork; }
    public void setCardNetwork(String cardNetwork) { this.cardNetwork = cardNetwork; }

    public LocalDate getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }

    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }

    public BankAccount getBankAccount() { return bankAccount; }
    public void setBankAccount(BankAccount bankAccount) { this.bankAccount = bankAccount; }

    public List<Customer> getCustomers() { return customers; }
    public void setCustomers(List<Customer> customers) { this.customers = customers; }

    @Override
    public String toString() {
        return "Card [id=" + cardId + ", number=" + cardNumber +
               ", type=" + cardType + ", network=" + cardNetwork +
               ", expiry=" + expiryDate + ", active=" + isActive + "]";
    }
}