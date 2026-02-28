package com.library.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.library.entity.LibraryBranch;

@Repository
public interface LibraryBranchRepository extends JpaRepository<LibraryBranch, Long> {

    // find branches in a specific city/location
    List<LibraryBranch> findByLocationContainingIgnoreCase(String location);

    // check if branch name exists
    boolean existsByName(String name);
}