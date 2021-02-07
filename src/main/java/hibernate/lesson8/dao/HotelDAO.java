package hibernate.lesson8.dao;

import hibernate.lesson8.entities.Hotel;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class HotelDAO implements DAO<Hotel> {
    private static final HotelDAO HOTEL_DAO = getInstance();

    @Override
    public Hotel save(Hotel hotel) {
        DAO.executeInsideTransaction(session -> session.save(hotel));
        return hotel;
    }

    @Override
    public void delete(long id) {
        Hotel hotel = new Hotel();
        hotel.setId(id);
        DAO.executeInsideTransaction(session -> session.delete(hotel));
    }

    @Override
    public Hotel update(Hotel hotel) {
        DAO.executeInsideTransaction(session -> session.update(hotel));
        return hotel;
    }

    @Override
    public Optional<Hotel> findById(long id) {
        AtomicReference<Hotel> hotel = new AtomicReference<>(new Hotel());
        DAO.executeInsideTransaction(session -> hotel.set(
                session.find(Hotel.class, id)
        ));
        return Optional.ofNullable(hotel.get());
    }

    public static HotelDAO getInstance() {
        return Optional.ofNullable(HOTEL_DAO)
                .orElseGet(HotelDAO::new);
    }
}
