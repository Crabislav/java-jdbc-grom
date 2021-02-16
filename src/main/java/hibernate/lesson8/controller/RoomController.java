package hibernate.lesson8.controller;

import hibernate.lesson8.entities.Room;
import hibernate.lesson8.service.RoomService;

import java.util.Optional;

public class RoomController implements Controller<Room> {
    private static RoomController roomControllerInstance;

    @Override
    public Room save(Room room) {
        return RoomService.getInstance().save(room);
    }

    @Override
    public void delete(long id) {
        RoomService.getInstance().delete(id);
    }

    @Override
    public Room update(Room room) {
        return RoomService.getInstance().update(room);
    }

    @Override
    public Optional<Room> findById(long id) {
        return RoomService.getInstance().findById(id);
    }

    public static RoomController getInstance() {
        if (roomControllerInstance == null) {
            roomControllerInstance = new RoomController();
        }
        return roomControllerInstance;
    }
}
