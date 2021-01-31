package hibernate.lesson8.service;

import hibernate.lesson8.dao.HotelDAO;
import hibernate.lesson8.entities.Hotel;

import java.util.Optional;

public class HotelService implements Service<Hotel> {
    private static final HotelService HOTEL_SERVICE = getInstance();
    private static final HotelDAO HOTEL_DAO = HotelDAO.getInstance();

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
        return Optional.ofNullable(HOTEL_SERVICE)
                .orElseGet(HotelService::new);
    }
}
