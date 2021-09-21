package the.bug.web_shop_system.data;

import org.springframework.stereotype.Repository;
import the.bug.web_shop_system.exceptions.ExceptionManager;
import the.bug.web_shop_system.model.ProductCategory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductCategoryDAORepository implements ProductCategoryDAO {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public ProductCategory create(ProductCategory productCategory) throws ExceptionManager {
        if (productCategory == null) {
            throw new ExceptionManager("Product can not be null.");
        }
        entityManager.persist(productCategory);
        return productCategory;
    }

    @Override
    @Transactional
    public ProductCategory delete(ProductCategory productCategory) throws ExceptionManager {
        if (productCategory == null) {
            throw new ExceptionManager("Product can not be null.");
        }
        entityManager.remove(productCategory);
        return productCategory;
    }

    @Override
    @Transactional
    public List<ProductCategory> findAll() {
        return entityManager.createQuery("SELECT pc FROM ProductCategory pc", ProductCategory.class).getResultList();
    }

    @Override
    @Transactional
    public ProductCategory findById(String productCategoryId) throws ExceptionManager {
        if (productCategoryId == null) {
            throw new ExceptionManager("Product Category can not be null.");
        }
        return entityManager.find(ProductCategory.class, productCategoryId);
    }

    @Override
    @Transactional
    public ProductCategory update(ProductCategory productCategory) throws ExceptionManager {
        if (productCategory == null) {
            throw new ExceptionManager("Product Category can not be null.");
        }
        return entityManager.merge(productCategory);
    }

    @Override
    @Transactional
    public void clear() {
        entityManager.clear();
    }

    @Override
    @Transactional
    public ProductCategory findProductCategoryByName(String productCategoryByName) throws ExceptionManager {
        if (productCategoryByName == null) {
            throw new ExceptionManager("Product Category can not be null.");
        }
        Optional<ProductCategory> productCategory = entityManager.createQuery("SELECT pc FROM ProductCategory pc WHERE pc.productCategoryName = ?1", ProductCategory.class)
                .setParameter(1, productCategoryByName).getResultStream().findFirst();
        return productCategory.orElseThrow(() -> new ExceptionManager("Product Category not found"));
    }

}