package com.library.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.library.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    // find by ISBN — should be unique
    Optional<Book> findByIsbn(String isbn);

    // find all books by status
    List<Book> findByStatus(String status);

    // find all books in a category
    List<Book> findByCategoryCategoryId(Long categoryId);

    // find all books in a branch
    List<Book> findByBranchBranchId(Long branchId);

    // find books with available copies
    List<Book> findByCopiesAvailableGreaterThan(int count);

    // custom JPQL — search by title keyword
    @Query("SELECT b FROM Book b WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Book> searchByTitle(@Param("keyword") String keyword);
}