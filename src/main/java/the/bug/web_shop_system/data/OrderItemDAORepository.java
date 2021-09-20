package the.bug.web_shop_system.data;

import org.springframework.stereotype.Repository;
import the.bug.web_shop_system.exceptions.ExceptionManager;
import the.bug.web_shop_system.model.OrderItem;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Collection;

@Repository
public class OrderItemDAORepository implements OrderItemDAO {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public OrderItem create(OrderItem orderItem) throws ExceptionManager {
        if (orderItem == null) {
            throw new ExceptionManager("Product can not be null.");
        }
        entityManager.persist(orderItem);
        return orderItem;
    }

    @Override
    @Transactional
    public OrderItem delete(OrderItem orderItem) throws ExceptionManager {
        if (orderItem == null) {
            throw new ExceptionManager("Product can not be null.");
        }
        entityManager.remove(orderItem);
        return orderItem;
    }

    @Override
    @Transactional
    public Collection<OrderItem> findAll() {
        return entityManager.createQuery("SELECT o FROM OrderItem o").getResultList();
    }

    @Override
    @Transactional
    public OrderItem findById(String orderItemId) {
        return entityManager.find(OrderItem.class, orderItemId);
    }

    @Override
    @Transactional
    public OrderItem update(OrderItem orderItem) {
        return entityManager.merge(orderItem);
    }

    @Override
    @Transactional
    public void clear() {
        entityManager.clear();
    }
}
