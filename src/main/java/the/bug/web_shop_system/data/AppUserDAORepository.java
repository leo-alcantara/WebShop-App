package the.bug.web_shop_system.data;

import org.springframework.stereotype.Repository;
import the.bug.web_shop_system.exceptions.ExceptionManager;
import the.bug.web_shop_system.model.AppUser;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class AppUserDAORepository implements AppUserDAO {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    //Create method persists an appUser and returns the persisted appUser
    public AppUser create(AppUser appUser) throws ExceptionManager {
        if (appUser == null) {
            throw new ExceptionManager("Product can not be null.");
        }
        entityManager.persist(appUser);
        return appUser;
    }

    @Override
    @Transactional
    //Delete method deletes an appUser and returns the deleted appUser
    public AppUser delete(AppUser appUser) throws ExceptionManager {
        if (appUser == null) {
            throw new ExceptionManager("Product can not be null.");
        }
        entityManager.remove(appUser);
        return appUser;
    }

    @Override
    @Transactional
    //Find All method finds all appUsers and returns a collection with all the found Appusers
    public List<AppUser> findAll() {
        return entityManager.createQuery("SELECT a FROM AppUser a", AppUser.class).getResultList();
    }

    @Override
    @Transactional
    //Find by id method finds an appUser using its id and returns the found appUser
    public AppUser findById(String appUserId) {
        return entityManager.find(AppUser.class, appUserId);
    }

    @Override
    @Transactional
    //Update method updates an appUser's attributes and returns the updated appUser
    public AppUser update(AppUser appUser) {
        return entityManager.merge(appUser);
    }

    @Override
    @Transactional
    //Clear method clears the DB table
    public void clear() {
        entityManager.clear();
    }
}
