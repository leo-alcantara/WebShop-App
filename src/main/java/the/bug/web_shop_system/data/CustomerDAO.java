package the.bug.web_shop_system.data;

import the.bug.web_shop_system.model.Customer;

import java.util.List;


public interface CustomerDAO extends GenericCRUDMethods<Customer, String>{

    List<Customer> findCustomerByName (String customerName);

    Customer findCustomerByEmail (String customerEmail);

    Customer findCustomerByUsername(String customerUsername);
}
