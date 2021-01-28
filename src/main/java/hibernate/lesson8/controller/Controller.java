package hibernate.lesson8.controller;

public interface  Controller<T> {
    T save(T t);

    void delete(T t);

    T update(T t);

    T findById(T t);

}
