package com.capgemini.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.dao.EmployeeDAO;
import com.capgemini.dto.Employee;

@Service
public class EmployeeService {

    @Autowired
    EmployeeDAO dao;

    public void insertEmployee(Employee e) {

        if (dao.findbyID(e.getId()) == null) {
            dao.insert(e);
        } else {
            System.out.println("Employee already exists");
        }
    }
}