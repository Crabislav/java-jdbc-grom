package hibernate.lesson8.usersession;

import hibernate.lesson8.entities.User;
import hibernate.lesson8.entities.UserType;
import hibernate.lesson8.exceptions.NoAuthorizedUserException;

import java.util.Optional;

public class UserSession {
    private static User authorizedUser = null;

    public static Optional<User> getAuthorizedUser() {
        return Optional.ofNullable(authorizedUser);
    }

    public static void login(User user) {
        if (authorizedUser == null) {
            authorizedUser = user;
        }
    }

    public static void logout() {
        authorizedUser = null;
    }

    public static boolean isAdmin() {
        return Optional.ofNullable(authorizedUser)
                .orElseThrow(() -> new NoAuthorizedUserException("User is not authorized"))
                .getUserType() == UserType.ADMIN;
//        return authorizedUser.getUserType() == UserType.ADMIN;
    }

    public static boolean isUserLoggedIn() {
        return authorizedUser == null;
    }
}
