package lesson4.homework.controller;

import lesson4.homework.model.Storage;
import lesson4.homework.service.StorageService;

public class StorageController {
    private static final StorageService STORAGE_SERVICE = new StorageService();

    public Storage save(Storage storage) {
        return STORAGE_SERVICE.save(storage);
    }

    public void delete(long id) {
        STORAGE_SERVICE.delete(id);
    }


    public Storage update(Storage storage) {
        return STORAGE_SERVICE.update(storage);
    }


    public Storage findById(long id) {
        return STORAGE_SERVICE.findById(id);
    }
}