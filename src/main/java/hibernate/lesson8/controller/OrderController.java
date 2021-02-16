package hibernate.lesson8.controller;

import hibernate.lesson8.entities.Order;
import hibernate.lesson8.service.OrderService;

import java.util.Optional;

public class OrderController implements Controller<Order> {
    private static OrderController orderControllerInstance;

    @Override
    public Order save(Order order) {
        return OrderService.getInstance().save(order);
    }

    @Override
    public void delete(long id) {
        OrderService.getInstance().delete(id);
    }

    @Override
    public Order update(Order order) {
        return OrderService.getInstance().update(order);
    }

    @Override
    public Optional<Order> findById(long id) {
        return OrderService.getInstance().findById(id);
    }

    public static OrderController getInstance() {
        if (orderControllerInstance == null) {
            orderControllerInstance = new OrderController();
        }
        return orderControllerInstance;
    }
}
