package lesson5.example;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    private SessionFactory sessionFactory;

    public SessionFactory createSessionFactory() {
        return new Configuration().configure().buildSessionFactory();
    }

    public void shutDownSessionFactory() {
        sessionFactory.close();
    }
}
