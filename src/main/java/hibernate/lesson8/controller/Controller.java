package hibernate.lesson8.controller;

public interface  Controller<T> {
    T save(T t);

    void delete(long id);

    T update(T t);

    T findById(long id);
}
