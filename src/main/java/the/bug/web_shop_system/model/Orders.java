package the.bug.web_shop_system.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
public class Orders {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String orderId;
    private LocalDate lastUpdated;
    private BigDecimal priceTotal;
    private OrderStatus orderStatus;

    @OneToMany(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH},
            fetch = FetchType.LAZY,
            mappedBy = "orders")
    private Set<OrderItem> orderItems;

    @ManyToOne(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH},
            fetch = FetchType.EAGER)
    private Customer customer;

    public Orders() {
    }

    public Orders(String orderId, LocalDate lastUpdated, BigDecimal priceTotal,
                  OrderStatus orderStatus, Set<OrderItem> orderItems, Customer customer) {
        this.orderId = orderId;
        this.lastUpdated = lastUpdated;
        this.priceTotal = priceTotal;
        this.orderStatus = orderStatus;
        this.orderItems = orderItems;
        this.customer = customer;
    }

    //Convenience Methods
    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrders(this);
    }

    public void removeOrderItem(OrderItem orderItem){
        orderItem.setOrders(null);
        orderItems.remove(orderItem);
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public LocalDate getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDate lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public BigDecimal getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(BigDecimal priceTotal) {
        this.priceTotal = priceTotal;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders order = (Orders) o;
        return Objects.equals(getPriceTotal(), order.getPriceTotal()) && Objects.equals(getOrderStatus(), order.getOrderStatus()) && Objects.equals(getOrderItems(), order.getOrderItems()) && Objects.equals(getCustomer(), order.getCustomer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPriceTotal(), getOrderStatus(), getOrderItems(), getCustomer());
    }


}
