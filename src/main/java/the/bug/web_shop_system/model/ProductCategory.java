package the.bug.web_shop_system.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class ProductCategory {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String productCategoryId;
    private String productCategoryName;

    @ManyToMany (cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    @JoinTable(name = "product_and_category",
            joinColumns = @JoinColumn(name = "product_category_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> productWithCategory;


    public ProductCategory() {
    }

    public ProductCategory(String productCategoryId, String productCategoryName,
                           Set<Product> productWithCategory) {
        this.productCategoryId = productCategoryId;
        this.productCategoryName = productCategoryName;
        this.productWithCategory = productWithCategory;
    }

    //Convenience Methods
    public void addProductCategory(Product product){
        productWithCategory.add(product);
    }

    public void removeProductCategory(Product product){
        productWithCategory.remove(product);
    }

    public String getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(String productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getProductCategoryName() {
        return productCategoryName;
    }

    public void setProductCategoryName(String productCategoryName) {
        this.productCategoryName = productCategoryName;
    }

    public Set<Product> getProductWithCategory() {
        return productWithCategory;
    }

    public void setProductWithCategory(Set<Product> productWithCategory) {
        this.productWithCategory = productWithCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductCategory that = (ProductCategory) o;
        return  Objects.equals(getProductCategoryName(), that.getProductCategoryName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductCategoryName());
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "productCategoryId='" + productCategoryId + '\'' +
                ", productCategoryName='" + productCategoryName + '\'' +
                '}';
    }
}
