package the.bug.web_shop_system.data;

import org.springframework.stereotype.Repository;
import the.bug.web_shop_system.exceptions.ExceptionManager;
import the.bug.web_shop_system.model.OrderItem;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class OrderItemDAORepository implements OrderItemDAO {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    //Create method persists an Order Item and returns the persisted item
    public OrderItem create(OrderItem orderItem) throws ExceptionManager {
        if (orderItem == null) {
            throw new ExceptionManager("Item can not be null.");
        }
        entityManager.persist(orderItem);
        return orderItem;
    }

    @Override
    @Transactional
    //Delete method deletes an Order Item and returns the deleted address
    public OrderItem delete(OrderItem orderItem) throws ExceptionManager {
        if (orderItem == null) {
            throw new ExceptionManager("Item can not be null.");
        }
        entityManager.remove(orderItem);
        return orderItem;
    }

    @Override
    @Transactional
    //Find All method finds all Order Items and returns a collection with all the found items
    public List<OrderItem> findAll() {
        return entityManager.createQuery("SELECT o FROM OrderItem o").getResultList();
    }

    @Override
    @Transactional
    //Find by id method finds an Order Item using its id and returns the found item
    public OrderItem findById(String orderItemId) throws ExceptionManager {
        if (orderItemId == null) {
            throw new ExceptionManager("Item id can not be null.");
        }
        return entityManager.find(OrderItem.class, orderItemId);
    }

    @Override
    @Transactional
    //Update method updates an Order Item's attributes and returns the updated Item
    public OrderItem update(OrderItem orderItem) throws ExceptionManager {
        if (orderItem == null) {
            throw new ExceptionManager("Item can not be null.");
        }
        return entityManager.merge(orderItem);
    }

    @Override
    @Transactional
    //Clear method clears the DB table
    public void clear() {
        entityManager.clear();
    }
}
