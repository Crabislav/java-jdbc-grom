package hibernate.lesson5.homework.repository;

import hibernate.lesson5.example.Product;
import hibernate.lesson5.homework.HibernateUtils;
import org.hibernate.Session;

public class ProductRepositoryWithLambda {
    private static final HibernateUtils HIBERNATE_UTILS = new HibernateUtils();

    public Product save(Product product) {
        DBWorker dbWorker = session -> session.save(product);
        executeQuery(dbWorker);
        return product;
    }

    public void delete(long id) {
        DBWorker dbWorker = session -> {
            Product product = new Product();
            product.setId(id);
            session.delete(product);
        };
        executeQuery(dbWorker);
    }

    public Product update(Product product) {
        DBWorker dbWorker = session -> session.update(product);
        executeQuery(dbWorker);
        return product;
    }

    private void executeQuery(DBWorker dbWorker) {
        Session session = HIBERNATE_UTILS.createSessionFactory().openSession();
        session.getTransaction().begin();

        dbWorker.execute(session);

        session.getTransaction().commit();
        session.close();
    }
}
