package hibernate.lesson8.controller;

import hibernate.lesson8.entities.User;
import hibernate.lesson8.service.UserService;

import java.util.Optional;

public class UserController implements Controller<User> {
    private static UserController userControllerInstance;

    @Override
    public User save(User user) {
        return UserService.getInstance().save(user);
    }

    @Override
    public void delete(long id) {
        UserService.getInstance().delete(id);
    }

    @Override
    public User update(User user) {
        return UserService.getInstance().update(user);
    }

    @Override
    public Optional<User> findById(long id) {
        return UserService.getInstance().findById(id);
    }

    public static UserController getInstance() {
        if (userControllerInstance == null) {
            userControllerInstance = new UserController();
        }
        return userControllerInstance;
    }
}
