package lesson4.homework.service;

import lesson4.homework.exception.InvalidInputException;


public interface Service<T> {
    T save(T t) throws InvalidInputException;

    void delete(long id);

    T update(T t);

    T findById(long id);
}
