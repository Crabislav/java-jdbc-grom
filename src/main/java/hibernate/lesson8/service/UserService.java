package hibernate.lesson8.service;

import hibernate.lesson8.dao.UserDAO;
import hibernate.lesson8.entities.User;
import hibernate.lesson8.exceptions.NoAuthorizedUserException;
import hibernate.lesson8.usersession.UserSession;

import java.util.Optional;

public class UserService implements Service<User> {
    private static UserService userServiceInstance;

    @Override
    public User save(User user) {
        User userToSave = getFilteredOptional(user)
                .orElseThrow(() -> new IllegalArgumentException("Input object has an invalid field"));
        return UserDAO.getInstance().save(userToSave);
    }

    @Override
    public void delete(long id) {
        if (UserSession.getAuthorizedUser().isEmpty()) {
            throw new NoAuthorizedUserException("User is not authorized");
        }

        UserDAO.getInstance().delete(id);
    }

    @Override
    public User update(User user) {
        if (UserSession.getAuthorizedUser().isEmpty()) {
            throw new NoAuthorizedUserException("User is not authorized");
        }

        User userToUpdate = getFilteredOptional(user)
                .orElseThrow(() -> new IllegalArgumentException("Input object has an invalid field"));

        return UserDAO.getInstance().update(userToUpdate);
    }

    @Override
    public Optional<User> findById(long id) {
        if (UserSession.getAuthorizedUser().isEmpty()) {
            throw new NoAuthorizedUserException("User is not authorized");
        }

        return UserDAO.getInstance().findById(id);
    }

    public static UserService getInstance() {
        if (userServiceInstance == null) {
            userServiceInstance = new UserService();
        }
        return userServiceInstance;
    }

    private Optional<User> getFilteredOptional(User user) {
        return Optional.ofNullable(user)
                .filter(input -> input.getId() != null && input.getId() > 0)
                .filter(input -> input.getUserName() != null && !input.getUserName().isEmpty())
                .filter(input -> input.getPassword() != null && !input.getPassword().isEmpty())
                .filter(input -> input.getCountry() != null && !input.getCountry().isEmpty());
    }
}
