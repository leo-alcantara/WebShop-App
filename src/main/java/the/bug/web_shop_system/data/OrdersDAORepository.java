package the.bug.web_shop_system.data;

import org.springframework.stereotype.Repository;
import the.bug.web_shop_system.exceptions.ExceptionManager;
import the.bug.web_shop_system.model.Orders;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class OrdersDAORepository implements OrdersDAO {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public Orders create(Orders orders) throws ExceptionManager {
        if (orders == null) {
            throw new ExceptionManager("Order can not be null.");
        }
        entityManager.persist(orders);
        return orders;
    }

    @Override
    @Transactional
    public Orders delete(Orders orders) throws ExceptionManager {
        if (orders == null) {
            throw new ExceptionManager("Order can not be null.");
        }
        entityManager.remove(orders);
        return orders;
    }

    @Override
    @Transactional
    public List<Orders> findAll() {
        return entityManager.createQuery("SELECT o FROM Orders o", Orders.class).getResultList();
    }

    @Override
    @Transactional
    public Orders findById(String ordersId) throws ExceptionManager {
        if (ordersId == null) {
            throw new ExceptionManager("Order can not be null.");
        }
        return entityManager.find(Orders.class, ordersId);
    }

    @Override
    @Transactional
    public Orders update(Orders orders) throws ExceptionManager {
        if (orders == null) {
            throw new ExceptionManager("Order can not be null.");
        }
        return entityManager.merge(orders);
    }

    @Override
    @Transactional
    public void clear() {
        entityManager.clear();
    }

    @Override
    public List<Orders> findOrdersByCustomer(String customerEmail) throws ExceptionManager {
        if (customerEmail == null) {
            throw new ExceptionManager("Product can not be null.");
        }
        return entityManager.createQuery("SELECT o FROM Orders o WHERE o.customer.email = ?1", Orders.class)
                .setParameter(1, customerEmail).getResultList();
    }
}
