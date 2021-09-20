package the.bug.web_shop_system.data;

import the.bug.web_shop_system.model.Product;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Set;

public interface ProductDAO extends GenericCRUDMethods<Product, String>{

    Collection<Product> findProductByName (String productName);

    Collection<Product> findProductByPriceBetween (BigDecimal priceLow, BigDecimal priceHigh);

    Collection<Product> findProductByCategory (String categoryName);

}
