package com.library.service;

import java.util.List;
import java.util.Set;
import java.util.HashSet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.library.entity.Author;
import com.library.entity.Book;
import com.library.entity.Category;
import com.library.entity.LibraryBranch;
import com.library.exception.ResourceNotFoundException;
import com.library.repository.AuthorRepository;
import com.library.repository.BookRepository;
import com.library.repository.CategoryRepository;
import com.library.repository.LibraryBranchRepository;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final LibraryBranchRepository branchRepository;
    private final AuthorRepository authorRepository;

    // constructor injection — all 4 repositories injected
    public BookService(BookRepository bookRepository,
                       CategoryRepository categoryRepository,
                       LibraryBranchRepository branchRepository,
                       AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
        this.branchRepository = branchRepository;
        this.authorRepository = authorRepository;
    }

    // CREATE — needs categoryId, branchId, and list of authorIds
    @Transactional
    public Book addBook(Book book, Long categoryId, 
                        Long branchId, List<Long> authorIds) {

        // fetch and set category — throws if not found
        Category category = categoryRepository.findById(categoryId)
            .orElseThrow(() -> new ResourceNotFoundException(
                "Category not found with id: " + categoryId));
        book.setCategory(category);

        // fetch and set branch — throws if not found
        LibraryBranch branch = branchRepository.findById(branchId)
            .orElseThrow(() -> new ResourceNotFoundException(
                "Branch not found with id: " + branchId));
        book.setBranch(branch);

        // fetch and set authors — build a set from list of IDs
        Set<Author> authors = new HashSet<>();
        for (Long authorId : authorIds) {
            Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Author not found with id: " + authorId));
            authors.add(author);
        }
        book.setAuthors(authors);

        // set defaults
        book.setStatus("ACTIVE");
        book.setCopiesAvailable(book.getCopiesTotal());

        return bookRepository.save(book);
    }

    // READ by ID
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                "Book not found with id: " + id));
    }

    // READ all
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // READ by category
    public List<Book> getBooksByCategory(Long categoryId) {
        return bookRepository.findByCategoryCategoryId(categoryId);
    }

    // READ by branch
    public List<Book> getBooksByBranch(Long branchId) {
        return bookRepository.findByBranchBranchId(branchId);
    }

    // SEARCH by title keyword
    public List<Book> searchBooks(String keyword) {
        return bookRepository.searchByTitle(keyword);
    }

    // UPDATE
    @Transactional
    public Book updateBook(Long id, Book updatedBook) {
        Book existing = getBookById(id);
        existing.setTitle(updatedBook.getTitle());
        existing.setIsbn(updatedBook.getIsbn());
        existing.setPublishYear(updatedBook.getPublishYear());
        existing.setCopiesTotal(updatedBook.getCopiesTotal());
        return bookRepository.save(existing);
    }

    // DISABLE — soft delete, don't actually remove from DB
    @Transactional
    public Book disableBook(Long id) {
        Book existing = getBookById(id);
        existing.setStatus("INACTIVE");
        return bookRepository.save(existing);
    }
}