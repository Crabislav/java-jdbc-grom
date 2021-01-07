package hibernate.lesson5.example;

import org.hibernate.Session;

public class Demo {
    public static void main(String[] args) {
        Product product = new Product();
        product.setId(2);
        product.setName("test");
        product.setDescription("test descr");
        product.setPrice(1);

        save(product);
    }

    public static void save(Product product) {
        Session session = new HibernateUtils().createSessionFactory().openSession();
        session.getTransaction().begin();
        session.save(product);
        session.getTransaction().commit();
        session.close();
    }
}
