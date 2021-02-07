package hibernate.lesson8.dao;

import hibernate.lesson8.entities.Room;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class RoomDAO implements DAO<Room> {
    private static final RoomDAO ROOM_DAO = getInstance();

    @Override
    public Room save(Room room) {
        DAO.executeInsideTransaction(session -> session.save(room));
        return room;
    }

    @Override
    public void delete(long id) {
        Room room = new Room();
        room.setId(id);
        DAO.executeInsideTransaction(session -> session.delete(room));
    }

    @Override
    public Room update(Room room) {
        DAO.executeInsideTransaction(session -> session.update(room));
        return room;
    }

    @Override
    public Optional<Room> findById(long id) {
        AtomicReference<Room> room = new AtomicReference<>(new Room());
        DAO.executeInsideTransaction(session -> room.set(
                session.find(Room.class, id)
        ));
        return Optional.ofNullable(room.get());
    }

    public static RoomDAO getInstance() {
        return Optional.ofNullable(ROOM_DAO)
                .orElseGet(RoomDAO::new);
    }
}
