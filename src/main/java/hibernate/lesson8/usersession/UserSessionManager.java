package hibernate.lesson8.usersession;

import hibernate.lesson8.entities.User;
import hibernate.lesson8.entities.UserType;

public class UserSessionManager {
    public static void logIn(User user) {
        if (UserSession.getAuthorizedUser().isEmpty()) {
            UserSession.setAuthorizedUser(user);
        }
    }

    public static void logOut() {
        UserSession.setAuthorizedUser(null);
    }

    public static boolean isUserAdmin() {
        return UserSession.getAuthorizedUser()
                .filter(user -> user.getUserType() == UserType.ADMIN)
                .isPresent();
    }

    public static boolean isUserLoggedIn() {
        return UserSession.getAuthorizedUser().isPresent();
    }
}
