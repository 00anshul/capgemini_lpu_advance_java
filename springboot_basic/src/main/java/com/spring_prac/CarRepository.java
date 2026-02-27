package com.spring_prac;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    // JpaRepository gives you save(), findById(), findAll(), delete() for free!
    // First generic = Entity type, Second = ID type
	public Car getByPrice(double price);
	
	@Modifying
	@Transactional
	@Query("delete  from Car c where c.brand =:carbrand")
	public void deleteByBrand(@Param("carbrand")  String brand);
}