package com.example.orders.controller;

import com.example.orders.model.Customer;
import com.example.orders.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> findAll() {
        return customerService.findAll();
    }

    @PostMapping
    public int save(@RequestBody Customer customer) {
        return customerService.save(customer);
    }
}
