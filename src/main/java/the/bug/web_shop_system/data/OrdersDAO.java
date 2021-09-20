package the.bug.web_shop_system.data;

import the.bug.web_shop_system.model.Orders;

import java.util.Set;

public interface OrdersDAO extends GenericCRUDMethods<Orders, String>{

    Set<Orders> findOrdersByCustomer (String customerEmail);

}
