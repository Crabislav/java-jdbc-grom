package hibernate.lesson8.controller;

import hibernate.lesson8.entities.Hotel;
import hibernate.lesson8.service.HotelService;

import java.util.Optional;

public class HotelController implements Controller<Hotel> {
    private static HotelController hotelControllerInstance;

    @Override
    public Hotel save(Hotel hotel) {
        return HotelService.getInstance().save(hotel);
    }

    @Override
    public void delete(long id) {
        HotelService.getInstance().delete(id);
    }

    @Override
    public Hotel update(Hotel hotel) {
        return HotelService.getInstance().update(hotel);
    }

    @Override
    public Optional<Hotel> findById(long id) {
        return HotelService.getInstance().findById(id);
    }

    public static HotelController getInstance() {
        if (hotelControllerInstance == null) {
            hotelControllerInstance = new HotelController();
        }
        return hotelControllerInstance;
    }
}
