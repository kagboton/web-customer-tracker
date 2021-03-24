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

    @Override
    public void deleteCustomer(int theId) {
        // get the current hibernate section
        Session session = sessionFactory.getCurrentSession();

        // retrieve the customer from the db using the PK
       // Customer theCustomer = session.get(Customer.class, theId);

        // delete the customer
      //  session.delete(theCustomer);

        // or
        // use HQL
        Query theQuery =
                session.createQuery("delete from Customer where id=:customerId");
        theQuery.setParameter("customerId", theId);

        theQuery.executeUpdate();
    }

    @Override
    public List<Customer> searchCustomers(String searchName) {
        // get current session from the factory
        Session session = sessionFactory.getCurrentSession();

        Query theQuery = null;

        // search by name only if searchName is not empty
        if (searchName != null && searchName.trim().length() > 0){
            // use HQL to fetch customer by the searchName
            theQuery = session.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName");
            theQuery.setParameter("theName", "%" + searchName.toLowerCase() + "%");
        }else {
            theQuery = session.createQuery("from Customer order by lastName", Customer.class);
        }

        List<Customer> customers = theQuery.getResultList();
      return customers;
    }
}
