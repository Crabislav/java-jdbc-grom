package lesson4.homework.dao;

import lesson4.homework.model.File;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FileDao extends DAO<File> {
    private static final String INSERT_FILE = "INSERT INTO FILES VALUES (?, ?, ?, ?, ?)";
    private static final String DELETE_FILE_BY_ID = "DELETE FROM FILES WHERE ID=?";
    private static final String UPDATE_FILE = "UPDATE FILES SET NAME=?, FILE_FORMAT=?, FILE_SIZE=?, STORAGE_ID=?, WHERE ID=?";
    private static final String SELECT_FILE_BY_ID = "SELECT * FROM FILES WHERE ID=?";

    private static final StorageDAO STORAGE_DAO = new StorageDAO();

    @Override
    public File save(File file) {
        try (Connection connection = getConnection()) {
            connection.setAutoCommit(false);
            saveSingleRecord(file, connection);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return file;
    }

    public List<File> save(List<File> files) {
        try (Connection connection = getConnection()) {
            connection.setAutoCommit(false);

            for (File file : files) {
                saveSingleRecord(file, connection);
            }

            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return files;
    }

    private void saveSingleRecord(File file, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_FILE)) {
            preparedStatement.setLong(1, file.getId());
            preparedStatement.setString(2, file.getName());
            preparedStatement.setString(3, file.getFormat());
            preparedStatement.setLong(4, file.getSize());
            preparedStatement.setLong(5, file.getStorage().getId());

            int res = preparedStatement.executeUpdate();
            System.out.println("save was finished with res " + res);
        } catch (SQLException e) {
            connection.rollback();
            throw new SQLException("Unable to save file(id=" + file.getId() + ")");
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
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FILE_BY_ID)) {
            connection.setAutoCommit(false);

            preparedStatement.setLong(1, id);
            int res = preparedStatement.executeUpdate();
            System.out.println("delete was finished with res " + res);

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw new SQLException("Unable to delete file(id=" + id + ")");
        }
    }

    @Override
    public File update(File file) {
        try (Connection connection = getConnection()) {
            updateRecord(file, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return file;
    }

    private void updateRecord(File file, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_FILE)) {
            connection.setAutoCommit(false);

            preparedStatement.setString(1, file.getName());
            preparedStatement.setString(2, file.getFormat());
            preparedStatement.setLong(3, file.getSize());
            preparedStatement.setLong(4, file.getStorage().getId());
            preparedStatement.setLong(5, file.getId());

            int res = preparedStatement.executeUpdate();
            System.out.println("update was finished with res " + res);

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw new SQLException("Unable to update file(id=" + file.getId() + ")");
        }
    }

    @Override
    public File findById(long id) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FILE_BY_ID)) {

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                File file = new File(resultSet.getLong(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getLong(4));
                file.setStorage(STORAGE_DAO.findById(resultSet.getLong(5)));

                return file;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
