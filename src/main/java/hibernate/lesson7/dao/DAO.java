package hibernate.lesson7.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public abstract class DAO<T> {
    private static SessionFactory sessionFactory;

    public abstract T save(T t);

    public abstract void delete(long id);

    public abstract T update(T t);

    public abstract T findById(long id);

    static void executeQuery(DBWorker dbWorker) {
        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()) {
            tr = session.getTransaction();
            tr.begin();

            dbWorker.execute(session);

            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
            }
            System.err.println(e.getMessage());
        }
    }

    private static SessionFactory createSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }
}
