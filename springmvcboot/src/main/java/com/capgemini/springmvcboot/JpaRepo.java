package com.capgemini.springmvcboot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaRepo extends JpaRepository<Users, Integer> {

	
	 Users findByEmailAndPassword(String email, String password);
}

