package hibernate.lesson8.service;

import hibernate.lesson8.dao.RoomDAO;
import hibernate.lesson8.entities.Room;
import hibernate.lesson8.exceptions.NoAuthorizedUserException;
import hibernate.lesson8.exceptions.NotEnoughRightsUserException;
import hibernate.lesson8.usersession.UserSession;

import java.util.Optional;

public class RoomService implements Service<Room> {
    private static RoomService roomServiceInstance;

    @Override
    public Room save(Room room) {
        if (!UserSession.isUserLoggedIn()) {
            throw new NoAuthorizedUserException("User is not authorized");
        }

        if (!UserSession.isAdmin()) {
            throw new NotEnoughRightsUserException("Not enough rights to perform this action");
        }

        Room roomToSave = getFilteredOptional(room)
                .orElseThrow(() -> new IllegalArgumentException("Input object has an invalid field"));
        return RoomDAO.getInstance().save(roomToSave);
    }

    @Override
    public void delete(long id) {
        if (!UserSession.isUserLoggedIn()) {
            throw new NoAuthorizedUserException("User is not authorized");
        }

        if (!UserSession.isAdmin()) {
            throw new NotEnoughRightsUserException("Not enough rights to perform this action");
        }

        RoomDAO.getInstance().delete(id);
    }

    @Override
    public Room update(Room room) {
        if (!UserSession.isUserLoggedIn()) {
            throw new NoAuthorizedUserException("User is not authorized");
        }

        if (!UserSession.isAdmin()) {
            throw new NotEnoughRightsUserException("Not enough rights to perform this action");
        }

        Room hotelToUpdate = getFilteredOptional(room)
                .orElseThrow(() -> new IllegalArgumentException("Input object has an invalid field"));

        return RoomDAO.getInstance().update(hotelToUpdate);
    }

    @Override
    public Optional<Room> findById(long id) {
        if (!UserSession.isUserLoggedIn()) {
            throw new NoAuthorizedUserException("User is not authorized");
        }

        return RoomDAO.getInstance().findById(id);
    }

    public static RoomService getInstance() {
        if (roomServiceInstance == null) {
            roomServiceInstance = new RoomService();
        }
        return roomServiceInstance;
    }

    private Optional<Room> getFilteredOptional(Room room) {
        return Optional.ofNullable(room)
                .filter(input -> input.getId() != null && input.getId() > 0)
                .filter(input -> input.getNumberOfGuests() != null && input.getNumberOfGuests() > 0)
                .filter(input -> input.getPrice() > 0)
                .filter(input -> input.getDateAvailableFrom() != null)
                .filter(input -> input.getHotel() != null);
    }
}
