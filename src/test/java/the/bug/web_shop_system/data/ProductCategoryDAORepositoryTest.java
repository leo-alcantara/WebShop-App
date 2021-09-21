package the.bug.web_shop_system.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import the.bug.web_shop_system.model.Product;
import the.bug.web_shop_system.model.ProductCategory;

import javax.transaction.Transactional;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestEntityManager
@AutoConfigureTestDatabase
@Transactional
class ProductCategoryDAORepositoryTest {

    @Autowired
    private ProductCategoryDAO productCategoryDAO;
    @Autowired
    TestEntityManager testEntityManager;

    private ProductCategory testProductCategory;
    private Set<Product> products;

    @BeforeEach
    void setUp() {
        products = new HashSet<>();
        testProductCategory = testEntityManager.persist(new ProductCategory(null, "Toys", products));
    }

    @Test
    void findProductCategoryByName() {
        //Arrange
        ProductCategory foundProductCategory;

        //Act
        foundProductCategory = productCategoryDAO.findProductCategoryByName(testProductCategory.getProductCategoryName());

        //Assert
        assertNotNull(foundProductCategory);
        assertEquals(foundProductCategory.getProductCategoryName(), testProductCategory.getProductCategoryName());
        assertNotNull(foundProductCategory.getProductCategoryId());
    }
}