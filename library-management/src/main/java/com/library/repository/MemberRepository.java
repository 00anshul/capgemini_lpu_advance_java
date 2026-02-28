package com.library.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.library.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    // find member by email — unique field
    Optional<Member> findByEmail(String email);

    // find all members by status
    List<Member> findByStatus(String status);

    // check if email already registered
    boolean existsByEmail(String email);
}