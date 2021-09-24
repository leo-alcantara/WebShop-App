package the.bug.web_shop_system.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String orderItemId;
    private Integer amount;
    private BigDecimal itemPrice;

    @ManyToOne(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH},
            fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", table = "order_item")
    private Product product;

    @ManyToOne(cascade = {CascadeType.DETACH,
    CascadeType.MERGE,
    CascadeType.PERSIST,
    CascadeType.REFRESH},
    fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", table = "order_item")
    private Orders orders;

    public OrderItem() {
    }

    public OrderItem(String orderItemId, Integer amount, BigDecimal itemPrice, Product product, Orders orders) {
        this.orderItemId = orderItemId;
        this.amount = amount;
        this.itemPrice = itemPrice;
        this.product = product;
        this.orders = orders;
        calcPrice();
    }

    private void calcPrice(){

        itemPrice = product.getProductPrice().multiply(BigDecimal.valueOf(amount));

    }


    public String getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(String orderItem) {
        this.orderItemId = orderItem;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(getAmount(), orderItem.getAmount()) && Objects.equals(getItemPrice(), orderItem.getItemPrice()) && Objects.equals(getProduct(), orderItem.getProduct());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAmount(), getItemPrice(), getProduct());
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "orderItem='" + orderItemId + '\'' +
                ", amount=" + amount +
                ", itemPrice=" + itemPrice +
                ", product=" + product +
                '}';
    }
}
