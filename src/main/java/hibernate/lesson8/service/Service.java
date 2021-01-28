package hibernate.lesson8.service;

public interface Service<T> {
    T save(T t);

    void delete(T t);

    T update(T t);

    T findById(T t);


}
