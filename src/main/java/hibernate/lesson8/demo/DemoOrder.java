package hibernate.lesson8.demo;

import hibernate.lesson8.dao.HotelDAO;
import hibernate.lesson8.dao.OrderDAO;
import hibernate.lesson8.dao.RoomDAO;
import hibernate.lesson8.dao.UserDAO;
import hibernate.lesson8.entities.*;

import java.util.Date;

public class DemoOrder {
    private static final OrderDAO ORDER_DAO = OrderDAO.getInstance();
    private static final HotelDAO HOTEL_DAO = HotelDAO.getInstance();
    private static final RoomDAO ROOM_DAO = RoomDAO.getInstance();
    private static final UserDAO USER_DAO = UserDAO.getInstance();

    private static Hotel testHotel = new Hotel(1L, "Hotel", "UA", "Kiev", "Street");
    private static Room testRoom = new Room(1L, 2, 50, true, false, new Date(), null);
    private static User testAdminUser = new User(1L, "Admin", "1234", "UA", UserType.ADMIN);
    private static Order testOrder = new Order(1, null, null, new Date(1_220_227_200L * 1000), new Date(1_226_000_000L * 1000), 6000d);

    public static void main(String[] args) {
        //hotel init
        HOTEL_DAO.save(testHotel);
        testRoom.setHotel(HOTEL_DAO.findById(testHotel.getId()).get());
        //room init
        ROOM_DAO.save(testRoom);
        testOrder.setRoom(ROOM_DAO.findById(testRoom.getId()).get());
        //user init
        USER_DAO.save(testAdminUser);
        testOrder.setUserOrdered(USER_DAO.findById(testAdminUser.getId()).get());

        testRoomDAO();

        USER_DAO.delete(testAdminUser.getId());
        ROOM_DAO.delete(testRoom.getId());
        HOTEL_DAO.delete(testHotel.getId());
    }

    private static void testRoomDAO() {
        boolean result;

        //save
        ORDER_DAO.save(testOrder);
        result = ORDER_DAO.findById(testOrder.getId()).isPresent();
        System.out.println("save: " + result);

        //update
        double newMoney = 900d;
        testOrder.setMoneyPaid(newMoney);
        ORDER_DAO.update(testOrder);

        Order updatedOrder = ORDER_DAO.findById(testOrder.getId()).get();
        result = updatedOrder.getMoneyPaid() == newMoney;
        System.out.println("update: " + result);

        //delete
        ORDER_DAO.delete(testOrder.getId());
        result = ORDER_DAO.findById(testOrder.getId()).isEmpty();
        System.out.println("delete: " + result);

        System.out.println("================================");
    }

}
