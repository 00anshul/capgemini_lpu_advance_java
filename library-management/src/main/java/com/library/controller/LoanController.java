package com.library.controller;

import java.time.LocalDate;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.library.entity.Loan;
import com.library.service.LoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/loans")
@Tag(name = "Loan Management")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    // POST /api/v1/loans/issue?memberId=1&bookId=1&dueDate=2025-12-31
    @PostMapping("/issue")
    @Operation(summary = "Issue a book to a member")
    public ResponseEntity<Loan> issueBook(
            @RequestParam Long memberId,
            @RequestParam Long bookId,
            @RequestParam LocalDate dueDate) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(loanService.issueBook(memberId,
                                                         bookId, dueDate));
    }

    // PUT /api/v1/loans/1/return
    @PutMapping("/{loanId}/return")
    @Operation(summary = "Return a borrowed book")
    public ResponseEntity<Loan> returnBook(@PathVariable Long loanId) {
        return ResponseEntity.ok(loanService.returnBook(loanId));
    }

    @GetMapping("/{loanId}")
    @Operation(summary = "Get loan by ID")
    public ResponseEntity<Loan> getLoanById(@PathVariable Long loanId) {
        return ResponseEntity.ok(loanService.getLoanById(loanId));
    }

    @GetMapping
    @Operation(summary = "Get all loans")
    public ResponseEntity<List<Loan>> getAllLoans() {
        return ResponseEntity.ok(loanService.getAllLoans());
    }

    @GetMapping("/overdue")
    @Operation(summary = "Get all overdue loans")
    public ResponseEntity<List<Loan>> getOverdueLoans() {
        return ResponseEntity.ok(loanService.getOverdueLoans());
    }

    // GET /api/v1/members/1/loans
    @GetMapping("/member/{memberId}")
    @Operation(summary = "Get loan history for a member")
    public ResponseEntity<List<Loan>> getLoansByMember(
            @PathVariable Long memberId) {
        return ResponseEntity.ok(loanService.getLoansByMember(memberId));
    }
}