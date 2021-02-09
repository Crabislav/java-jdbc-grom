package lesson8.servicetest;

import hibernate.lesson8.entities.Hotel;
import hibernate.lesson8.entities.User;
import hibernate.lesson8.entities.UserType;
import hibernate.lesson8.service.HotelService;
import hibernate.lesson8.usersession.UserSession;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class HotelServiceAdminUserTest {
    private static final HotelService HOTEL_SERVICE = HotelService.getInstance();
    private static User testAdminUser = new User(1L, "Admin", "1234", "UA", UserType.ADMIN);

    @BeforeAll
    static void loginUser() {
        UserSession.login(testAdminUser);
    }

    @AfterAll
    static void logout() {
        UserSession.logout();
    }

    @Test
    public void AuthorizedAdminUser_SaveHotel_WithInvalidId_thenThrowsIllegalArgumentException() {
        Hotel hotel = new Hotel(-1L,
                "Hotel",
                "UA",
                "Kiev",
                "Kievskaja");
        Assertions.assertThrows(IllegalArgumentException.class, () -> HOTEL_SERVICE.save(hotel));
    }

    @Test
    public void AuthorizedAdminUser_SaveHotel_WithEmptyCountry_thenThrowsIllegalArgumentException() {
        Hotel hotel = new Hotel(1L,
                "Hotel",
                "",
                "Kiev",
                "Kievskaja");
        Assertions.assertThrows(IllegalArgumentException.class, () -> HOTEL_SERVICE.save(hotel));
    }

    @Test
    public void AuthorizedAdminUser_SaveHotel_WithNullCountry_thenThrowsIllegalArgumentException() {
        Hotel hotel = new Hotel(1L,
                "Hotel",
                null,
                "Kiev",
                "Kievskaja");
        Assertions.assertThrows(IllegalArgumentException.class, () -> HOTEL_SERVICE.save(hotel));
    }

    @Test
    public void AuthorizedAdminUser_SaveHotel_WithNullCity_thenThrowsIllegalArgumentException() {
        Hotel hotel = new Hotel(1L,
                "Hotel",
                "Ukraine",
                null,
                "Kievskaja");
        Assertions.assertThrows(IllegalArgumentException.class, () -> HOTEL_SERVICE.save(hotel));
    }

    @Test
    public void AuthorizedAdminUser_SaveHotel_WithEmptyCity_thenThrowsIllegalArgumentException() {
        Hotel hotel = new Hotel(1L,
                "Hotel",
                "Ukraine",
                "",
                "Kievskaja");
        Assertions.assertThrows(IllegalArgumentException.class, () -> HOTEL_SERVICE.save(hotel));
    }


    @Test
    public void AuthorizedAdminUser_SaveHotel_WithNullStreet_thenThrowsIllegalArgumentException() {
        Hotel hotel = new Hotel(1L,
                "Hotel",
                "Ukraine",
                "Kiev",
                null);
        Assertions.assertThrows(IllegalArgumentException.class, () -> HOTEL_SERVICE.save(hotel));
    }

    @Test
    public void AuthorizedAdminUser_SaveHotel_WithEmptyStreet_thenThrowsIllegalArgumentException() {
        Hotel hotel = new Hotel(1L,
                "Hotel",
                "Ukraine",
                "Kiev",
                "");
        Assertions.assertThrows(IllegalArgumentException.class, () -> HOTEL_SERVICE.save(hotel));
    }
}
