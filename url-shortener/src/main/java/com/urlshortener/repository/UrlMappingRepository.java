package com.urlshortener.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.urlshortener.entity.UrlMapping;

@Repository
public interface UrlMappingRepository extends JpaRepository<UrlMapping, Long> {

    // find by short code
    Optional<UrlMapping> findByShortCode(String shortCode);

    // check if short code exists — used for collision detection
    boolean existsByShortCode(String shortCode);

    // find all created after a date
    List<UrlMapping> findByCreatedAtAfter(LocalDateTime date);

    // top 5 most clicked — custom JPQL
    @Query("SELECT u FROM UrlMapping u ORDER BY u.clickCount DESC LIMIT 5")
    List<UrlMapping> findTop5ByOrderByClickCountDesc();
}