package com.spring_prac.customer;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    // Spring auto-generates all these queries just from the method name!
    Customer findByEmail(String email);
    List<Customer> findByCity(String city);
    List<Customer> findByBalanceGreaterThan(double balance);
    List<Customer> findByNameContaining(String keyword);
}