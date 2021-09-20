package the.bug.web_shop_system.data;


import org.springframework.stereotype.Repository;
import the.bug.web_shop_system.exceptions.ExceptionManager;
import the.bug.web_shop_system.model.Address;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class AddressDAORepository implements AddressDAO {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    //Create method persists an address and returns the persisted address
    public Address create(Address address) throws ExceptionManager {
        if (address == null) {
            throw new ExceptionManager("Product can not be null.");
        }
        entityManager.persist(address);
        return address;
    }

    @Override
    @Transactional
    //Delete method deletes an address and returns the deleted address
    public Address delete(Address address) throws ExceptionManager {
        if (address == null) {
            throw new ExceptionManager("Product can not be null.");
        }
        entityManager.remove(address);
        return address;
    }

    @Override
    @Transactional
    //Find All method finds all addresses and returns a collection with all the found addresses
    public List<Address> findAll() {
        return entityManager.createQuery("SELECT a FROM Address a").getResultList();
    }

    @Override
    @Transactional
    //Find by id method finds an address using its id and returns the found address
    public Address findById(String address) {
        return entityManager.find(Address.class, address);
    }

    @Override
    @Transactional
    //Update method updates an address' attributes and returns the updated address
    public Address update(Address address) {
        return entityManager.merge(address);
    }

    @Override
    @Transactional
    //Clear method clears the DB table
    public void clear() {

    }
}
