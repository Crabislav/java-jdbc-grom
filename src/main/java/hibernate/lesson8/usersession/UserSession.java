package hibernate.lesson8.usersession;

import hibernate.lesson8.entities.User;

import java.util.Optional;

public class UserSession {
    private static User authorizedUser = null;

    public static Optional<User> getAuthorizedUser() {
        return Optional.ofNullable(authorizedUser);
    }

    static void setAuthorizedUser(User authorizedUser) {
        UserSession.authorizedUser = authorizedUser;
    }
}
