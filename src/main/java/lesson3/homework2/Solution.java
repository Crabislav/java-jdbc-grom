package lesson3.homework2;

import java.sql.*;

public class Solution {
    private static final String DB_URL = "jdbc:oracle:thin:@gromcode-lessons.cgumkbozheyb.eu-west-3.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASSWORD = "gromcode";

    private static final String INSERT_INTO_TEST_SPEED = "INSERT INTO TEST_SPEED VALUES (?,?,?)";
    private static final String DELETE_FROM_TEST_SPEED_BY_ID = "DELETE FROM TEST_SPEED WHERE ID=?";
    private static final String DELETE_ALL_FROM_TEST_SPEED_BY_ID = "DELETE FROM TEST_SPEED";
    private static final String SELECT_FROM_TEST_SPEED_BY_SINGLE_ID = "SELECT * FROM TEST_SPEED WHERE ID=?";
    private static final String SELECT_ALL_FROM_TEST_SPEED_BY_ID = "SELECT *  FROM TEST_SPEED";

    private static final int ROWS_AMOUNT = 1000;

    private static final String performanceExceptionMessage = "Unable to test performance";
    //Все методы должны замерять скорость операции в миллисекундах и возвращать полученное значение

    //будет успешно добавлять 1000 записей в таблицу TEST_SPEED c произвольными значениями
    //103404
    long testSavePerformance() throws PerformanceTestException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_TEST_SPEED)) {

            long start = System.currentTimeMillis();
            for (int i = 0; i < ROWS_AMOUNT; i++) {
                preparedStatement.setLong(1, i);
                preparedStatement.setString(2, "string" + i);
                preparedStatement.setInt(3, i * 10);

                preparedStatement.executeUpdate();
            }
            long finish = System.currentTimeMillis();

            return finish - start;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new PerformanceTestException(performanceExceptionMessage);
    }

    //будет удалять 1000 добавленных перед этим записей, отдельными запросами по полю ID
    //106088
    long testDeleteByIdPerformance() throws PerformanceTestException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FROM_TEST_SPEED_BY_ID)) {

            long start = System.currentTimeMillis();
            for (int i = 0; i < ROWS_AMOUNT; i++) {
                preparedStatement.setLong(1, i);
                preparedStatement.executeUpdate();
            }
            long finish = System.currentTimeMillis();

            return finish - start;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new PerformanceTestException(performanceExceptionMessage);
    }

    //будет удалять 1000, одним SQL запросом
    //206
    long testDeletePerformance() throws PerformanceTestException {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            long start = System.currentTimeMillis();
            statement.executeQuery(DELETE_ALL_FROM_TEST_SPEED_BY_ID);
            long finish = System.currentTimeMillis();

            return finish - start;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new PerformanceTestException(performanceExceptionMessage);
    }

    //будет выбирать по очереди 1000 добавленных перед этим записей, отдельными запросами
    //по полю ID
    //103220
    long testSelectByIdPerformance() throws PerformanceTestException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_TEST_SPEED_BY_SINGLE_ID)) {

            long start = System.currentTimeMillis();
            for (int i = 0; i < ROWS_AMOUNT; i++) {
                preparedStatement.setLong(1, i);
                preparedStatement.executeQuery();
            }
            long finish = System.currentTimeMillis();

            return finish - start;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new PerformanceTestException(performanceExceptionMessage);
    }


    //будет выбирать 1000 записей, одним SQL запросом
    //308
    long testSelectPerformance() throws PerformanceTestException {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            long start = System.currentTimeMillis();
            statement.executeQuery(SELECT_ALL_FROM_TEST_SPEED_BY_ID);
            long finish = System.currentTimeMillis();

            return finish - start;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new PerformanceTestException(performanceExceptionMessage);
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }
}

