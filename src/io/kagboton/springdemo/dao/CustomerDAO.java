package io.kagboton.springdemo.dao;

import io.kagboton.springdemo.entity.Customer;

import java.util.List;

public interface CustomerDAO {
     List<Customer> getCustomers();
}
