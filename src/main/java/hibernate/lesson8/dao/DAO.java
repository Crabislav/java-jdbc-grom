package hibernate.lesson8.dao;

public interface DAO<T> {
    T save(T t);

    void delete(T t);

    T update(T t);

    T findById();
}
