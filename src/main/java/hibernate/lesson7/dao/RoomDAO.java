package hibernate.lesson7.dao;

import hibernate.lesson7.entity.Room;

import java.util.concurrent.atomic.AtomicReference;

public class RoomDAO extends DAO<Room> {
    @Override
    public Room save(Room room) {
        executeInsideTransaction(session -> session.save(room));
        return room;
    }

    @Override
    public void delete(long id) {
        Room room = new Room();
        room.setId(id);
        executeInsideTransaction(session -> session.delete(room));
    }

    @Override
    public Room update(Room room) {
        executeInsideTransaction(session -> session.update(room));
        return room;
    }

    @Override
    public Room findById(long id) {
        AtomicReference<Room> roomAtomicReference = new AtomicReference<>();
        executeInsideTransaction(session -> roomAtomicReference.set(session.find(Room.class, id)));
        return roomAtomicReference.get();
    }
}
