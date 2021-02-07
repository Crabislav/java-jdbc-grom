package hibernate.lesson8.demo;

import hibernate.lesson8.dao.HotelDAO;
import hibernate.lesson8.dao.RoomDAO;
import hibernate.lesson8.entities.Hotel;
import hibernate.lesson8.entities.Room;

import java.util.Date;

public class DemoRoom {
    private static final RoomDAO ROOM_DAO = RoomDAO.getInstance();
    private static final HotelDAO HOTEL_DAO = HotelDAO.getInstance();

    private static Hotel testHotel = new Hotel(1L, "Hotel", "UA", "Kiev", "Street");
    private static Room testRoom = new Room(1L, 2, 50, true, false, new Date(), testHotel);

    public static void main(String[] args) {
        HOTEL_DAO.save(testHotel);

        testRoomDAO();

        HOTEL_DAO.delete(testHotel.getId());
    }

    private static void testRoomDAO() {
        boolean result;

        //save
        ROOM_DAO.save(testRoom);

        //find by id
        result = ROOM_DAO.findById(testRoom.getId()).isPresent();
        System.out.println("save:" + result);

        //update
        testRoom.setBreakfastIncluded(false);
        ROOM_DAO.update(testRoom);

        Room updatedRoom = ROOM_DAO.findById(testRoom.getId()).get();
        result = updatedRoom.isBreakfastIncluded() == false;
        System.out.println("update:" + result);

        //delete
        ROOM_DAO.delete(testRoom.getId());
        result = ROOM_DAO.findById(testRoom.getId()).isEmpty();
        System.out.println("delete:" + result);

        System.out.println("================================");
    }

}
