package hibernate.lesson8.controller;

import hibernate.lesson8.entities.User;
import hibernate.lesson8.service.UserService;

import java.util.Optional;

public class UserController implements Controller<User> {
    private static final UserController USER_CONTROLLER = getInstance();
    private static final UserService userService = UserService.getInstance();

    @Override
    public User save(User user) {
        return userService.save(user);
    }

    @Override
    public void delete(long id) {
        userService.delete(id);
    }

    @Override
    public User update(User user) {
        return userService.update(user);
    }

    @Override
    public User findById(long id) {
        return userService.findById(id);
    }

    public static UserController getInstance() {
        return Optional.ofNullable(USER_CONTROLLER)
                .orElseGet(UserController::new);
    }
}
