package hibernate.lesson8.dao;

import hibernate.lesson8.usersession.SessionFactoryManager;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Optional;
import java.util.function.Consumer;

public interface DAO<T> {
    T save(T t);

    void delete(long id);

    T update(T t);

    Optional<T> findById(long id);

    static void executeInsideTransaction(Consumer<Session> action){
        Transaction tr = null;
        try (Session session = SessionFactoryManager.getInstance().openSession()) {
            tr = session.getTransaction();
            tr.begin();

            action.accept(session);

            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
            }
        }
    }
}
