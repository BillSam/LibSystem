package Beans;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by error on 3/27/18.
 */
@Local
public interface BeanInt<T> {
    boolean create(T t);
    T get(long id);
    boolean update(T t,long id);
    boolean delete(long id);
    List getAll();

}
