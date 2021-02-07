package hibernate.lesson8.service;

import java.util.Optional;

public interface Service<T> {
    T save(T t);

    void delete(long id);

    T update(T t);

    Optional<T> findById(long id);
}
