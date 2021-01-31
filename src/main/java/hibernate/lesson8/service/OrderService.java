package hibernate.lesson8.service;

import hibernate.lesson8.dao.OrderDAO;
import hibernate.lesson8.entities.Order;

import java.util.Optional;

public class OrderService implements Service<Order> {
    private static final OrderService ORDER_SERVICE = getInstance();
    private static final OrderDAO ORDER_DAO = OrderDAO.getInstance();

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
    public Order findById(Order order) {
        return null;
    }

    public static OrderService getInstance() {
        return Optional.ofNullable(ORDER_SERVICE)
                .orElseGet(OrderService::new);
    }
}
