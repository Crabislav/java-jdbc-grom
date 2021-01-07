package hibernate.lesson5.homework.repository;

import org.hibernate.Session;

@FunctionalInterface
interface DBWorker {
    void execute(Session session);
}
