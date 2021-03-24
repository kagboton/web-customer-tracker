package io.kagboton.springdemo.service;

import io.kagboton.springdemo.entity.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> getCustomers(int theSortField);
    void saveCustomer(Customer customer);
    Customer getCustomerById(int id);
    void deleteCustomer(int theId);
    List<Customer> searchCustomers(String searchName);
}
