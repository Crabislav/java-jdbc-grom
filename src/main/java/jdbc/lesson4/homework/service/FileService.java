package jdbc.lesson4.homework.service;

import jdbc.lesson4.homework.dao.FileDAO;
import jdbc.lesson4.homework.dao.StorageDAO;
import jdbc.lesson4.homework.exception.IncompatibleFormatsException;
import jdbc.lesson4.homework.exception.InvalidInputException;
import jdbc.lesson4.homework.exception.NoSpaceException;
import jdbc.lesson4.homework.model.File;
import jdbc.lesson4.homework.model.Storage;

import java.sql.SQLException;
import java.util.List;

public class FileService {
    private final static FileDAO FILE_DAO = new FileDAO();

    public File put(Storage storage, File file) throws Exception {
        FileServiceValidator.validatePut(storage, file);

        file.setStorage(storage);
        return FILE_DAO.save(file);
    }

    public List<File> putAll(Storage storage, List<File> files) throws Exception {
        if (files.isEmpty()) {
            return files;
        }

        FileServiceValidator.validatePutAll(storage, files);

        for (File file : files) {
            file.setStorage(storage);
        }
        return FILE_DAO.save(files);
    }

    public void delete(Storage storage, File file) throws InvalidInputException, SQLException {
        if (file == null || storage == null) {
            throw new InvalidInputException("Input can't be null");
        }

        FileServiceValidator.validateDelete(storage, file);

        FILE_DAO.delete(file.getId());
    }

    public File transferFile(Storage storageFrom, Storage storageTo, long id) throws Exception {
        File file = FILE_DAO.findById(id);
        FileServiceValidator.validateTransferFile(storageFrom, storageTo, file);

        file.setStorage(storageTo);
        return FILE_DAO.update(file);
    }

    public void transferAll(Storage storageFrom, Storage storageTo) throws Exception {
        FileServiceValidator.validateTransferAll(storageFrom, storageTo);
        FILE_DAO.updateStorageForFiles(storageFrom, storageTo);
    }


}