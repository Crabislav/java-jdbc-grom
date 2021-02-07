package hibernate.lesson8.dao;

import hibernate.lesson8.entities.Order;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class OrderDAO implements DAO<Order> {
    private static final OrderDAO ORDER_DAO = getInstance();

    @Override
    public Order save(Order order) {
        DAO.executeInsideTransaction(session -> session.save(order));
        return order;
    }

    @Override
    public void delete(long id) {
        Order order = new Order();
        order.setId(id);
        DAO.executeInsideTransaction(session -> session.delete(order));
    }

    @Override
    public Order update(Order order) {
        DAO.executeInsideTransaction(session -> session.update(order));
        return order;
    }

    @Override
    public Optional<Order> findById(long id) {
        AtomicReference<Order> order = new AtomicReference<>(new Order());
        DAO.executeInsideTransaction(session -> order.set(
                session.find(Order.class, id)
        ));
        return Optional.ofNullable(order.get());
    }

    public static OrderDAO getInstance() {
        return Optional.ofNullable(ORDER_DAO)
                .orElseGet(OrderDAO::new);
    }
}
