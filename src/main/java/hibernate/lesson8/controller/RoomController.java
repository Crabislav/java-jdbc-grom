package hibernate.lesson8.controller;

import hibernate.lesson8.entities.Room;
import hibernate.lesson8.service.RoomService;

import java.util.Optional;

public class RoomController implements Controller<Room> {
    private static final RoomController ROOM_CONTROLLER = getInstance();
    private static final RoomService ROOM_SERVICE = RoomService.getInstance();

    @Override
    public Room save(Room room) {
        return ROOM_SERVICE.save(room);
    }

    @Override
    public void delete(Room room) {
        ROOM_SERVICE.delete(room);
    }

    @Override
    public Room update(Room room) {
        return ROOM_SERVICE.update(room);
    }

    @Override
    public Room findById(long id) {
        return ROOM_SERVICE.findById(id);
    }

    public static RoomController getInstance() {
        return Optional.ofNullable(ROOM_CONTROLLER)
                .orElseGet(RoomController::new);
    }
}
