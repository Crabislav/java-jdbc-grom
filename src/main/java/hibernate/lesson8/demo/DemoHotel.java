package hibernate.lesson8.demo;

import hibernate.lesson8.dao.HotelDAO;
import hibernate.lesson8.entities.Hotel;

public class DemoHotel {
    private static final HotelDAO HOTEL_DAO = HotelDAO.getInstance();
    private static Hotel testHotel = new Hotel(1L, "Hotel", "UA", "Kiev", "Street");

    public static void main(String[] args) {
        testHotelDAO();
    }

    private static void testHotelDAO() {
        boolean result;

        //save
        HOTEL_DAO.save(testHotel);

        //find by id
        result = HOTEL_DAO.findById(testHotel.getId()).isPresent();
        System.out.println("save:" + result);

        //update
        String newName = "NotHotel";
        testHotel.setName(newName);

        HOTEL_DAO.update(testHotel);

        Hotel updatedHotel = HOTEL_DAO.findById(testHotel.getId()).get();
        result = updatedHotel.getName().equals(newName);
        System.out.println("update:" + result);

        //delete
        HOTEL_DAO.delete(testHotel.getId());
        result = HOTEL_DAO.findById(testHotel.getId()).isEmpty();
        System.out.println("delete:" + result);

        System.out.println("================================");
    }

}
