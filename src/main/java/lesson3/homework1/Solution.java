package lesson3.homework1;

import lesson3.example.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Solution {
    private static final String DB_URL = "jdbc:oracle:thin:@gromcode-lessons.cgumkbozheyb.eu-west-3.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASSWORD = "gromcode";

    private static final String SELECT_PRODUCT_BY_PRICE = "SELECT * FROM PRODUCT WHERE PRICE BETWEEN ? AND ?";
    private static final String SELECT_PRODUCT_BY_NAME = "SELECT * FROM PRODUCT WHERE NAME LIKE ?";
    private static final String SELECT_PRODUCT_BY_LENGTH_OF_DESCRIPTION = "SELECT * FROM PRODUCT WHERE DESCRIPTION IS NULL";

    //будет искать продукты с заданной ценной в диапазоне +=delta включительно.
    // Например, если нужно найти продукты с ценой 100 и дельтой 10, то ищем все от 90 до 110
    List<Product> findProductsByPrice(int price, int delta) {
        StatementPreparer statementPreparer = preparedStatement -> {
            preparedStatement.setInt(1, price - delta);
            preparedStatement.setInt(2, price + delta);
        };
        return findProducts(statementPreparer, SELECT_PRODUCT_BY_PRICE);
    }

    //продукты, которые содержат в своем имене слово word.
    //Если word является некоректным (больше одного слова в стринге, длина меньше 3, содержит спецсимволы),
    //выбрасывать ошибку, которая в описании обязательно должна содержать само слово и описание ошибки
    List<Product> findProductsByName(String word) throws BadRequestException {
        validateWord(word);

        StatementPreparer statementPreparer = preparedStatement -> {
            String pattern = "%" + word + "%";
            preparedStatement.setString(1, pattern);
        };

        return findProducts(statementPreparer, SELECT_PRODUCT_BY_NAME);
    }

    // продукты с пустым полем описания
    List<Product> findProductsWithEmptyDescription() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(SELECT_PRODUCT_BY_LENGTH_OF_DESCRIPTION);
            return mapProducts(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void validateWord(String word) throws BadRequestException {
        if (word.length() < 3) {
            throw new BadRequestException("Input (" + word + ") length should be equals or greater than 3.");
        }

        if (word.split("(\\w+)+").length > 1) {
            throw new BadRequestException("Input (" + word + ") should be only a single word");
        }

        for (char wordChar : word.toCharArray()) {
            if (!Character.isLetterOrDigit(wordChar)) {
                throw new BadRequestException("Input (" + word + ") mustn't contain any special characters");
            }
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }

    private List<Product> findProducts(StatementPreparer statementPreparer, String sql) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            statementPreparer.prepare(preparedStatement);

            ResultSet resultSet = preparedStatement.executeQuery();
            return mapProducts(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private List<Product> mapProducts(ResultSet resultSet) throws SQLException {
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
