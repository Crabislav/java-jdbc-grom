package hibernate.lesson8.controller;

import hibernate.lesson8.entities.Hotel;
import hibernate.lesson8.service.HotelService;

import java.util.Optional;

public class HotelController implements Controller<Hotel> {
    private static final HotelController HOTEL_CONTROLLER = getInstance();
    private static final HotelService HOTEL_SERVICE = HotelService.getInstance();

    @Override
    public Hotel save(Hotel hotel) {
        return HOTEL_SERVICE.save(hotel);
    }

    @Override
    public void delete(long id) {
        HOTEL_SERVICE.delete(id);
    }

    @Override
    public Hotel update(Hotel hotel) {
        return HOTEL_SERVICE.update(hotel);
    }

    @Override
    public Hotel findById(long id) {
        return HOTEL_SERVICE.findById(id);
    }

    public static HotelController getInstance() {
        return Optional.ofNullable(HOTEL_CONTROLLER)
                .orElseGet(HotelController::new);
    }
}
