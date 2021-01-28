package hibernate.lesson8.service;

import hibernate.lesson8.entities.Hotel;

public class HotelService implements Service<Hotel> {
    private static HotelService hotelService;

    @Override
    public Hotel save(Hotel hotel) {
        return null;
    }

    @Override
    public void delete(Hotel hotel) {

    }

    @Override
    public Hotel update(Hotel hotel) {
        return null;
    }

    @Override
    public Hotel findById(Hotel hotel) {
        return null;
    }

    public static HotelService getInstance() {
        if (hotelService == null) {
            hotelService = new HotelService();
        }
        return hotelService;
    }
}
