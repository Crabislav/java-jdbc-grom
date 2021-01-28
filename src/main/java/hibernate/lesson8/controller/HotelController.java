package hibernate.lesson8.controller;

import hibernate.lesson8.entities.Hotel;
import hibernate.lesson8.service.HotelService;

public class HotelController implements Controller<Hotel> {
    private static HotelController hotelController;
    private static final HotelService hotelService = HotelService.getInstance();

    @Override
    public Hotel save(Hotel hotel) {
        return hotelService.save(hotel);
    }

    @Override
    public void delete(Hotel hotel) {
        hotelService.delete(hotel);
    }

    @Override
    public Hotel update(Hotel hotel) {
        return hotelService.update(hotel);
    }

    @Override
    public Hotel findById(Hotel hotel) {
        return hotelService.findById(hotel);
    }

    public static HotelController getInstance() {
        if (hotelController == null) {
            hotelController = new HotelController();
        }
        return hotelController;
    }
}
