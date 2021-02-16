package hibernate.lesson8.service;

import hibernate.lesson8.dao.HotelDAO;
import hibernate.lesson8.entities.Hotel;
import hibernate.lesson8.exceptions.NoAuthorizedUserException;
import hibernate.lesson8.exceptions.NotEnoughRightsUserException;
import hibernate.lesson8.usersession.UserSession;

import java.util.Optional;

public class HotelService implements Service<Hotel> {
    private static HotelService hotelServiceInstance;

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
        return HotelDAO.getInstance().save(hotelToSave);
    }

    @Override
    public void delete(long id) {
        if (!UserSession.isUserLoggedIn()) {
            throw new NoAuthorizedUserException("User is not authorized");
        }

        if (!UserSession.isAdmin()) {
            throw new NotEnoughRightsUserException("Not enough rights to perform this action");
        }

        HotelDAO.getInstance().delete(id);
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

        return HotelDAO.getInstance().update(hotelToUpdate);
    }

    @Override
    public Optional<Hotel> findById(long id) {
        if (!UserSession.isUserLoggedIn()) {
            throw new NoAuthorizedUserException("User is not authorized");
        }

        return HotelDAO.getInstance().findById(id);
    }

    public static HotelService getInstance() {
        if (hotelServiceInstance == null) {
            hotelServiceInstance = new HotelService();
        }
        return hotelServiceInstance;
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
