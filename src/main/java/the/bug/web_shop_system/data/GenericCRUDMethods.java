package the.bug.web_shop_system.data;

import java.util.Collection;
import java.util.Set;

public interface GenericCRUDMethods <T,ID>{

    T create(T t);
    T delete(T t);
    Collection<T> findAll();
    T findById(ID id);
    T update(T t);
    void clear();
}

