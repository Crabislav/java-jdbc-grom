package jdbc.lesson4.homework.controller;

import jdbc.lesson4.homework.exception.InvalidInputException;
import jdbc.lesson4.homework.model.Storage;
import jdbc.lesson4.homework.service.StorageService;

import java.sql.SQLException;

public class StorageController {
    private static final StorageService STORAGE_SERVICE = new StorageService();

    public Storage save(Storage storage) throws InvalidInputException, SQLException {
        return STORAGE_SERVICE.save(storage);
    }

    public void delete(long id) throws InvalidInputException, SQLException {
        STORAGE_SERVICE.delete(id);
    }

    public Storage update(Storage storage) throws InvalidInputException, SQLException {
        return STORAGE_SERVICE.update(storage);
    }

    public Storage findById(long id) throws InvalidInputException, SQLException {
        return STORAGE_SERVICE.findById(id);
    }
}