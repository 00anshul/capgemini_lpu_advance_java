package com.library.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    @Column(nullable = false)
    private String title;

    @Column(unique = true)
    private String isbn;

    private int publishYear;
    private int copiesTotal;
    private int copiesAvailable;

    @Column(nullable = false)
    private String status;   // ACTIVE or INACTIVE

    // M:N OWNING side — Book defines the join table
    // HashSet avoids duplicate authors
    @ManyToMany
    @JoinTable(
        name = "book_author",
        joinColumns = @JoinColumn(name = "book_id"),
        inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Author> authors = new HashSet<>();

    // N:1 — Book references Category, category_id FK lives in book table
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    // N:1 — Book references LibraryBranch, branch_id FK lives in book table
    @ManyToOne
    @JoinColumn(name = "branch_id")
    private LibraryBranch branch;

    // 1:N INVERSE — Loan owns the FK, Book just reads
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Loan> loans = new ArrayList<>();

    public Book() {}

    public Long getBookId() { return bookId; }
    public void setBookId(Long bookId) { this.bookId = bookId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public int getPublishYear() { return publishYear; }
    public void setPublishYear(int publishYear) { this.publishYear = publishYear; }

    public int getCopiesTotal() { return copiesTotal; }
    public void setCopiesTotal(int copiesTotal) { this.copiesTotal = copiesTotal; }

    public int getCopiesAvailable() { return copiesAvailable; }
    public void setCopiesAvailable(int copiesAvailable) { this.copiesAvailable = copiesAvailable; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Set<Author> getAuthors() { return authors; }
    public void setAuthors(Set<Author> authors) { this.authors = authors; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }

    public LibraryBranch getBranch() { return branch; }
    public void setBranch(LibraryBranch branch) { this.branch = branch; }

    public List<Loan> getLoans() { return loans; }
    public void setLoans(List<Loan> loans) { this.loans = loans; }

    @Override
    public String toString() {
        return "Book [bookId=" + bookId + ", title=" + title +
               ", isbn=" + isbn + ", status=" + status +
               ", copiesAvailable=" + copiesAvailable + "]";
    }
}