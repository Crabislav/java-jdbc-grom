package hibernate.lesson7.dao;

import org.hibernate.Session;

@FunctionalInterface
interface DBWorker {
    void execute(Session session);
}