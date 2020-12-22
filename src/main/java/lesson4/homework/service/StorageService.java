package lesson4.homework.service;

import lesson4.homework.dao.StorageDAO;
import lesson4.homework.exception.InvalidInputException;
import lesson4.homework.model.Storage;

public class StorageService {
    private static final StorageDAO STORAGE_DAO = new StorageDAO();

    public Storage save(Storage storage) throws InvalidInputException {
        validateStorage(storage);
        return STORAGE_DAO.save(storage);
    }

    public void delete(long id) throws InvalidInputException {
        checkId(id);
        STORAGE_DAO.delete(id);
    }

    public Storage update(Storage storage) throws InvalidInputException {
        validateStorage(storage);
        return STORAGE_DAO.update(storage);
    }

    public Storage findById(long id) throws InvalidInputException {
        checkId(id);
        return STORAGE_DAO.findById(id);
    }

    private void checkId(long id) throws InvalidInputException {
        if (id < 0) {
            throw new InvalidInputException("Id must be equals or greater than 0");
        }
    }

    private void validateStorage(Storage storage) throws InvalidInputException {
        if (storage == null) {
            throw new InvalidInputException("Input can't be null");
        }

        if (storage.getId() < -1) {
            throw new InvalidInputException("Id must be 0 or greater");
        }

        if (storage.getStorageMaxSize() <= 0) {
            throw new InvalidInputException("Storage's size must be greater than 0");
        }

        if (storage.getStorageCountry() == null || storage.getStorageCountry().isEmpty()) {
            throw new InvalidInputException("Storage's country can't be null or empty");
        }
    }
}
