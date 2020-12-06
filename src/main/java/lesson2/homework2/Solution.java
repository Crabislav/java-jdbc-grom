package lesson2.homework2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    private static final String DB_URL = "jdbc:oracle:thin:@gromcode-lessons.cgumkbozheyb.eu-west-3.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASSWORD = "gromcode";

    private static final String SELECT_PRODUCTS_BY_PRICE = "SELECT * FROM PRODUCT WHERE PRICE<970";
    private static final String SELECT_PRODUCTS_BY_DESCRIPTION = "SELECT * FROM PRODUCT WHERE LENGTH(DESCRIPTION)>6";

    private static final String UPDATE_DESCRIPTION = "UPDATE PRODUCT SET DESCRIPTION=? WHERE ID=?";
    private static final String UPDATE_PRICE = "UPDATE PRODUCT SET PRICE=? WHERE ID=?";

    public static void main(String[] args) {
        increasePrice();
        changeDescription();
    }

    static void increasePrice() {
        List<Product> products = getProducts(SELECT_PRODUCTS_BY_PRICE);

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRICE)) {

            for (Product product : products) {
                preparedStatement.setInt(1, product.getPrice() + 100);
                preparedStatement.setLong(2, product.getId());

                preparedStatement.executeUpdate();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    static void changeDescription() {
        List<Product> products = getProducts(SELECT_PRODUCTS_BY_DESCRIPTION);

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DESCRIPTION)) {

            for (Product product : products) {
                preparedStatement.setString(1, deleteLastSentence(product));
                preparedStatement.setLong(2, product.getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private static String deleteLastSentence(Product product) {
        String newDescription;
        String oldDescription = product.getDescription();
        int indexOfLastPoint = oldDescription.lastIndexOf('.');

        if (indexOfLastPoint != -1) {
            String temp = oldDescription.substring(0, indexOfLastPoint);
            newDescription = temp.substring(0, temp.lastIndexOf('.') + 1);
        } else {
            newDescription = "";
        }
        return newDescription;
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

}

