package hibernate.lesson8.demo;

import hibernate.lesson8.dao.HotelDAO;
import hibernate.lesson8.dao.OrderDAO;
import hibernate.lesson8.dao.RoomDAO;
import hibernate.lesson8.entities.*;

import java.util.Date;

public class DemoOrder {
    private static final OrderDAO ORDER_DAO = OrderDAO.getInstance();
    private static final HotelDAO HOTEL_DAO = HotelDAO.getInstance();
    private static final RoomDAO ROOM_DAO = RoomDAO.getInstance();

    private static Hotel testHotel = new Hotel(1L, "Hotel", "UA", "Kiev", "Street", null);
    private static Room testRoom = new Room(1L, 2, 50, true, false, new Date(), testHotel);
    private static User testAdminUser = new User(1L, "Admin", "1234", "UA", UserType.ADMIN, null);
    private static Order testOrder = new Order(1, testAdminUser, testRoom, new Date(1_220_227_200L * 1000), new Date(1_226_000_000L * 1000), 6000d);

    public static void main(String[] args) {
        testRoomDAO();
    }

    private static void testRoomDAO() {
        HOTEL_DAO.save(testHotel);
        ROOM_DAO.save(testRoom);

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


        ROOM_DAO.delete(testRoom.getId());
        HOTEL_DAO.delete(testHotel.getId());

        System.out.println("================================");
    }

}
