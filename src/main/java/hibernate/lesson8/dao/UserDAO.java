package hibernate.lesson8.dao;

import hibernate.lesson8.entities.User;

import java.util.Optional;

public class UserDAO implements DAO<User>{
    private static final UserDAO USER_DAO = getInstance();

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
    public User findById() {
        return null;
    }

    public static UserDAO getInstance(){
        return Optional.ofNullable(USER_DAO)
                .orElseGet(UserDAO::new);
    }
}
