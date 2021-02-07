package hibernate.lesson8.usersession;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryManager {
    private static SessionFactory sessionFactory;

    public static SessionFactory getInstance() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }

// TODO: 31.01.2021 check up commented variant

//    private static SessionFactory sessionFactory = getInstance();
//
//    static SessionFactory getInstance() {
//        return Optional.ofNullable(sessionFactory)
//                .orElseGet(() -> new Configuration().configure().buildSessionFactory());
//    }
}
