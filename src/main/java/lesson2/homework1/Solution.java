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
        List<Product> products = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(sql);
            products = mapProducts(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    private static List<Product> mapProducts(ResultSet resultSet) {
        Product product;
        List<Product> products = new ArrayList<>();

        try {
            while (resultSet.next()) {
                long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                String description = resultSet.getString(3);
                int price = resultSet.getInt(4);

                product = new Product(id, name, description, price);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    static void insertTestValues(int count) {
        String sql;
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            for (int i = 1; i < count + 1; i++) {
                String name = "'Product" + i + "'";
                String description = "'Item" + i + "'";
                int price = 100 * i;

                sql = "INSERT INTO PRODUCT VALUES("
                        + (long) i + ","
                        + name + ","
                        + description + ","
                        + price + ")";

                int response = statement.executeUpdate(sql);
                if (response == 0) {
                    System.out.println("(" + sql + ") didn't executed properly");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
