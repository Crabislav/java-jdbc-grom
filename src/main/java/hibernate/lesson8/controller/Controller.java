package hibernate.lesson8.controller;

import java.util.Optional;

public interface  Controller<T> {
    T save(T t);

    void delete(long id);

    T update(T t);

    Optional<T> findById(long id);
}
