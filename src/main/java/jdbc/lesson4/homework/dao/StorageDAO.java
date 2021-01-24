package jdbc.lesson4.homework.dao;

import jdbc.lesson4.homework.model.Storage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StorageDAO extends DAO<Storage> {
    private static final String INSERT_STORAGE = "INSERT INTO STORAGES VALUES(?, ?, ?, ?)";
    private static final String DELETE_STORAGE_BY_ID = "DELETE FROM STORAGES WHERE ID=?";
    private static final String SELECT_STORAGE_BY_ID = "SELECT * FROM STORAGES WHERE ID=?";
    private static final String UPDATE_STORAGE = "UPDATE STORAGES SET FORMATS_SUPPORTED=?, STORAGE_COUNTRY=?, " +
            "STORAGE_MAX_SIZE=? WHERE ID=?";
    private static final String SELECT_SIZE = "SELECT SUM(FILE_SIZE) FROM FILES F JOIN STORAGES S ON S.ID = F.STORAGE_ID WHERE S.ID=?";

    @Override
    public Storage save(Storage storage) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STORAGE)) {

            preparedStatement.setLong(1, storage.getId());
            preparedStatement.setString(2, storage.getFormatsSupported());
            preparedStatement.setString(3, storage.getStorageCountry());
            preparedStatement.setLong(4, storage.getStorageMaxSize());

            int res = preparedStatement.executeUpdate();
            System.out.println("save was finished with res " + res);
        } catch (SQLException e) {
            throw new SQLException("Unable to save storage(id=" + storage.getId() + ")");
        }

        return storage;
    }

    @Override
    public void delete(long id) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STORAGE_BY_ID)) {

            preparedStatement.setLong(1, id);
            int res = preparedStatement.executeUpdate();
            System.out.println("delete was finished with res " + res);
        } catch (SQLException e) {
            throw new SQLException("Unable to delete storage(id=" + id + ")");
        }
    }

    @Override
    public Storage update(Storage storage) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STORAGE)) {

            preparedStatement.setString(1, storage.getFormatsSupported());
            preparedStatement.setString(2, storage.getStorageCountry());
            preparedStatement.setLong(3, storage.getStorageMaxSize());
            preparedStatement.setLong(4, storage.getId());

            int res = preparedStatement.executeUpdate();
            System.out.println("update was finished with res " + res);
        } catch (SQLException e) {
            throw new SQLException("Unable to update storage(id=" + storage.getId() + ")");
        }

        return storage;
    }

    @Override
    public Storage findById(long id) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STORAGE_BY_ID)) {

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            Storage storage = null;
            if (resultSet.next()) {
                storage = new Storage(resultSet.getLong(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getLong(4));
            }

            return storage;
        } catch (SQLException e) {
            throw new SQLException("Unable to update storage(id=" + id + ")");
        }
    }

    public long getFreeSpace(Storage storage) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SIZE)) {

            preparedStatement.setLong(1, storage.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                return -1;
            }

            return storage.getStorageMaxSize() - resultSet.getLong(1);
        } catch (SQLException e) {
            throw new SQLException("Can't calculate free space for storage(id=" + storage.getId() + ")");
        }
    }
}
