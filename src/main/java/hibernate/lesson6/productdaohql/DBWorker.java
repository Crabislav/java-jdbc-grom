package hibernate.lesson6.productdaohql;

import org.hibernate.Session;

@FunctionalInterface
interface DBWorker {
    void execute(Session session);
}