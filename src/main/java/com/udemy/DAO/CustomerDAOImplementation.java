package com.udemy.DAO;

import com.udemy.entity.Customer;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CustomerDAOImplementation implements CustomerDAO {

    // need to inject the session factory --> session factory is in conflict with the one
    // created by spring boot, so use EntityManager instead
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Customer> getCustomers() {

        // get the current hibernate session
        // create a query
        // execute query and get result list
        // return the results

        Session currentSession = entityManager.unwrap(Session.class);
        Query<Customer> query = currentSession.createQuery("from Customer order by lastName", Customer.class);

        return query.getResultList();
    }

    @Override
    public void saveCustomer(Customer customer) {
        // get current hibernate session
        // save/update the customer

        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(customer);
    }

    @Override
    public Customer getCustomer(int theId) {
        // get the current hibernate session
        // now retrieve/read from database using the primary key

        Session currentSession = entityManager.unwrap(Session.class);
        return currentSession.get(Customer.class, theId);
    }

    @Override
    public void deleteCustomer(int theId) {
        // get the current hibernate session
        // delete object with primary key

        Session currentSession = entityManager.unwrap(Session.class);
        Query<Customer> query = currentSession.createQuery("delete from Customer where id=:customerId", Customer.class);
        query.setParameter("customerId", theId);
        query.executeUpdate();
    }
}
