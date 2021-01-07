package hibernate.lesson6.fullproductdao;

import org.hibernate.Session;

@FunctionalInterface
public interface DBWorker {
    void execute(Session session);
}