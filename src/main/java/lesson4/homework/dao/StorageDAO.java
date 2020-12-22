package lesson4.homework.dao;

import lesson4.homework.model.Storage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StorageDAO extends DAO<Storage> {
    private static final String INSERT_STORAGE = "INSERT INTO STORAGE VALUES(?, ?, ?, ?)";
    private static final String DELETE_STORAGE_BY_ID = "DELETE FROM STORAGE WHERE ID=?";
    private static final String SELECT_STORAGE_BY_ID = "SELECT * FROM STORAGE WHERE ID=?";
    private static final String UPDATE_STORAGE = "UPDATE STORAGE SET FORMATS_SUPPORTED=?, STORAGE_COUNTRY=?, " +
            "STORAGE_MAX_SIZE=? WHERE ID=?";

    @Override
    public Storage save(Storage storage) {
        try (Connection connection = getConnection()) {
            saveSingleRecord(storage, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return storage;
    }

    private void saveSingleRecord(Storage storage, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STORAGE)) {
            connection.setAutoCommit(false);

            preparedStatement.setLong(1, storage.getId());
            preparedStatement.setString(2, storage.getFormatsSupported());
            preparedStatement.setString(3, storage.getStorageCountry());
            preparedStatement.setLong(4, storage.getStorageMaxSize());

            int res = preparedStatement.executeUpdate();
            System.out.println("save was finished with res " + res);

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw new SQLException("Unable to save storage(id=" + storage.getId() + ")");
        }
    }

    @Override
    public void delete(long id) {
        try (Connection connection = getConnection()) {
            deleteSingleRecord(id, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteSingleRecord(long id, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STORAGE_BY_ID)) {
            connection.setAutoCommit(false);

            preparedStatement.setLong(1, id);
            int res = preparedStatement.executeUpdate();
            System.out.println("delete was finished with res " + res);

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw new SQLException("Unable to delete storage(id=" + id + ")");
        }
    }

    @Override
    public Storage update(Storage storage) {
        try (Connection connection = getConnection()) {
            updateRecord(storage, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return storage;
    }

    private void updateRecord(Storage storage, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STORAGE)) {
            connection.setAutoCommit(false);

            preparedStatement.setString(1, storage.getFormatsSupported());
            preparedStatement.setString(2, storage.getStorageCountry());
            preparedStatement.setLong(3, storage.getStorageMaxSize());
            preparedStatement.setLong(4, storage.getId());

            int res = preparedStatement.executeUpdate();
            System.out.println("update was finished with res " + res);

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw new SQLException("Unable to update storage(id=" + storage.getId() + ")");
        }
    }

    @Override
    public Storage findById(long id) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STORAGE_BY_ID)) {

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new Storage(resultSet.getLong(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getLong(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
