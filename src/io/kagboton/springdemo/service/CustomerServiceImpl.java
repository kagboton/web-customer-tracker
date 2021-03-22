package io.kagboton.springdemo.service;

import io.kagboton.springdemo.dao.CustomerDAO;
import io.kagboton.springdemo.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    // inject customer dao
    @Autowired
    private CustomerDAO customerDAO;

    @Override
    @Transactional
    public List<Customer> getCustomers(){

        // get customers from the dao
        return customerDAO.getCustomers();
    }
}