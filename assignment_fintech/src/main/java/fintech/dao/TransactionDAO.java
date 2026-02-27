package fintech.dao;

import jakarta.persistence.EntityManager;
import java.util.List;
import fintech.entity.BankAccount;
import fintech.entity.Transaction;
import fintech.util.JPAUtil;

public class TransactionDAO {

    // addTransaction() helper syncs both sides of bidirectional 1:N
    public void save(Transaction txn, Long accountId) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();

            BankAccount account = em.find(BankAccount.class, accountId);
            if (account == null) {
                System.out.println("Account not found: " + accountId);
                em.getTransaction().rollback();
                return;
            }

            // helper method sets both sides:
            // transactions.add(txn) AND txn.setBankAccount(this)
            account.addTransaction(txn);
            em.persist(txn);

            em.getTransaction().commit();
            System.out.println("Transaction saved: " + txn);
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Failed to save transaction: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public List<Transaction> findByAccount(Long accountId) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery(
                "SELECT t FROM Transaction t WHERE t.bankAccount.accountId = :aid " +
                "ORDER BY t.txnDate DESC", Transaction.class)
                .setParameter("aid", accountId)
                .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Transaction> findByType(Long accountId, String type) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery(
                "SELECT t FROM Transaction t WHERE t.bankAccount.accountId = :aid " +
                "AND t.txnType = :type ORDER BY t.txnDate DESC", Transaction.class)
                .setParameter("aid", accountId)
                .setParameter("type", type)
                .getResultList();
        } finally {
            em.close();
        }
    }
}