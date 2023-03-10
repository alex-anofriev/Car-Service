package testtask.autoservice.service;

import java.util.List;

public interface CommonMethods<T> {

    T findById(Long id);

    T save(T entity);

    T update(T entity);

    List<T> findAll();
}
