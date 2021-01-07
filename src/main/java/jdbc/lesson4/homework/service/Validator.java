package jdbc.lesson4.homework.service;

import jdbc.lesson4.homework.dao.StorageDAO;
import jdbc.lesson4.homework.exception.InvalidInputException;
import jdbc.lesson4.homework.model.File;
import jdbc.lesson4.homework.model.Storage;

import java.sql.SQLException;

public class Validator {
    private final static StorageDAO STORAGE_DAO = new StorageDAO();

    private Validator() {
    }

    static void checkId(long id) throws InvalidInputException {
        if (id < 0) {
            throw new InvalidInputException("Id must be equals 0 or greater");
        }
    }

    static void checkForNull(Object obj) throws InvalidInputException {
        if (obj == null) {
            throw new InvalidInputException("Input can't be null");
        }
    }

    static void validateStorage(Storage storage) throws InvalidInputException {
        checkForNull(storage);
        checkId(storage.getId());

        if (storage.getStorageMaxSize() <= 0) {
            throw new InvalidInputException("Size must be equals 0 or greater");
        }

        if (storage.getStorageCountry() == null || storage.getStorageCountry().isEmpty()) {
            throw new InvalidInputException("Storage's country can't be null or empty");
        }
    }

    static void validateFile(File file) throws InvalidInputException {
        checkForNull(file);
        checkId(file.getId());

        if (file.getName() == null || file.getName().isEmpty()) {
            throw new InvalidInputException("File's name can't be null or empty");
        }

        if (file.getFormat() == null || file.getFormat().isEmpty()) {
            throw new InvalidInputException("File's format can't be null or empty");
        }

        if (file.getSize() < 0) {
            throw new InvalidInputException("File size must be equals 0 or greater");
        }
    }

    static void checkIfStorageExists(Storage storage) throws InvalidInputException {
        try {
            if (STORAGE_DAO.findById(storage.getId()) == null) {
                throw new InvalidInputException("Storage (id=" + storage.getId() + ") is absent at database");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
