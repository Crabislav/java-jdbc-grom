package jdbc.lesson4.homework.service;

import jdbc.lesson4.homework.dao.StorageDAO;
import jdbc.lesson4.homework.exception.InvalidInputException;
import jdbc.lesson4.homework.model.Storage;

import java.sql.SQLException;

public class StorageService {
    private static final StorageDAO STORAGE_DAO = new StorageDAO();

    public Storage save(Storage storage) throws InvalidInputException, SQLException {
        validateStorage(storage);
        return STORAGE_DAO.save(storage);
    }

    public void delete(long id) throws InvalidInputException, SQLException {
        if (id < 0) {
            throw new InvalidInputException("Id must be equals 0 or greater");
        }

        STORAGE_DAO.delete(id);
    }

    public Storage update(Storage storage) throws InvalidInputException, SQLException {
        validateStorage(storage);
        validateStorageNewFreeSpace(storage);

        return STORAGE_DAO.update(storage);
    }

    public Storage findById(long id) throws InvalidInputException, SQLException {
        if (id < 0) {
            throw new InvalidInputException("Id must be equals 0 or greater");
        }
        return STORAGE_DAO.findById(id);
    }

    private void validateStorageNewFreeSpace(Storage storage) throws SQLException, InvalidInputException {
        long freeSpace = STORAGE_DAO.getFreeSpace(STORAGE_DAO.findById(storage.getId()));
        long newMaxSize = storage.getStorageMaxSize();

        if (freeSpace > newMaxSize) {
            throw new InvalidInputException("Unable to update storage(id=" + storage.getId() + ")." +
                    " Old storage's free space(" + freeSpace + " can't be lower than new storage's max size("
                    + newMaxSize + ")");
        }
    }

    void validateStorage(Storage storage) throws InvalidInputException {
        if (storage == null) {
            throw new InvalidInputException("Input can't be null");
        }

        if (storage.getId() < 0) {
            throw new InvalidInputException("Id must be equals 0 or greater");
        }

        if (storage.getStorageMaxSize() <= 0) {
            throw new InvalidInputException("Size(" + storage.getStorageMaxSize() + ") must be greater than 0");
        }

        if (storage.getStorageCountry() == null || storage.getStorageCountry().isEmpty()) {
            throw new InvalidInputException("Storage's country(" + storage.getStorageCountry()
                    + ") can't be null or empty");
        }

        if (storage.getFormatsSupported() == null || storage.getFormatsSupported().isEmpty()) {
            throw new InvalidInputException("Storage's formats(" + storage.getFormatsSupported()
                    + ") can't be null or empty");
        }
    }
}
