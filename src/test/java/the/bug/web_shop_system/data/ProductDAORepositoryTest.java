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

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@AutoConfigureTestEntityManager
@AutoConfigureTestDatabase
@Transactional
class ProductDAORepositoryTest {

    @Autowired
    private ProductDAO productDAO;
    @Autowired
    TestEntityManager testEntityManager;

    private Product testProduct;
    private BigDecimal low;
    private BigDecimal high;
    private Set<ProductCategory> categories;
    private Set<Product> products;
    private ProductCategory productCategory;

    @BeforeEach
    void setUp() {
        low = new BigDecimal(100);
        high = new BigDecimal(120);
        categories = new HashSet<>();
        products = new HashSet<>();
        productCategory = new ProductCategory(null, "Toys", products);
        categories.add(productCategory);
        testProduct = testEntityManager.persist(new Product(null, "He-man figure", "Toy Figure", new BigDecimal(110), categories));
    }

    @Test
    void findProductByName() {
        //Arrange
        Collection<Product> foundByName;

        //Act
        foundByName = productDAO.findProductByName(testProduct.getProductName());

        //Assert
        assertNotNull(foundByName);
        assertTrue(foundByName.contains(testProduct));
        assertEquals(1, foundByName.size());
    }

    @Test
    void findProductByPriceBetween() {
        //Arrange
        Collection<Product> foundByPriceBetween;
        //Act
        foundByPriceBetween = productDAO.findProductByPriceBetween(low, high);

        //Assert
        assertNotNull(foundByPriceBetween);
        assertTrue(foundByPriceBetween.contains(testProduct));
        assertEquals(1, foundByPriceBetween.size());
    }

    @Test
    void findProductByCategory() {
        //Arrange
        Collection<Product> foundByCategory;
        //Act
        foundByCategory = productDAO.findProductByCategory(productCategory.getProductCategoryName());

        //Assert
        assertNotNull(foundByCategory);
        assertEquals(1, foundByCategory.size());
    }
}