package jdbc.lesson1;

import java.sql.*;

public class JDBCFirstStep {
    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL = "jdbc:oracle:thin:@gromcode-lessons.cgumkbozheyb.eu-west-3.rds.amazonaws.com:1521:ORCL";

    private static final String USER = "main";
    private static final String PASSWORD = "gromcode";

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

            try (ResultSet resultSet = stmt.executeQuery("SELECT * FROM TEST")) {
                while (resultSet.next()) {
                    System.out.println("Object found");
                }
            }
        } catch (Exception e) {
            throw new Exception("Something went wrong");
        }
    }
}
