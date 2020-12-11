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
//        increasePrice();
        changeDescription();
    }

    static void increasePrice() {
        StatementPreparer statementPreparer = (preparedStatement, product) -> {
            try {
                preparedStatement.setInt(1, product.getPrice() + 100);
                preparedStatement.setLong(2, product.getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        };
        processData(SELECT_PRODUCTS_BY_PRICE, UPDATE_PRICE, statementPreparer);

    }

    static void changeDescription() {
        StatementPreparer statementPreparer = (preparedStatement, product) -> {
            try {
                preparedStatement.setString(1, deleteLastSentence(product));
                preparedStatement.setLong(2, product.getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        };
        processData(SELECT_PRODUCTS_BY_DESCRIPTION, UPDATE_DESCRIPTION, statementPreparer);
    }

    static void processData(String selectSQL, String updateSQL, StatementPreparer statementPreparer) {
        List<Product> products = getProducts(selectSQL);

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {

            for (Product product : products) {
                statementPreparer.prepare(preparedStatement, product);
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

