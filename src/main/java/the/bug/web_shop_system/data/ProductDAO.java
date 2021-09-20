package the.bug.web_shop_system.data;

import the.bug.web_shop_system.model.Product;

import java.math.BigDecimal;
import java.util.Set;

public interface ProductDAO extends GenericCRUDMethods<Product, String>{

    Product findProductByName (String productName);

    Set<Product> findProductByPriceBetween (BigDecimal priceLow, BigDecimal priceHigh);

    Set<Product> findProductByCategory (String categoryName);

}
