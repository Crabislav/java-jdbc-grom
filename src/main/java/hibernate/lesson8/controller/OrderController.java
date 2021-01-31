package hibernate.lesson8.controller;

import hibernate.lesson8.entities.Order;
import hibernate.lesson8.service.OrderService;

import java.util.Optional;

public class OrderController implements Controller<Order> {
    private static final OrderController ORDER_CONTROLLER = getInstance();
    private static final OrderService ORDER_SERVICE = OrderService.getInstance();

    @Override
    public Order save(Order order) {
        return ORDER_SERVICE.save(order);
    }

    @Override
    public void delete(long id) {
        ORDER_SERVICE.delete(id);
    }

    @Override
    public Order update(Order order) {
        return ORDER_SERVICE.update(order);
    }

    @Override
    public Order findById(long id) {
        return ORDER_SERVICE.findById(id);
    }

    public static OrderController getInstance() {
        return Optional.ofNullable(ORDER_CONTROLLER)
                .orElseGet(OrderController::new);
    }
}
