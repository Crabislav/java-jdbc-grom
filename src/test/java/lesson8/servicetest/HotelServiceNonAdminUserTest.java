package lesson8.servicetest;

import hibernate.lesson8.entities.Hotel;
import hibernate.lesson8.entities.User;
import hibernate.lesson8.entities.UserType;
import hibernate.lesson8.exceptions.NotEnoughRightsUserException;
import hibernate.lesson8.service.HotelService;
import hibernate.lesson8.usersession.UserSession;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class HotelServiceNonAdminUserTest {
    private static final HotelService HOTEL_SERVICE = HotelService.getInstance();
    private static User testSimpleUser = new User(2L, "NotAdminUser", "1234", "UA", UserType.USER);

    @BeforeAll
    static void loginUser() {
        UserSession.login(testSimpleUser);
    }

    @AfterAll
    static void logout() {
        UserSession.logout();
    }

    @Test
    public void AuthorizedNonAdminUser_SaveHotel_thenThrowsNotEnoughRightsUserException() {
        Hotel hotel = new Hotel(1L,
                "Hotel",
                "UA",
                "Kiev",
                "Kievskaja");
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
