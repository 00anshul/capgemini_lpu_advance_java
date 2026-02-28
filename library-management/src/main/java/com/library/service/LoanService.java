package com.library.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.library.entity.Book;
import com.library.entity.Loan;
import com.library.entity.Member;
import com.library.exception.ResourceNotFoundException;
import com.library.repository.BookRepository;
import com.library.repository.LoanRepository;
import com.library.repository.MemberRepository;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    public LoanService(LoanRepository loanRepository,
                       BookRepository bookRepository,
                       MemberRepository memberRepository) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
    }

    // ISSUE BOOK — core business logic
    @Transactional
    public Loan issueBook(Long memberId, Long bookId, LocalDate dueDate) {

        // 1. fetch member — throws if not found
        Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new ResourceNotFoundException(
                "Member not found with id: " + memberId));

        // 2. check member is not blocked
        if (member.getStatus().equals("BLOCKED")) {
            throw new RuntimeException(
                "Member is blocked and cannot borrow books");
        }

        // 3. fetch book — throws if not found
        Book book = bookRepository.findById(bookId)
            .orElseThrow(() -> new ResourceNotFoundException(
                "Book not found with id: " + bookId));

        // 4. check book is active
        if (book.getStatus().equals("INACTIVE")) {
            throw new RuntimeException("Book is not available");
        }

        // 5. check copies available — core business rule
        if (book.getCopiesAvailable() <= 0) {
            throw new RuntimeException(
                "No copies available for book: " + book.getTitle());
        }

        // 6. decrease available copies
        book.setCopiesAvailable(book.getCopiesAvailable() - 1);
        bookRepository.save(book);

        // 7. create loan record
        Loan loan = new Loan();
        loan.setMember(member);
        loan.setBook(book);
        loan.setIssueDate(LocalDate.now());
        loan.setDueDate(dueDate);
        loan.setLoanStatus("ISSUED");

        return loanRepository.save(loan);
    }

    // RETURN BOOK — core business logic
    @Transactional
    public Loan returnBook(Long loanId) {

        // 1. fetch loan — throws if not found
        Loan loan = loanRepository.findById(loanId)
            .orElseThrow(() -> new ResourceNotFoundException(
                "Loan not found with id: " + loanId));

        // 2. check loan is not already returned
        if (loan.getLoanStatus().equals("RETURNED")) {
            throw new RuntimeException("Book already returned for loan: " + loanId);
        }

        // 3. set return date
        loan.setReturnDate(LocalDate.now());

        // 4. check if late — compare return date with due date
        if (LocalDate.now().isAfter(loan.getDueDate())) {
            loan.setLoanStatus("LATE");
        } else {
            loan.setLoanStatus("RETURNED");
        }

        // 5. increase available copies back
        Book book = loan.getBook();
        book.setCopiesAvailable(book.getCopiesAvailable() + 1);
        bookRepository.save(book);

        return loanRepository.save(loan);
    }

    // READ by ID
    public Loan getLoanById(Long id) {
        return loanRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                "Loan not found with id: " + id));
    }

    // READ all loans
    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    // READ loans by member
    public List<Loan> getLoansByMember(Long memberId) {
        return loanRepository.findByMemberMemberId(memberId);
    }

    // READ overdue loans
    public List<Loan> getOverdueLoans() {
        return loanRepository.findOverdueLoans();
    }
}