package the.bug.web_shop_system.data;

import the.bug.web_shop_system.model.Product;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Set;

public class ProductDAORepository implements ProductDAO {

    @Override
    public Product create(Product product) {
        return null;
    }

    @Override
    public Product delete(Product product) {
        return null;
    }

    @Override
    public Collection<Product> findAll() {
        return null;
    }

    @Override
    public Product findById(String s) {
        return null;
    }

    @Override
    public Product update(Product product) {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public Product findProductByName(String productName) {
        return null;
    }

    @Override
    public Set<Product> findProductByPriceBetween(BigDecimal priceLow, BigDecimal priceHigh) {
        return null;
    }

    @Override
    public Set<Product> findProductByCategory(String categoryName) {
        return null;
    }
}
