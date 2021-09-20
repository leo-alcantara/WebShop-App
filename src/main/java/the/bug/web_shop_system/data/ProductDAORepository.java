package the.bug.web_shop_system.data;

import org.springframework.stereotype.Repository;
import the.bug.web_shop_system.exceptions.ExceptionManager;
import the.bug.web_shop_system.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Collection;


@Repository
public class ProductDAORepository implements ProductDAO {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    //Create method persists a product and returns the persisted product
    public Product create(Product product) throws ExceptionManager {
        if (product == null) {
            throw new ExceptionManager("Product can not be null.");
        }
        entityManager.persist(product);
        return product;
    }

    @Override
    @Transactional
    //Delete method deletes a product and returns the deleted product
    public Product delete(Product product) throws ExceptionManager {
        if (product == null) {
            throw new ExceptionManager("Product can not be null.");
        }
        entityManager.remove(product);
        return product;
    }

    @Override
    @Transactional
    //Find All method finds all products and returns a collection with all the found products
    public Collection<Product> findAll() {
        return entityManager.createQuery("SELECT p FROM Product p").getResultList();
    }

    @Override
    @Transactional
    //Find by id method finds a method using its id and returns the found product
    public Product findById(String productId) {
        return entityManager.find(Product.class, productId);
    }

    @Override
    @Transactional
    //Update method updates a product's attributes and returns the updated product
    public Product update(Product product) {
        return entityManager.merge(product);
    }

    @Override
    @Transactional
    //Clear method clears the DB table
    public void clear() {
        entityManager.clear();
    }

    @Override
    @Transactional
    //Find Product By Name method finds products with a certain name and returns a collection of products containing that name
    public Collection<Product> findProductByName(String productName) throws ExceptionManager {
        if(productName==null){
            throw new ExceptionManager("Product name can not be null.");
        }
        return entityManager.createQuery("SELECT p FROM Product p WHERE p.productName = ?1", Product.class)
                .setParameter(1, productName).getResultList();
    }

    @Override
    @Transactional
    //Find Product By Price Between method finds products inside a range of price and returns a collection with all the found products
    public Collection<Product> findProductByPriceBetween(BigDecimal priceLow, BigDecimal priceHigh) throws ExceptionManager {
        if(priceLow==null || priceHigh==null){
            throw new ExceptionManager("Parameters can not be null.");
        }
        return entityManager.createQuery("SELECT p FROM Product p WHERE p.productPrice BETWEEN ?1 AND ?2", Product.class)
                .setParameter(1,priceLow)
                .setParameter(2, priceHigh)
                .getResultList();
    }

    @Override
    @Transactional
    //Find Product By Category method finds all the products in a determined category and returns a collection of all found products
    public Collection<Product> findProductByCategory(String categoryName) throws ExceptionManager {
        if(categoryName==null){
            throw new ExceptionManager("parameters can not be null.");
        }
        return entityManager.createQuery("SELECT p FROM Product p JOIN FETCH p.productCategories AS pc WHERE pc.productCategoryName = ?1", Product.class)
                .setParameter(1, categoryName).getResultList();

    }
}
