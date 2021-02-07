import hibernate.lesson8.entities.Hotel;
import hibernate.lesson8.entities.User;
import hibernate.lesson8.entities.UserType;
import hibernate.lesson8.service.HotelService;
import hibernate.lesson8.usersession.UserSessionManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class HotelServiceAdminUserTest {
    private static final HotelService HOTEL_SERVICE = HotelService.getInstance();
    private static User testAdminUser = new User(1L, "Hotel", "1234", "UA", UserType.ADMIN, null);

    @BeforeAll
    static void loginUser() {
        UserSessionManager.logIn(testAdminUser);
    }

    @AfterAll
    static void logout() {
        UserSessionManager.logOut();
    }

    @Test
    public void AuthorizedAdminUser_SaveHotel_WithInvalidId_thenThrowsIllegalArgumentException() {
        Hotel hotel = new Hotel(-1L,
                "Hotel",
                "UA",
                "Kiev",
                "Kievskaja",
                null);
        Assertions.assertThrows(IllegalArgumentException.class, () -> HOTEL_SERVICE.save(hotel));
    }

    @Test
    public void AuthorizedAdminUser_SaveHotel_WithEmptyCountry_thenThrowsIllegalArgumentException() {
        Hotel hotel = new Hotel(1L,
                "Hotel",
                "",
                "Kiev",
                "Kievskaja",
                null);
        Assertions.assertThrows(IllegalArgumentException.class, () -> HOTEL_SERVICE.save(hotel));
    }

    @Test
    public void AuthorizedAdminUser_SaveHotel_WithNullCountry_thenThrowsIllegalArgumentException() {
        Hotel hotel = new Hotel(1L,
                "Hotel",
                null,
                "Kiev",
                "Kievskaja",
                null);
        Assertions.assertThrows(IllegalArgumentException.class, () -> HOTEL_SERVICE.save(hotel));
    }

    @Test
    public void AuthorizedAdminUser_SaveHotel_WithNullCity_thenThrowsIllegalArgumentException() {
        Hotel hotel = new Hotel(1L,
                "Hotel",
                "Ukraine",
                null,
                "Kievskaja",
                null);
        Assertions.assertThrows(IllegalArgumentException.class, () -> HOTEL_SERVICE.save(hotel));
    }

    @Test
    public void AuthorizedAdminUser_SaveHotel_WithEmptyCity_thenThrowsIllegalArgumentException() {
        Hotel hotel = new Hotel(1L,
                "Hotel",
                "Ukraine",
                "",
                "Kievskaja",
                null);
        Assertions.assertThrows(IllegalArgumentException.class, () -> HOTEL_SERVICE.save(hotel));
    }


    @Test
    public void AuthorizedAdminUser_SaveHotel_WithNullStreet_thenThrowsIllegalArgumentException() {
        Hotel hotel = new Hotel(1L,
                "Hotel",
                "Ukraine",
                "Kiev",
                null,
                null);
        Assertions.assertThrows(IllegalArgumentException.class, () -> HOTEL_SERVICE.save(hotel));
    }

    @Test
    public void AuthorizedAdminUser_SaveHotel_WithEmptyStreet_thenThrowsIllegalArgumentException() {
        Hotel hotel = new Hotel(1L,
                "Hotel",
                "Ukraine",
                "Kiev",
                "",
                null);
        Assertions.assertThrows(IllegalArgumentException.class, () -> HOTEL_SERVICE.save(hotel));
    }
}
