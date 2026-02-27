package fintech.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import java.math.BigDecimal;
import java.util.List;
import fintech.entity.BankAccount;
import fintech.entity.Customer;
import fintech.util.JPAUtil;

public class BankAccountDAO {

    public void save(BankAccount account) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(account);
            em.getTransaction().commit();
            System.out.println("Account saved: " + account);
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Failed to save account: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public BankAccount findById(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(BankAccount.class, id);
        } finally {
            em.close();
        }
    }

    public BankAccount findByAccountNumber(String accountNumber) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery(
                "SELECT a FROM BankAccount a WHERE a.accountNumber = :num",
                 BankAccount.class)
                .setParameter("num", accountNumber)
                .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    // links account and customer — sets BOTH sides of bidirectional 1:1
    public void linkToCustomer(Long accountId, Long customerId) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();

            BankAccount account = em.find(BankAccount.class, accountId);
            Customer customer = em.find(Customer.class, customerId);

            if (account == null || customer == null) {
                System.out.println("Account or Customer not found");
                em.getTransaction().rollback();
                return;
            }

            // sync BOTH sides of bidirectional 1:1
            account.setCustomer(customer);
            customer.setBankAccount(account);

            em.merge(account);
            em.merge(customer);

            em.getTransaction().commit();
            System.out.println("Account " + accountId + 
                               " linked to Customer " + customerId);
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Failed to link: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    // dirty checking handles UPDATE — no merge() needed
    public void updateBalance(Long accountId, BigDecimal newBalance) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            BankAccount account = em.find(BankAccount.class, accountId);
            if (account != null) {
                account.setBalance(newBalance);  // dirty checking auto-updates
                System.out.println("Balance updated to: " + newBalance);
            } else {
                System.out.println("Account not found: " + accountId);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Failed to update balance: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public List<BankAccount> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT a FROM BankAccount a", BankAccount.class)
                     .getResultList();
        } finally {
            em.close();
        }
    }
}