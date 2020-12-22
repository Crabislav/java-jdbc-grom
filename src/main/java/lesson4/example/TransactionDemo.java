package lesson4.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TransactionDemo {
    private static final String DB_URL = "jdbc:oracle:thin:@gromcode-lessons.cgumkbozheyb.eu-west-3.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASSWORD = "gromcode";

    private static final String INSERT_INTO_PRODUCT = "INSERT INTO PRODUCT VALUES(?,?,?,?)";

    public static void save(List<Product> products) {
        try (Connection connection = getConnection()) {
            saveList(products, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void saveList(List<Product> products, Connection connection) throws SQLException {
        long productId = -1;
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_PRODUCT)) {
            connection.setAutoCommit(false);

            for (Product product : products) {
                productId = product.getId();

                preparedStatement.setLong(1, productId);
                preparedStatement.setString(2, product.getName());
                preparedStatement.setString(3, product.getDescription());
                preparedStatement.setInt(4, product.getPrice());

                int res = preparedStatement.executeUpdate();
                System.out.println("save was finished with res " + res);
            }

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw new SQLException("Unable to save product item (id=" + productId + ")");
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }
}
