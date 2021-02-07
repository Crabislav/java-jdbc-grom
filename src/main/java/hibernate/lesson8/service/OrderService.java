package hibernate.lesson8.service;

import hibernate.lesson8.dao.OrderDAO;
import hibernate.lesson8.entities.Order;
import hibernate.lesson8.exceptions.NoAuthorizedUserException;
import hibernate.lesson8.usersession.UserSessionManager;

import java.util.Optional;

public class OrderService implements Service<Order> {
    private static final OrderService ORDER_SERVICE = getInstance();
    private static final OrderDAO ORDER_DAO = OrderDAO.getInstance();

    @Override
    public Order save(Order order) {
        if (!UserSessionManager.isUserLoggedIn()) {
            throw new NoAuthorizedUserException("User is not authorized");
        }

        Order orderToSave = getFilteredOptional(order)
                .orElseThrow(() -> new IllegalArgumentException("Input object has an invalid field"));
        return ORDER_DAO.save(orderToSave);
    }

    @Override
    public void delete(long id) {
        if (!UserSessionManager.isUserLoggedIn()) {
            throw new NoAuthorizedUserException("User is not authorized");
        }

        ORDER_DAO.delete(id);
    }

    @Override
    public Order update(Order order) {
        if (!UserSessionManager.isUserLoggedIn()) {
            throw new NoAuthorizedUserException("User is not authorized");
        }

        return ORDER_DAO.update(order);
    }

    @Override
    public Optional<Order> findById(long id) {
        if (!UserSessionManager.isUserLoggedIn()) {
            throw new NoAuthorizedUserException("User is not authorized");
        }

        return ORDER_DAO.findById(id);
    }

    public static OrderService getInstance() {
        return Optional.ofNullable(ORDER_SERVICE)
                .orElseGet(OrderService::new);
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
