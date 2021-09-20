package the.bug.web_shop_system.data;

import the.bug.web_shop_system.model.Customer;

import java.util.Set;

public interface CustomerDAO extends GenericCRUDMethods<Customer, String>{

    Set<Customer> findCustomerByName (String customerName);

    Customer findCustomerByEmail (String customerEmail);

    Customer findCustomerByUsername(String customerUsername);
}
