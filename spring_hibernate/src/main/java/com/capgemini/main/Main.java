package com.capgemini.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.capgemini.ProjectConfig;
import com.capgemini.dto.Employee;
import com.capgemini.service.EmployeeService;

public class Main {

    public static void main(String[] args) {

        ApplicationContext ioc =
                new AnnotationConfigApplicationContext(ProjectConfig.class);

        Employee e1 = ioc.getBean(Employee.class);
        Employee e2 = ioc.getBean(Employee.class);

        e1.setId(1);
        e1.setName("John");
        e1.setSalary(50000);

        e2.setName("Dom");

        System.out.println(e1);
        System.out.println(e2);

        System.out.println(e1 == e2);   // false (prototype scope)

        // Database insert
        EmployeeService service = ioc.getBean(EmployeeService.class);
        service.insertEmployee(e1);
    }
}