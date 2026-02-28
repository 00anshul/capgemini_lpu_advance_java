package com.library.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.library.entity.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    // find all loans for a specific member
    List<Loan> findByMemberMemberId(Long memberId);

    // find all loans for a specific book
    List<Loan> findByBookBookId(Long bookId);

    // find loans by status
    List<Loan> findByLoanStatus(String loanStatus);

    // find active loan for a specific book
    // used when returning — find the ISSUED loan for this book
    List<Loan> findByBookBookIdAndLoanStatus(Long bookId, String loanStatus);

    // custom JPQL — find all overdue loans
    @Query("SELECT l FROM Loan l WHERE l.loanStatus = 'ISSUED' AND l.dueDate < CURRENT_DATE")
    List<Loan> findOverdueLoans();

    // custom JPQL — loan history for a member with book details
    @Query("SELECT l FROM Loan l JOIN FETCH l.book WHERE l.member.memberId = :memberId")
    List<Loan> findLoanHistoryByMember(@Param("memberId") Long memberId);
}