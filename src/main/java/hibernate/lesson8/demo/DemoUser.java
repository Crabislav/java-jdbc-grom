package hibernate.lesson8.demo;

import hibernate.lesson8.dao.UserDAO;
import hibernate.lesson8.entities.User;
import hibernate.lesson8.entities.UserType;

public class DemoUser {
    private static final UserDAO USER_DAO = UserDAO.getInstance();
    private static User testAdminUser = new User(1L, "Admin", "1234", "UA", UserType.ADMIN);

    public static void main(String[] args) {
        testUserDAO();
    }

    private static void testUserDAO() {
        boolean result;

        //save
        USER_DAO.save(testAdminUser);

        //find by id
        result = USER_DAO.findById(testAdminUser.getId()).isPresent();
        System.out.println("save:" + result);

        //update
        testAdminUser.setUserName("SUPER_ADMIN");
        USER_DAO.update(testAdminUser);
        result = USER_DAO.findById(testAdminUser.getId()).isPresent();
        System.out.println("update:" + result);

        //delete
        USER_DAO.delete(testAdminUser.getId());
        result = USER_DAO.findById(testAdminUser.getId()).isEmpty();
        System.out.println("delete:" + result);

        System.out.println("================================");
    }

}
