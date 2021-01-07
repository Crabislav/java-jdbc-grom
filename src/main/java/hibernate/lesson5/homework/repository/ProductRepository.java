package hibernate.lesson5.homework.repository;

import hibernate.lesson5.example.Product;
import hibernate.lesson5.homework.HibernateUtils;
import org.hibernate.Session;

public class ProductRepository {
    private static final HibernateUtils HIBERNATE_UTILS = new HibernateUtils();

    public Product save(Product product) {
        Session session = HIBERNATE_UTILS.createSessionFactory().openSession();
        session.getTransaction().begin();

        session.save(product);

        session.getTransaction().commit();
        session.close();
        return product;
    }

    public void delete(long id) {
        Session session = HIBERNATE_UTILS.createSessionFactory().openSession();
        session.getTransaction().begin();

        Product product = new Product();
        product.setId(id);
        session.delete(product);

        session.getTransaction().commit();
        session.close();
    }

    public Product update(Product product) {
        Session session = HIBERNATE_UTILS.createSessionFactory().openSession();
        session.getTransaction().begin();

        session.update(product);

        session.getTransaction().commit();
        session.close();
        return product;
    }
}
