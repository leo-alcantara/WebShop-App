package the.bug.web_shop_system.data;

import the.bug.web_shop_system.model.Customer;

import java.util.Collection;
import java.util.Set;

public class CustomerDAORepository implements CustomerDAO {

    @Override
    public Set<Customer> findCustomerByName(String customerName) {
        return null;
    }

    @Override
    public Customer findCustomerByEmail(String customerEmail) {
        return null;
    }

    @Override
    public Customer findCustomerByUsername(String customerUsername) {
        return null;
    }

    @Override
    public Customer create(Customer customer) {
        return null;
    }

    @Override
    public Customer delete(Customer customer) {
        return null;
    }

    @Override
    public Collection<Customer> findAll() {
        return null;
    }

    @Override
    public Customer findById(String s) {
        return null;
    }

    @Override
    public Customer update(Customer customer) {
        return null;
    }

    @Override
    public void clear() {

    }
}
