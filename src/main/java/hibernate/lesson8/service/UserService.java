package hibernate.lesson8.service;

import hibernate.lesson8.dao.UserDAO;
import hibernate.lesson8.entities.User;

import java.util.Optional;

public class UserService implements Service<User> {
    private final static UserService USER_SERVICE = getInstance();
    private static final UserDAO USER_DAO = UserDAO.getInstance();

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public void delete(User user) {

    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public User findById(User user) {
        return null;
    }

    public static UserService getInstance() {
        return Optional.ofNullable(USER_SERVICE)
                .orElseGet(UserService::new);
    }
}
