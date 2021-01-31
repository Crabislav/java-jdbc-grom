package hibernate.lesson8.dao;

import hibernate.lesson8.entities.Hotel;

import java.util.Optional;

public class HotelDAO implements DAO<Hotel> {
    private static final HotelDAO HOTEL_DAO = getInstance();

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
    public Hotel findById() {
        return null;
    }

    public static HotelDAO getInstance() {
        return Optional.ofNullable(HOTEL_DAO)
                .orElseGet(HotelDAO::new);
    }
}
