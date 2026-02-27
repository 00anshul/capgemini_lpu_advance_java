package fintech.dao;

import jakarta.persistence.EntityManager;
import java.util.List;
import fintech.entity.BankAccount;
import fintech.entity.Card;
import fintech.entity.Customer;
import fintech.util.JPAUtil;

public class CardDAO {

    // unidirectional — only set card side, account unaware
    public void issueCard(Card card, Long accountId) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();

            BankAccount account = em.find(BankAccount.class, accountId);
            if (account == null) {
                System.out.println("Account not found: " + accountId);
                em.getTransaction().rollback();
                return;
            }

            card.setBankAccount(account);  // only one side — unidirectional
            em.persist(card);

            em.getTransaction().commit();
            System.out.println("Card issued: " + card);
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Failed to issue card: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    // M:N — add on OWNING side (Customer) to write to customer_card table
    public void assignCardToCustomer(Long cardId, Long customerId) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();

            Card card = em.find(Card.class, cardId);
            Customer customer = em.find(Customer.class, customerId);

            if (card == null || customer == null) {
                System.out.println("Card or Customer not found");
                em.getTransaction().rollback();
                return;
            }

            // add on OWNING side — writes to customer_card join table
            customer.getCards().add(card);
            em.merge(customer);

            em.getTransaction().commit();
            System.out.println("Card " + cardId + 
                               " assigned to Customer " + customerId);
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Failed to assign card: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public Card findById(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Card.class, id);
        } finally {
            em.close();
        }
    }

    public List<Card> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT c FROM Card c", Card.class)
                     .getResultList();
        } finally {
            em.close();
        }
    }

    // dirty checking handles UPDATE — no merge() needed
    public void deactivateCard(Long cardId) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Card card = em.find(Card.class, cardId);
            if (card != null) {
                card.setIsActive(false);  // dirty checking auto-updates
                System.out.println("Card deactivated: " + cardId);
            } else {
                System.out.println("Card not found: " + cardId);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Failed to deactivate card: " + e.getMessage());
        } finally {
            em.close();
        }
    }
}