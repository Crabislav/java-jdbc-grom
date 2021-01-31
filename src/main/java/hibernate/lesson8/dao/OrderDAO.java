package hibernate.lesson8.dao;

import hibernate.lesson8.entities.Order;

import java.util.Optional;

public class OrderDAO implements DAO<Order> {
    private static final OrderDAO ORDER_DAO = getInstance();

    @Override
    public Order save(Order order) {
        return null;
    }

    @Override
    public void delete(Order order) {

    }

    @Override
    public Order update(Order order) {
        return null;
    }

    @Override
    public Order findById() {
        return null;
    }

    public static OrderDAO getInstance() {
        return Optional.ofNullable(ORDER_DAO)
                .orElseGet(OrderDAO::new);
    }
}
