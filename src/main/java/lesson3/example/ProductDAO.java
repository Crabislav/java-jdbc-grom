package lesson3.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//CRUD
//create, read, update, delete
public class ProductDAO {
    private static final String DB_URL = "jdbc:oracle:thin:@gromcode-lessons.cgumkbozheyb.eu-west-3.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASSWORD = "gromcode";

    private static final String INSERT_INTO_PRODUCT = "INSERT INTO PRODUCT VALUES(?,?,?,?)";
    private static final String SELECT_ALL_FROM_PRODUCT = "SELECT * FROM PRODUCT";
    private static final String UPDATE_PRODUCT = "UPDATE PRODUCT SET NAME=?, DESCRIPTION=?, PRICE=? WHERE ID=?";
    private static final String DELETE_PRODUCT = "DELETE FROM PRODUCT WHERE ID=?";

    public Product save(Product product) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_PRODUCT)) {
            preparedStatement.setLong(1, product.getId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setInt(4, product.getPrice());

            int res = preparedStatement.executeUpdate();
            System.out.println("save was finished with res " + res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    public Product update(Product product) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT)) {

            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setInt(3, product.getPrice());
            preparedStatement.setLong(4, product.getId());

            int res = preparedStatement.executeUpdate();
            System.out.println("update was finished with res " + res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    public List<Product> getProducts() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            List<Product> products = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_FROM_PRODUCT);

            while (resultSet.next()) {
                Product product = new Product(resultSet.getLong(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getInt(4));
                products.add(product);
            }

            return products;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public void delete(long id) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT)) {

            preparedStatement.setLong(1, id);

            int res = preparedStatement.executeUpdate();
            System.out.println("delete was finished with res " + res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }
}
