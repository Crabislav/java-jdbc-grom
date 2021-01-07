package hibernate.lesson6.productdaonativesql;

import org.hibernate.Session;

@FunctionalInterface
interface DBWorker {
    void execute(Session session);
}