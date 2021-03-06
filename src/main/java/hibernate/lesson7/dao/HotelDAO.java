package hibernate.lesson7.dao;

import hibernate.lesson7.entity.Hotel;

import java.util.concurrent.atomic.AtomicReference;

public class HotelDAO extends DAO<Hotel> {
    @Override
    public Hotel save(Hotel hotel) {
        executeInsideTransaction(session -> session.save(hotel));
        return hotel;
    }

    @Override
    public void delete(long id) {
        Hotel hotel = new Hotel();
        hotel.setId(id);
        executeInsideTransaction(session -> session.delete(hotel));
    }

    @Override
    public Hotel update(Hotel hotel) {
        executeInsideTransaction(session -> session.update(hotel));
        return hotel;
    }

    @Override
    public Hotel findById(long id) {
        AtomicReference<Hotel> hotelAtomicReference = new AtomicReference<>();
        executeInsideTransaction(session -> hotelAtomicReference.set(session.find(Hotel.class, id)));
        return hotelAtomicReference.get();
    }
}
