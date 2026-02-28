package com.library.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "loan")
public class Loan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long loanId;

	@Column(nullable = false)
	private LocalDate issueDate;

	@Column(nullable = false)
	private LocalDate dueDate;

	// null until book is actually returned
	private LocalDate returnDate;

	@Column(nullable = false)
	private String loanStatus; // ISSUED, RETURNED, LATE

	// OWNING side — loan table gets member_id FK column
	@ManyToOne
	@JoinColumn(name = "member_id", nullable = false)
	private Member member;

	// OWNING side — loan table gets book_id FK column
	@ManyToOne
	@JoinColumn(name = "book_id", nullable = false)
	private Book book;

	public Loan() {
	}

	public Long getLoanId() {
		return loanId;
	}

	public void setLoanId(Long loanId) {
		this.loanId = loanId;
	}

	public LocalDate getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(LocalDate issueDate) {
		this.issueDate = issueDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	public String getLoanStatus() {
		return loanStatus;
	}

	public void setLoanStatus(String loanStatus) {
		this.loanStatus = loanStatus;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	@Override
	public String toString() {
		return "Loan [loanId=" + loanId + ", issueDate=" + issueDate + ", dueDate=" + dueDate + ", returnDate="
				+ returnDate + ", loanStatus=" + loanStatus + "]";
	}
}