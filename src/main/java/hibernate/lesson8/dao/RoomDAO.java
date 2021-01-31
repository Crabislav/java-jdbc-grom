package hibernate.lesson8.dao;

import hibernate.lesson8.entities.Room;

import java.util.Optional;

public class RoomDAO implements DAO<Room> {
    private static final RoomDAO ROOM_DAO = getInstance();

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
    public Room findById() {
        return null;
    }

    public static RoomDAO getInstance() {
        return Optional.ofNullable(ROOM_DAO)
                .orElseGet(RoomDAO::new);
    }
}
