package lesson4.homework.service;

import lesson4.homework.exception.InvalidInputException;

public interface Service<T> {
    T save(T t) throws InvalidInputException;

    void delete(long id) throws InvalidInputException;

    T update(T t) throws InvalidInputException;

    T findById(long id) throws InvalidInputException;

    default void checkId(long id) throws InvalidInputException {
        if (id < 0) {
            throw new InvalidInputException("Id must be equals or greater than 0");
        }
    }

    default void checkForNull(Object obj) throws InvalidInputException {
        if (obj == null) {
            throw new InvalidInputException("Input can't be null");
        }
    }
}
