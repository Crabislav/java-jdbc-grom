package hibernate.lesson8.service;

import hibernate.lesson8.dao.RoomDAO;
import hibernate.lesson8.entities.Room;

import java.util.Optional;

public class RoomService implements Service<Room> {
    private static final RoomService ROOM_SERVICE = getInstance();
    private static final RoomDAO ROOM_DAO = RoomDAO.getInstance();

    @Override
    public Room save(Room room) {
        return null;
    }

    @Override
    public void delete(Room room) {

    }

    @Override
    public Room update(Room room) {
        return null;
    }

    @Override
    public Room findById(Room room) {
        return null;
    }

    public static RoomService getInstance() {
        return Optional.ofNullable(ROOM_SERVICE)
                .orElseGet(RoomService::new);
    }
}
