package hibernate.lesson8.service;

import hibernate.lesson8.entities.User;

public class UserService implements Service<User> {
    private static UserService userService;


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
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }
}
