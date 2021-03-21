package io.kagboton.springdemo.dao;

import io.kagboton.springdemo.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    // inject the session factory
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<Customer> getCustomers() {
        // get the current hibernate session
        Session session = sessionFactory.getCurrentSession();

        // create the query
        Query<Customer> theQuery = session.createQuery("from Customer", Customer.class);

        // execute the query and get the result
        List<Customer> customers = theQuery.getResultList();

        // return the result
        return customers;
    }
}
