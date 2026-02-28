package com.library.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.library.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    // find category by exact name
    Optional<Category> findByName(String name);

    // check if category name already exists
    boolean existsByName(String name);
}