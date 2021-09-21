package the.bug.web_shop_system.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import the.bug.web_shop_system.model.Customer;
import the.bug.web_shop_system.model.OrderItem;
import the.bug.web_shop_system.model.Orders;
import static org.junit.jupiter.api.Assertions.*;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@SpringBootTest
@AutoConfigureTestEntityManager
@AutoConfigureTestDatabase
@Transactional
class OrdersDAORepositoryTest {

    @Autowired
    private OrdersDAO ordersDAO;
    @Autowired
    TestEntityManager testEntityManager;

    private Orders testOrders;
    private Customer customer;
    private Set<OrderItem> orderItems;


    @BeforeEach
    void setUp() {
        orderItems = new HashSet<>();
        customer = testEntityManager.persist(new Customer(null, "Denzel", "Washington", "denzel@email.com",
                null, null));
        testOrders = testEntityManager.persist(new Orders(null, null, null, null, orderItems, customer));
    }

    @Test
    void findOrdersByCustomer() {
        //Arrange
        List<Orders> foundByCustomer;

        //Act
        foundByCustomer = ordersDAO.findOrdersByCustomer(customer.getEmail());

        //Assert
        assertNotNull(foundByCustomer);
        assertEquals(1, foundByCustomer.size());
    }
}