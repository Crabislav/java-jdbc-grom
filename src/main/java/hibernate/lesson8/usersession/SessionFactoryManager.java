package hibernate.lesson8.usersession;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Optional;

public class SessionFactoryManager {
    private static SessionFactory sessionFactory;

    public static SessionFactory getInstance() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }

//    private static final SessionFactory sessionFactory = getInstance();
//
//    public static SessionFactory getInstance() {
//        return Optional.ofNullable(sessionFactory)
//                .orElseGet(() -> new Configuration().configure().buildSessionFactory());
//    }
}
