package jdbc.lesson2.example1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExamples {
    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL = "jdbc:oracle:thin:@gromcode-lessons.cgumkbozheyb.eu-west-3.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASSWORD = "gromcode";

    private static final String INSERT_INTO_PRODUCT = "INSERT INTO PRODUCT VALUES(1, 'Toy', 'Toy for children', 50)";

    public static void main(String[] args) {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

//            boolean res = stmt.execute("INSERT INTO PRODUCT(ID, NAME, DESCRIPTION, PRICE) " +
//                    "VALUES(1, 'Toy', 'Toy for children', 50)");
//            System.out.println(res);

//            boolean res = stmt.execute("DELETE FROM PRODUCT WHERE NAME = 'Toy'");
//            System.out.println(res);

            int response = stmt.executeUpdate(INSERT_INTO_PRODUCT);
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }
}
