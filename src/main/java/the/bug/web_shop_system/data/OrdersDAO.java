package the.bug.web_shop_system.data;

import the.bug.web_shop_system.model.Orders;

import java.util.List;

public interface OrdersDAO extends GenericCRUDMethods<Orders, String>{

    List<Orders> findOrdersByCustomer (String customerEmail);

}
