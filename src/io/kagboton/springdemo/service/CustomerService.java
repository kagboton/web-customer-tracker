package io.kagboton.springdemo.service;

import io.kagboton.springdemo.entity.Customer;

import java.util.List;

public interface CustomerService {

    public List<Customer> getCustomers();

    void saveCustomer(Customer customer);
}
