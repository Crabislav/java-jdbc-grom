package hibernate.lesson8.controller;

import hibernate.lesson8.entities.User;
import hibernate.lesson8.service.UserService;

public class UserController implements Controller<User> {
    private static UserController userControllerInstance;
    private static final UserService userService = UserService.getInstance();

    @Override
    public User save(User user) {
        return userService.save(user);
    }

    @Override
    public void delete(User user) {
        userService.delete(user);
    }

    @Override
    public User update(User user) {
        return userService.update(user);
    }

    @Override
    public User findById(User user) {
        return userService.findById(user);
    }

    public static UserController getInstance() {
        if (userControllerInstance == null) {
            userControllerInstance = new UserController();
        }
        return userControllerInstance;
    }
}
