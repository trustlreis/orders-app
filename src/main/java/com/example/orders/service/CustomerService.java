package com.example.orders.service;

import com.example.orders.model.Customer;
import com.example.orders.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public int save(Customer customer) {
        return customerRepository.save(customer);
    }
}
