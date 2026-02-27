package com.spring_prac.customer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")  // ← all URLs start with /customers
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepo;

    // CREATE - POST http://localhost:8080/customers
    @PostMapping
    public Customer addCustomer(@RequestBody Customer c) {
        return customerRepo.save(c);
    }

    // GET ALL - GET http://localhost:8080/customers
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    // GET BY ID - GET http://localhost:8080/customers/1
    @GetMapping("/{id}")
    public Customer getById(@PathVariable int id) {
        Optional<Customer> opt = customerRepo.findById(id);
        return opt.orElse(null);  // returns null if not found
    }

    // GET BY CITY - GET http://localhost:8080/customers/city/Mumbai
    @GetMapping("/city/{city}")
    public List<Customer> getByCity(@PathVariable String city) {
        return customerRepo.findByCity(city);
    }

    // GET BY EMAIL - GET http://localhost:8080/customers/email/john@gmail.com
    @GetMapping("/email/{email}")
    public Customer getByEmail(@PathVariable String email) {
        return customerRepo.findByEmail(email);
    }

    // GET RICH CUSTOMERS - GET http://localhost:8080/customers/rich/50000
    @GetMapping("/rich/{amount}")
    public List<Customer> getRichCustomers(@PathVariable double amount) {
        return customerRepo.findByBalanceGreaterThan(amount);
    }

    // SEARCH BY NAME - GET http://localhost:8080/customers/search/john
    @GetMapping("/search/{keyword}")
    public List<Customer> searchByName(@PathVariable String keyword) {
        return customerRepo.findByNameContaining(keyword);
    }

    // UPDATE - PUT http://localhost:8080/customers/1
    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable int id, @RequestBody Customer updated) {
        updated.setId(id);  // make sure the ID stays the same
        return customerRepo.save(updated);
    }

    // DELETE - DELETE http://localhost:8080/customers/1
    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable int id) {
        customerRepo.deleteById(id);
        return "Customer " + id + " deleted";
    }
}