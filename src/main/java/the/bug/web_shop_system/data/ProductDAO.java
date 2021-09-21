package the.bug.web_shop_system.data;

import the.bug.web_shop_system.model.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductDAO extends GenericCRUDMethods<Product, String>{

    List<Product> findProductByName (String productName);

    List<Product> findProductByPriceBetween (BigDecimal priceLow, BigDecimal priceHigh);

    List<Product> findProductByCategory (String categoryName);

}
