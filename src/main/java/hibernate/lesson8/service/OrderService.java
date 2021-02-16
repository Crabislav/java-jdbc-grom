package hibernate.lesson8.service;

import hibernate.lesson8.dao.OrderDAO;
import hibernate.lesson8.entities.Order;
import hibernate.lesson8.exceptions.NoAuthorizedUserException;
import hibernate.lesson8.usersession.UserSession;

import java.util.Optional;

public class OrderService implements Service<Order> {
    private static OrderService orderServiceInstance;

    @Override
    public Order save(Order order) {
        if (!UserSession.isUserLoggedIn()) {
            throw new NoAuthorizedUserException("User is not authorized");
        }

        Order orderToSave = getFilteredOptional(order)
                .orElseThrow(() -> new IllegalArgumentException("Input object has an invalid field"));
        return OrderDAO.getInstance().save(orderToSave);
    }

    @Override
    public void delete(long id) {
        if (!UserSession.isUserLoggedIn()) {
            throw new NoAuthorizedUserException("User is not authorized");
        }

        OrderDAO.getInstance().delete(id);
    }

    @Override
    public Order update(Order order) {
        if (!UserSession.isUserLoggedIn()) {
            throw new NoAuthorizedUserException("User is not authorized");
        }

        return OrderDAO.getInstance().update(order);
    }

    @Override
    public Optional<Order> findById(long id) {
        if (!UserSession.isUserLoggedIn()) {
            throw new NoAuthorizedUserException("User is not authorized");
        }

        return OrderDAO.getInstance().findById(id);
    }

    public static OrderService getInstance() {
        if (orderServiceInstance == null) {
            orderServiceInstance = new OrderService();
        }
        return orderServiceInstance;
    }

    private Optional<Order> getFilteredOptional(Order order) {
        return Optional.ofNullable(order)
                .filter(input -> input.getId() > 0)
                .filter(input -> input.getUserOrdered() != null)
                .filter(input -> input.getRoom() != null)
                .filter(input -> input.getDateFrom() != null)
                .filter(input -> input.getDateTo() != null)
                .filter(input -> input.getDateTo().after(input.getDateFrom()))
                .filter(input -> input.getMoneyPaid() > 0
                        && input.getMoneyPaid() >= input.getRoom().getPrice());

    }
}
