package com.udemy.DAO;

import com.udemy.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDAOImplementation implements CustomerDAO {

    // need to inject the session factory
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Customer> getCustomers() {

        // get the current hibernate session
        // create a query
        // execute query and get result list
        // return the results

        Session currentSession = sessionFactory.openSession();
        Query<Customer> query = currentSession.createQuery("from Customer order by lastName", Customer.class);

        return query.getResultList();
    }

    @Override
    public void saveCustomer(Customer customer) {
        // get current hibernate session
        // save/update the customer

        Session currentSession = sessionFactory.openSession();
        currentSession.saveOrUpdate(customer);
    }

    @Override
    public Customer getCustomer(int theId) {
        // get the current hibernate session
        // now retrieve/read from database using the primary key

        Session currentSession = sessionFactory.openSession();
        return currentSession.get(Customer.class, theId);
    }
}
