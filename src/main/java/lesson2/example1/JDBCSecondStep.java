package lesson2.example1;

import java.sql.*;
import java.util.Date;

public class JDBCSecondStep {
    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL = "jdbc:oracle:thin:@gromcode-lessons.cgumkbozheyb.eu-west-3.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASSWORD = "gromcode";

    private static final String SELECT_FROM_ORDERS_BY_PRICE = "SELECT * FROM ORDERS WHERE PRICE > 450";
    //1,DB Driver
    //2. create connection
    //3. create query
    //4. execute query
    //5. work with result
    //6. close all the connection

    public static void main(String[] args) throws Exception {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            try {
                Class.forName(JDBC_DRIVER);
            } catch (ClassNotFoundException e) {
                System.out.println("Class " + JDBC_DRIVER + " not found");
                return;
            }

            try (ResultSet resultSet = stmt.executeQuery(SELECT_FROM_ORDERS_BY_PRICE)) {
                while (resultSet.next()) {
                    long id = resultSet.getLong(1);
                    String productName = resultSet.getString(2);
                    int price = resultSet.getInt(3);
                    Date dateOrdered = resultSet.getDate(4);
                    Date dateConfirmed = resultSet.getDate(5);
                    Order order = new Order(id, productName, price, dateOrdered, dateConfirmed);

                    System.out.println(order);
                }
            }
        } catch (Exception e) {
            throw new Exception("Something went wrong");
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }
}
