package lesson2.example2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Solution {
    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL = "jdbc:oracle:thin:@gromcode-lessons.cgumkbozheyb.eu-west-3.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASSWORD = "gromcode";

    public static void main(String[] args) {
        saveProduct();
        deleteProduct();
        deleteProductsByPrice();
    }

    static void saveProduct() {
        String sql = "INSERT INTO PRODUCT VALUES(999, 'toy', 'for children', 60)";
        executeQuery(sql);
    }

    static void deleteProduct() {
        String sql = "DELETE FROM PRODUCT WHERE NAME!='toy'";
        executeQuery(sql);
    }

    static void deleteProductsByPrice() {
        String sql = "DELETE FROM PRODUCT WHERE PRICE!=100";
        executeQuery(sql);
    }

    static void executeQuery(String sql) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            int response = statement.executeUpdate(sql);

            if (response == 0) {
                System.out.println("(" + sql + ") didn't executed");
            } else {
                System.out.println("(" + sql + ") succeeded");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
