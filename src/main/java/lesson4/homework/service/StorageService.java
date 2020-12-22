package lesson4.homework.service;

import lesson4.homework.dao.StorageDAO;
import lesson4.homework.exception.InvalidInputException;
import lesson4.homework.model.Storage;

public class StorageService implements Service<Storage> {
    private static final StorageDAO STORAGE_DAO = new StorageDAO();

    @Override
    public Storage save(Storage storage) throws InvalidInputException {
        validateStorage(storage);
        return STORAGE_DAO.save(storage);
    }

    @Override
    public void delete(long id) throws InvalidInputException {
        checkId(id);
        STORAGE_DAO.delete(id);
    }

    @Override
    public Storage update(Storage storage) throws InvalidInputException {
        validateStorage(storage);
        return STORAGE_DAO.update(storage);
    }

    @Override
    public Storage findById(long id) throws InvalidInputException {
        checkId(id);
        return STORAGE_DAO.findById(id);
    }

    private void validateStorage(Storage storage) throws InvalidInputException {
        checkForNull(storage);
        checkId(storage.getId());

        if (storage.getStorageMaxSize() <= 0) {
            throw new InvalidInputException("Size must be equals 0 or greater");
        }

        if (storage.getStorageCountry() == null || storage.getStorageCountry().isEmpty()) {
            throw new InvalidInputException("Storage's country can't be null or empty");
        }
    }
}
