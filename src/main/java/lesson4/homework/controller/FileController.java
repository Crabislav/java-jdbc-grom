package lesson4.homework.controller;

import lesson4.homework.exception.InvalidInputException;
import lesson4.homework.model.File;
import lesson4.homework.model.Storage;
import lesson4.homework.service.FileService;

import java.sql.SQLException;
import java.util.List;

public class FileController {
    private static final FileService FILE_SERVICE = new FileService();

    public File put(Storage storage, File file) throws Exception {
        return FILE_SERVICE.put(storage, file);
    }

    public List<File> putAll(Storage storage, List<File> files) throws Exception {
        return FILE_SERVICE.putAll(storage, files);
    }

    public void delete(Storage storage, File file) throws InvalidInputException, SQLException {
        FILE_SERVICE.delete(storage, file);
    }

    public void transferAll(Storage storageFrom, Storage storageTo) throws Exception {
        FILE_SERVICE.transferAll(storageFrom, storageTo);
    }

    public File transferFile(Storage storageFrom, Storage storageTo, long id) throws Exception {
        return FILE_SERVICE.transferFile(storageFrom, storageTo, id);
    }
}