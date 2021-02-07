import hibernate.lesson8.entities.Hotel;
import hibernate.lesson8.entities.User;
import hibernate.lesson8.entities.UserType;
import hibernate.lesson8.exceptions.NotEnoughRightsUserException;
import hibernate.lesson8.service.HotelService;
import hibernate.lesson8.usersession.UserSessionManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class HotelServiceNonAdminUserTest {
    private static final HotelService HOTEL_SERVICE = HotelService.getInstance();
    private static User testSimpleUser = new User(2L, "SimpleUser", "1234", "UA", UserType.USER, null);

    @BeforeAll
    static void loginUser() {
        UserSessionManager.logIn(testSimpleUser);
    }

    @AfterAll
    static void logout() {
        UserSessionManager.logOut();
    }

    @Test
    public void AuthorizedNonAdminUser_SaveHotel_thenThrowsNotEnoughRightsUserException() {
        Hotel hotel = new Hotel(1L,
                "Hotel",
                "UA",
                "Kiev",
                "Kievskaja",
                null);
        Assertions.assertThrows(NotEnoughRightsUserException.class, () -> HOTEL_SERVICE.save(hotel));
    }

    @Test
    public void AuthorizedNonAdminUser_DeleteHotel_thenThrowsNotEnoughRightsUserException() {
        Assertions.assertThrows(NotEnoughRightsUserException.class, () -> HOTEL_SERVICE.delete(1));
    }

    @Test
    public void AuthorizedNonAdminUser_UpdateHotel_thenThrowsNotEnoughRightsUserException() {
        Assertions.assertThrows(NotEnoughRightsUserException.class, () -> HOTEL_SERVICE.update(new Hotel()));
    }

}
