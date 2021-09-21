package the.bug.web_shop_system.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import the.bug.web_shop_system.model.Address;
import the.bug.web_shop_system.model.AppUser;
import the.bug.web_shop_system.model.Customer;
import static org.junit.jupiter.api.Assertions.*;

import javax.transaction.Transactional;

import java.util.List;


@SpringBootTest
@AutoConfigureTestEntityManager
@AutoConfigureTestDatabase
@Transactional
class CustomerDAORepositoryTest {

    @Autowired
    private CustomerDAO customerDAO;
    @Autowired
    TestEntityManager testEntityManager;

    private Customer testCustomer;

    private AppUser userCredentials;
    private Address address;

    @BeforeEach
    void setUp() {
        userCredentials = new AppUser(null, "DD", "1234");
        address = new Address(null, "Some Street 10", "10298", "Los Angeles", "USA");
        testCustomer = testEntityManager.persist(new Customer(null, "Denzel", "Washington", "denzel@email.com",
                userCredentials, address));
    }

    @Test
    void findCustomerByName() {
        //Arrange
        List<Customer> foundByName;

        //Act
        foundByName = customerDAO.findCustomerByName(testCustomer.getFirstName());

        //Assert
        assertTrue(foundByName.contains(testCustomer));
        assertEquals(1, foundByName.size());
        assertNotNull(foundByName);
    }

    @Test
    void findCustomerByEmail() {
        //Arrange
        Customer customer;

        //Act
        customer = customerDAO.findCustomerByEmail(testCustomer.getEmail());

        //Assert
        assertEquals(testCustomer, customer);
        assertNotNull(customer);
        assertEquals(testCustomer.getEmail(), customer.getEmail());
    }

    @Test
    void findCustomerByUsername() {
        //Arrange
        Customer customer;
        testCustomer.setUserCredentials(userCredentials);

        //Act
        customer = customerDAO.findCustomerByUsername(testCustomer.getUserCredentials().getUsername());

        //Assert
        assertNotNull(customer);
        assertEquals(testCustomer.getUserCredentials().getUsername(), customer.getUserCredentials().getUsername());
        assertEquals("DD", customer.getUserCredentials().getUsername());
    }
}