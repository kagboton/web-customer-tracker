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
    public List<Customer> getCustomers() {
        // get the current hibernate session
        Session session = sessionFactory.getCurrentSession();

        // create the query
        Query<Customer> theQuery = session.createQuery("from Customer order by lastName",
                Customer.class);

        // execute the query and get the result
        List<Customer> customers = theQuery.getResultList();

        // return the result
        return customers;
    }

    @Override
    public void saveCustomer(Customer customer) {
        // get the current hibernate session
        Session session = sessionFactory.getCurrentSession();

        // save or update the customer
        session.saveOrUpdate(customer);
    }

    @Override
    public Customer getCustomerById(int customerId) {
        // get the current hibernate session
        Session session = sessionFactory.getCurrentSession();

        // retrieve the customer from the db using the primary key
        Customer theCustomer = session.get(Customer.class, customerId);

        return theCustomer;
    }
}
