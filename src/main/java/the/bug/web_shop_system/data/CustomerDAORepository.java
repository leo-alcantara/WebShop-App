package the.bug.web_shop_system.data;

import org.springframework.stereotype.Repository;
import the.bug.web_shop_system.exceptions.ExceptionManager;
import the.bug.web_shop_system.model.Customer;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Repository
public class CustomerDAORepository implements CustomerDAO {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public Customer create(Customer customer) throws ExceptionManager {
        if (customer == null) {
            throw new ExceptionManager("Customer can not be null.");
        }
        entityManager.persist(customer);
        return customer;
    }

    @Override
    @Transactional
    public Customer delete(Customer customer) throws ExceptionManager {
        if (customer == null) {
            throw new ExceptionManager("Customer can not be null.");
        }
        entityManager.remove(customer);
        return customer;
    }

    @Override
    public List<Customer> findAll() {
        return entityManager.createQuery("SELECT c FROM Customer c", Customer.class)
                .getResultList();
    }

    @Override
    public Customer findById(String customerId) throws ExceptionManager {
        if (customerId == null) {
            throw new ExceptionManager("Customer id can not be null.");
        }
        return entityManager.find(Customer.class, customerId);
    }

    @Override
    public Customer update(Customer customer) throws ExceptionManager {
        if (customer == null) {
            throw new ExceptionManager("Customer can not be null.");
        }
        return entityManager.merge(customer);
    }

    @Override
    public void clear() {
        entityManager.clear();
    }

    @Override
    public List<Customer> findCustomerByName(String customerName) throws ExceptionManager {
        if (customerName == null) {
            throw new ExceptionManager("Customer name can not be null.");
        }
        return entityManager.createQuery( "SELECT c FROM Customer c WHERE UPPER(c.firstName) LIKE UPPER(CONCAT('%',:customerName,'%')) " +
        "OR " + "UPPER(c.lastName) LIKE UPPER(CONCAT('%', :customerName, '%')) ", Customer.class)
                .setParameter("customerName", customerName)
                .getResultList();
    }

    @Override
    public Customer findCustomerByEmail(String customerEmail) throws ExceptionManager {
        if (customerEmail == null) {
            throw new ExceptionManager("Customer email can not be null.");
        }
        Optional<Customer> customer = entityManager.createQuery("SELECT c FROM Customer c WHERE c.email = ?1", Customer.class)
                .setParameter(1, customerEmail).getResultStream().findFirst();
        return customer.orElseThrow(()-> new ExceptionManager("Customer not found"));
    }

    @Override
    public Customer findCustomerByUsername(String customerUsername) throws ExceptionManager {
        if (customerUsername == null) {
            throw new ExceptionManager("Customer username can not be null.");
        }
        Optional<Customer> customer = entityManager.createQuery("SELECT c FROM Customer c WHERE c.userCredentials.username = ?1", Customer.class)
                .setParameter(1, customerUsername).getResultStream().findFirst();
        return customer.orElseThrow(()-> new ExceptionManager("Customer not found"));
    }
}
