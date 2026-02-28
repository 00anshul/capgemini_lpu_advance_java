package com.library.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.library.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    // Spring generates SELECT * FROM author WHERE name = ?
    Optional<Author> findByName(String name);

    // Spring generates SELECT * FROM author WHERE name LIKE %?%
    List<Author> findByNameContainingIgnoreCase(String keyword);
}