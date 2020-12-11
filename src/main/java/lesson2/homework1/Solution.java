package lesson2.homework1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL = "jdbc:oracle:thin:@gromcode-lessons.cgumkbozheyb.eu-west-3.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASSWORD = "gromcode";

    private static final String SELECT_ALL_PRODUCTS = "SELECT * FROM PRODUCT";
    private static final String SELECT_ALL_PRODUCTS_BY_PRICE = "SELECT * FROM PRODUCT WHERE PRICE <=100";
    private static final String SELECT_ALL_PRODUCTS_BY_DESCRIPTION = "SELECT * FROM PRODUCT WHERE LENGTH(DESCRIPTION)>50";


    public static void main(String[] args) {
//        System.out.println(getAllProducts());
//        System.out.println(getProductsByPrice());
        System.out.println(getProductsByDescription());
    }

    static List<Product> getAllProducts() {
        return getProducts(SELECT_ALL_PRODUCTS);
    }

    static List<Product> getProductsByPrice() {
        return getProducts(SELECT_ALL_PRODUCTS_BY_PRICE);
    }

    static List<Product> getProductsByDescription() {
        return getProducts(SELECT_ALL_PRODUCTS_BY_DESCRIPTION);
    }

    private static List<Product> getProducts(String sql) {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(sql);
            return mapProducts(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }

    private static List<Product> mapProducts(ResultSet resultSet) throws SQLException {
        try {
            List<Product> products = new ArrayList<>();

            while (resultSet.next()) {
                Product product = new Product(resultSet.getLong(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getInt(4));
                products.add(product);
            }

            return products;
        } catch (SQLException e) {
            throw new SQLException();
        }
    }
}
