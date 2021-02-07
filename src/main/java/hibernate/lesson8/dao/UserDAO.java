package hibernate.lesson8.dao;

import hibernate.lesson8.entities.User;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class UserDAO implements DAO<User> {

    private static final UserDAO USER_DAO = getInstance();

    @Override
    public User save(User user) {
        DAO.executeInsideTransaction(session -> session.save(user));
        return user;
    }

    @Override
    public void delete(long id) {
        User userToDelete = new User();
        userToDelete.setId(id);
        DAO.executeInsideTransaction(session -> session.delete(userToDelete));
    }

    @Override
    public User update(User user) {
        DAO.executeInsideTransaction(session -> session.update(user));
        return user;
    }

    @Override
    public Optional<User> findById(long id) {
        AtomicReference<User> user = new AtomicReference<>(new User());
        DAO.executeInsideTransaction(session -> user.set(
                session.find(User.class, id)
        ));
        return Optional.ofNullable(user.get());
    }

    public static UserDAO getInstance() {
        return Optional.ofNullable(USER_DAO)
                .orElseGet(UserDAO::new);
    }
}
