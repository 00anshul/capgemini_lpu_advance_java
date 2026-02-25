package com.capgemini.dao;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capgemini.dto.Employee;
import com.capgemini.main.JpaUtil;

@Repository
public class EmployeeDAO {

    @Autowired
    JpaUtil jpa;

    public void insert(Employee e) {

        EntityManager em = jpa.getEm();

        em.getTransaction().begin();

        Employee employee = findbyID(e.getId());

        if (employee == null) {     // Correct condition
            em.persist(e);
            System.out.println("Inserted Successfully");
        } else {
            System.out.println("Data already exists");
        }

        em.getTransaction().commit();
    }

    public Employee findbyID(int id) {
        return jpa.getEm().find(Employee.class, id);
    }
}