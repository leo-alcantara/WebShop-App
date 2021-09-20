package the.bug.web_shop_system.data;

import the.bug.web_shop_system.model.ProductCategory;

public interface ProductCategoryDAO extends GenericCRUDMethods<ProductCategory, String >{

    ProductCategory findProductCategoryByName(String productCategoryByName);
}
