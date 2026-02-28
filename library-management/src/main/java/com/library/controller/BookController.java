package com.library.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.library.entity.Book;
import com.library.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/books")
@Tag(name = "Book Management")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // POST /api/v1/books?categoryId=1&branchId=1&authorIds=1,2
    @PostMapping
    @Operation(summary = "Add a new book")
    public ResponseEntity<Book> addBook(
            @RequestBody Book book,
            @RequestParam Long categoryId,
            @RequestParam Long branchId,
            @RequestParam List<Long> authorIds) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(bookService.addBook(book, categoryId,
                                                       branchId, authorIds));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get book by ID")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @GetMapping
    @Operation(summary = "Get all books")
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/search")
    @Operation(summary = "Search books by title keyword")
    public ResponseEntity<List<Book>> searchBooks(@RequestParam String keyword) {
        return ResponseEntity.ok(bookService.searchBooks(keyword));
    }

    @GetMapping("/category/{categoryId}")
    @Operation(summary = "Get books by category")
    public ResponseEntity<List<Book>> getBooksByCategory(
            @PathVariable Long categoryId) {
        return ResponseEntity.ok(bookService.getBooksByCategory(categoryId));
    }

    @GetMapping("/branch/{branchId}")
    @Operation(summary = "Get books by branch")
    public ResponseEntity<List<Book>> getBooksByBranch(
            @PathVariable Long branchId) {
        return ResponseEntity.ok(bookService.getBooksByBranch(branchId));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update book")
    public ResponseEntity<Book> updateBook(@PathVariable Long id,
                                           @RequestBody Book book) {
        return ResponseEntity.ok(bookService.updateBook(id, book));
    }

    @PutMapping("/{id}/disable")
    @Operation(summary = "Disable a book")
    public ResponseEntity<Book> disableBook(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.disableBook(id));
    }
}