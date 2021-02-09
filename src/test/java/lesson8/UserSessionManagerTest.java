package lesson8;

import hibernate.lesson8.entities.User;
import hibernate.lesson8.entities.UserType;
import hibernate.lesson8.usersession.UserSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserSessionManagerTest {
    private static User testAdminUser;
    private static User testSimpleUser;

    @BeforeAll
    static void createTestUser() {
        testAdminUser = new User(1L, "Admin", "1234", "UA", UserType.ADMIN);
        testSimpleUser = new User(2L, "SimpleUser", "1234", "UA", UserType.USER);
    }

    @Test
    public void InvokeGetAuthorizedUser_WhenEmptySlot_thenReturnEmptyOptional() {
        assertEquals(Optional.empty(), UserSession.getAuthorizedUser());
    }

    @Test
    public void NonAuthorizedUser_LoginWhenEmptySlot_thenOK() {
        UserSession.login(testAdminUser);
        assertEquals(Optional.of(testAdminUser), UserSession.getAuthorizedUser());
    }

    @Test
    public void AnotherAuthorizedUser_LoginWhenOccupiedSlot_thenOK() {
        UserSession.login(testAdminUser);

        UserSession.login(testSimpleUser);
        assertEquals(Optional.of(testAdminUser), UserSession.getAuthorizedUser());
    }

    @Test
    public void AuthorizedUser_LoginWhenOccupiedSlot_thenOK() {
        UserSession.login(testAdminUser);

        UserSession.login(testSimpleUser);
        assertEquals(Optional.of(testAdminUser), UserSession.getAuthorizedUser());
    }

    @AfterEach
    void logoutUsers() {
        UserSession.logout();
    }
}
