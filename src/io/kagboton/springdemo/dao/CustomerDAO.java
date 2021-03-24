package io.kagboton.springdemo.dao;

import io.kagboton.springdemo.entity.Customer;

import java.util.List;

public interface CustomerDAO {
     List<Customer> getCustomers();
     void saveCustomer(Customer customer);
     Customer getCustomerById(int customerId);
     void deleteCustomer(int theId);
     List<Customer> searchCustomers(String searchName);
}
