package hibernate.lesson5.homework.repository;

import org.hibernate.Session;

@FunctionalInterface
public interface DBWorker {
    void execute(Session session);
}
