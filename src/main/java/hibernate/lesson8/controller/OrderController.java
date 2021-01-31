package hibernate.lesson8.controller;

import hibernate.lesson8.entities.Order;
import hibernate.lesson8.service.OrderService;

import java.util.Optional;

public class OrderController implements Controller<Order> {
    private static final OrderController ORDER_CONTROLLER = getInstance();
    private static final OrderService ORDER_SERVICE = OrderService.getInstance();

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
    public Order findById(long id) {
        return null;
    }

    public static OrderController getInstance() {
        return Optional.ofNullable(ORDER_CONTROLLER)
                .orElseGet(OrderController::new);
    }
}
