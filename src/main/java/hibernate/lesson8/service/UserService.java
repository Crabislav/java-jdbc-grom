package hibernate.lesson8.service;

import hibernate.lesson8.dao.UserDAO;
import hibernate.lesson8.entities.User;
import hibernate.lesson8.exceptions.NoAuthorizedUserException;
import hibernate.lesson8.usersession.UserSession;

import java.util.Optional;

public class UserService implements Service<User> {
    private final static UserService USER_SERVICE = getInstance();
    private static final UserDAO USER_DAO = UserDAO.getInstance();

    @Override
    public User save(User user) {
        User userToSave = getFilteredOptional(user)
                .orElseThrow(() -> new IllegalArgumentException("Input object has an invalid field"));
        return USER_DAO.save(userToSave);
    }

    @Override
    public void delete(long id) {
        if (!UserSession.isUserLoggedIn()) {
            throw new NoAuthorizedUserException("User is not authorized");
        }

        if (id < 1) {
            throw new IllegalArgumentException("Id(" + id + ") can't be < 1");
        }

        USER_DAO.delete(id);
    }

    @Override
    public User update(User user) {
        if (!UserSession.isUserLoggedIn()) {
            throw new NoAuthorizedUserException("User is not authorized");
        }

        User userToUpdate = getFilteredOptional(user)
                .orElseThrow(() -> new IllegalArgumentException("Input object has an invalid field"));

        return USER_DAO.update(userToUpdate);
    }

    @Override
    public Optional<User> findById(long id) {
        if (!UserSession.isUserLoggedIn()) {
            throw new NoAuthorizedUserException("User is not authorized");
        }

        return USER_DAO.findById(id);
    }

    public static UserService getInstance() {
        return Optional.ofNullable(USER_SERVICE).orElseGet(UserService::new);
    }

    private Optional<User> getFilteredOptional(User user) {
        return Optional.ofNullable(user)
                .filter(input -> input.getId() != null && input.getId() > 0)
                .filter(input -> input.getUserName() != null && !input.getUserName().isEmpty())
                .filter(input -> input.getPassword() != null && !input.getPassword().isEmpty())
                .filter(input -> input.getCountry() != null && !input.getCountry().isEmpty());
    }
}
