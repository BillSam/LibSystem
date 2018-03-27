package DAOs;

import java.util.List;

/**
 * Created by error on 3/26/18.
 */
public interface Crud<T> {
    T create(T t);
    T read(long id);
    T update(T t,long id);
    T delete(long id);
    List getAll();
}
