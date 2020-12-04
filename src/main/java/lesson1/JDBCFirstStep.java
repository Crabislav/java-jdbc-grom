package lesson1;

import java.sql.*;

public class JDBCFirstStep {
    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL = "";

    private static final String USER = "";
    private static final String PASSWORD = "";

    //1,DB Driver
    //2. create connection
    //3. create query
    //4. execute query
    //5. work with result
    //6. close all the connection

    public static void main(String[] args) throws Exception {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {

            try {
                Class.forName(JDBC_DRIVER);
            } catch (ClassNotFoundException e) {
                System.out.println("Class " + JDBC_DRIVER + " not found");
                return;
            }

            try (ResultSet resultSet = stmt.executeQuery("SELECT * FROM Test")) {
                while (resultSet.next()) {
                    System.out.println("Object found");
                }
            }
        } catch (Exception e) {
            throw new Exception("Something went wrong");
        }
    }
}
