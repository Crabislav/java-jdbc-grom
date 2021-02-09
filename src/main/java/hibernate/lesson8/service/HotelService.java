package hibernate.lesson8.service;

import hibernate.lesson8.dao.HotelDAO;
import hibernate.lesson8.entities.Hotel;
import hibernate.lesson8.exceptions.NoAuthorizedUserException;
import hibernate.lesson8.exceptions.NotEnoughRightsUserException;
import hibernate.lesson8.usersession.UserSession;

import java.util.Optional;

public class HotelService implements Service<Hotel> {
    private static final HotelService HOTEL_SERVICE = getInstance();
    private static final HotelDAO HOTEL_DAO = HotelDAO.getInstance();

    @Override
    public Hotel save(Hotel hotel) {
        if (!UserSession.isUserLoggedIn()) {
            throw new NoAuthorizedUserException("User is not authorized");
        }

        if (!UserSession.isAdmin()) {
            throw new NotEnoughRightsUserException("Not enough rights to perform this action");
        }

        Hotel hotelToSave = getFilteredOptional(hotel)
                .orElseThrow(() -> new IllegalArgumentException("Input object has an invalid field"));
        return HOTEL_DAO.save(hotelToSave);
    }

    @Override
    public void delete(long id) {
        if (!UserSession.isUserLoggedIn()) {
            throw new NoAuthorizedUserException("User is not authorized");
        }

        if (!UserSession.isAdmin()) {
            throw new NotEnoughRightsUserException("Not enough rights to perform this action");
        }

        if (id < 1) {
            throw new IllegalArgumentException("Id(" + id + ") can't be < 1");
        }
        HOTEL_DAO.delete(id);
    }

    @Override
    public Hotel update(Hotel hotel) {
        if (!UserSession.isUserLoggedIn()) {
            throw new NoAuthorizedUserException("User is not authorized");
        }

        if (!UserSession.isAdmin()) {
            throw new NotEnoughRightsUserException("Not enough rights to perform this action");
        }

        Hotel hotelToUpdate = getFilteredOptional(hotel)
                .orElseThrow(() -> new IllegalArgumentException("Input object has an invalid field"));

        return HOTEL_DAO.update(hotelToUpdate);
    }

    @Override
    public Optional<Hotel> findById(long id) {
        if (!UserSession.isUserLoggedIn()) {
            throw new NoAuthorizedUserException("User is not authorized");
        }

        if (id < 1) {
            throw new IllegalArgumentException("Input id (id" + id + ") can't be lower than 1");
        }
        return HOTEL_DAO.findById(id);
    }

    public static HotelService getInstance() {
        return Optional.ofNullable(HOTEL_SERVICE).orElseGet(HotelService::new);
    }

    private Optional<Hotel> getFilteredOptional(Hotel hotel) {
        return Optional.ofNullable(hotel)
                .filter(input -> input.getId() != null && input.getId() > 0)
                .filter(input -> input.getName() != null && !input.getName().isEmpty())
                .filter(input -> input.getCountry() != null && !input.getCountry().isEmpty())
                .filter(input -> input.getCity() != null && !input.getCity().isEmpty())
                .filter(input -> input.getStreet() != null && !input.getStreet().isEmpty());
    }
}
