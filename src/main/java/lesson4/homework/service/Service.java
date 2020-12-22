package lesson4.homework.service;

import lesson4.homework.exception.InvalidInputException;

public interface Service<T> {
    T save(T t) throws InvalidInputException;

    void delete(long id) throws InvalidInputException;

    T update(T t) throws InvalidInputException;

    T findById(long id) throws InvalidInputException;

    T findById(long id);
}
