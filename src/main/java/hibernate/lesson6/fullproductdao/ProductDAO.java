package hibernate.lesson6.fullproductdao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ProductDAO {
    private static SessionFactory sessionFactory;

    public Product save(Product product) {
        DBWorker dbWorker = session -> session.save(product);
        executeQuery(dbWorker);
        return product;
    }

    public void delete(long id) {
        Product product = new Product();
        product.setId(id);
        DBWorker dbWorker = session -> session.delete(product);
        executeQuery(dbWorker);
    }

    public Product update(Product product) {
        DBWorker dbWorker = session -> session.update(product);
        executeQuery(dbWorker);
        return product;
    }

    public void saveAll(List<Product> products) {
        DBWorker dbWorker = session -> {
            products.forEach(session::save);
        };
        executeQuery(dbWorker);
    }

    public void updateAll(List<Product> products) {
        DBWorker dbWorker = session -> {
            products.forEach(session::update);
        };
        executeQuery(dbWorker);
    }

    public void deleteAll(List<Product> products) {
        DBWorker dbWorker = session -> {
            products.forEach(session::delete);
        };
        executeQuery(dbWorker);
    }

    private static void executeQuery(DBWorker dbWorker) {
        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()) {
            tr = session.getTransaction();
            tr.begin();

            dbWorker.execute(session);

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
