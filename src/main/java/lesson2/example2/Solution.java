package lesson2.example2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Solution {
    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL = "jdbc:oracle:thin:@gromcode-lessons.cgumkbozheyb.eu-west-3.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASSWORD = "gromcode";

    private static final String INSERT_INTO_PRODUCT = "INSERT INTO PRODUCT VALUES(999, 'toy', 'for children', 60)";
    private static final String DELETE_FROM_PRODUCT_WHERE_NAME_TOY = "DELETE FROM PRODUCT WHERE NAME!='toy'";
    private static final String DELETE_FROM_PRODUCT_BY_PRICE = "DELETE FROM PRODUCT WHERE PRICE!=100";

    public static void main(String[] args) {
        saveProduct();
        deleteProduct();
        deleteProductsByPrice();
    }

    static void saveProduct() {
        executeQuery(INSERT_INTO_PRODUCT);
    }

    static void deleteProduct() {
        executeQuery(DELETE_FROM_PRODUCT_WHERE_NAME_TOY);
    }

    static void deleteProductsByPrice() {
        executeQuery(DELETE_FROM_PRODUCT_BY_PRICE);
    }

    static void executeQuery(String sql) {
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
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

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }
}
