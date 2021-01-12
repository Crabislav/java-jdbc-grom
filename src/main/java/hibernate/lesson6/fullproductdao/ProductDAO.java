package hibernate.lesson6.fullproductdao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.function.Consumer;

public class ProductDAO {
    private static SessionFactory sessionFactory;

    public Product save(Product product) {
        executeInsideTransaction(session -> session.save(product));
        return product;
    }

    public void delete(long id) {
        Product product = new Product();
        product.setId(id);
        executeInsideTransaction(session -> session.delete(product));
    }

    public Product update(Product product) {
        executeInsideTransaction(session -> session.update(product));
        return product;
    }

    public void saveAll(List<Product> products) {
        executeInsideTransaction(session -> products.forEach(session::save));
    }

    public void updateAll(List<Product> products) {
        executeInsideTransaction(session -> products.forEach(session::update));
    }

    public void deleteAll(List<Product> products) {
        executeInsideTransaction(session -> products.forEach(session::delete));
    }

    private static void executeInsideTransaction(Consumer<Session> action) {
        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()) {
            tr = session.getTransaction();
            tr.begin();

            action.accept(session);

            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.err.println(e.getMessage());

            if (tr != null) {
                tr.rollback();
            }
        }
    }

    private static SessionFactory createSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }
}
