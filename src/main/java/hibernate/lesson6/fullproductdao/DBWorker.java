package hibernate.lesson6.fullproductdao;

import org.hibernate.Session;

@FunctionalInterface
interface DBWorker {
    void execute(Session session);
}