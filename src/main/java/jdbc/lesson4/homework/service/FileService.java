package jdbc.lesson4.homework.service;

import jdbc.lesson4.homework.dao.FileDAO;
import jdbc.lesson4.homework.dao.StorageDAO;
import jdbc.lesson4.homework.exception.IncompatibleFormatsException;
import jdbc.lesson4.homework.exception.InvalidInputException;
import jdbc.lesson4.homework.exception.NoSpaceException;
import jdbc.lesson4.homework.model.File;
import jdbc.lesson4.homework.model.Storage;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class FileService {
    private final static FileDAO FILE_DAO = new FileDAO();
    private final static StorageDAO STORAGE_DAO = new StorageDAO();

    public File put(Storage storage, File file) throws Exception {
        validateFile(file);
        checkAmountOfStoragesForFile(file);

        validateStorage(storage);
        checkIfStorageExists(storage);

        checkFileFormat(storage.getFormatsSupported().split(", "), file);
        checkStorageForFreeSpace(storage, file.getSize());

        file.setStorage(storage);
        return FILE_DAO.save(file);
    }

    public List<File> putAll(Storage storage, List<File> files) throws Exception {
        validateStorage(storage);

        if (files == null || files.isEmpty()) {
            throw new InvalidInputException("Input files can't be null or empty");
        }

        checkIfStorageExists(storage);

        String[] storageFormats = storage.getFormatsSupported().split(", ");

        long filesSize = 0;
        for (File file : files) {
            validateFile(file);
            checkAmountOfStoragesForFile(file);
            checkFileFormat(storageFormats, file);
            filesSize += file.getSize();
        }

        checkStorageForFreeSpace(storage, filesSize);

        for (File file : files) {
            file.setStorage(storage);
        }

        return FILE_DAO.save(files);
    }

    public void delete(Storage storage, File file) throws InvalidInputException, SQLException {
        validateStorage(storage);
        validateFile(file);

        checkStorageForFile(storage, file);

        FILE_DAO.delete(file.getId());
    }

    public void transferAll(Storage storageFrom, Storage storageTo) throws Exception {
        validateStorage(storageFrom);
        validateStorage(storageTo);
        checkIfStorageExists(storageTo);

        checkStorageForFreeSpace(storageTo, storageFrom.getStorageMaxSize());
        checkStorageFilesFormats(storageFrom, storageTo);

        FILE_DAO.updateStorageForFiles(storageFrom, storageTo);
    }

    public File transferFile(Storage storageFrom, Storage storageTo, long id) throws Exception {
        validateStorage(storageFrom);
        validateStorage(storageTo);
        checkIfStorageExists(storageTo);

        if (id < 0) {
            throw new InvalidInputException("Id must be equals 0 or greater");
        }

        File file = FILE_DAO.findById(id);
        if (file == null) {
            throw new InvalidInputException("File (id=" + id + " is absent");
        }

        checkStorageForFile(storageFrom, file);
        checkFileFormat(storageTo.getFormatsSupported().split(", "), file);
        checkStorageForFreeSpace(storageTo, file.getSize());

        file.setStorage(storageTo);
        return FILE_DAO.update(file);
    }

    private void checkStorageFilesFormats(Storage storageFrom, Storage storageTo) throws SQLException,
            IncompatibleFormatsException {
        List<String> toFormats = Arrays.asList(storageTo.getFormatsSupported().split(", "));

        for (String fromFormat : storageFrom.getFormatsSupported().split(", ")) {
            if (toFormats.contains(fromFormat)) {
                continue;
            }

            if (!STORAGE_DAO.getFilesByFormat(storageFrom, fromFormat).isEmpty()) {
                throw new IncompatibleFormatsException("Unable to transfer all files from storage(id=" + storageFrom.getId() +
                        ") to storage(id=" + storageTo.getId() + ")");
            }
        }
    }

    private void checkStorageForFreeSpace(Storage storage, long size) throws NoSpaceException {
        if (size > storage.getStorageMaxSize()) {
            throw new NoSpaceException("Storage (id=" + storage.getId() + ") has no space available");
        }
    }

    private void checkFileFormat(String[] formats, File file) throws IncompatibleFormatsException {
        for (String format : formats) {
            if (format.equals(file.getFormat())) {
                return;
            }
        }

        throw new IncompatibleFormatsException("File (id=" + file.getId() + ") has invalid format");
    }

    private void checkStorageForFile(Storage storage, File file) throws InvalidInputException {
        if (file.getStorage().getId() != storage.getId()) {
            throw new InvalidInputException("Invalid file's(id=" + file.getId()
                    + ") storage(id=" + storage.getId() + ")");
        }
    }

    private void checkAmountOfStoragesForFile(File file) throws InvalidInputException {
        if (file.getStorage() != null) {
            throw new InvalidInputException("File (id=" + file.getId() + " can't have more than one storage");
        }
    }

    static void validateStorage(Storage storage) throws InvalidInputException {
        if (storage == null) {
            throw new InvalidInputException("Input can't be null");
        }

        if (storage.getId() < 0) {
            throw new InvalidInputException("Id must be equals 0 or greater");
        }

        if (storage.getStorageMaxSize() <= 0) {
            throw new InvalidInputException("Size must be equals 0 or greater");
        }

        if (storage.getStorageCountry() == null || storage.getStorageCountry().isEmpty()) {
            throw new InvalidInputException("Storage's country can't be null or empty");
        }
    }

    static void validateFile(File file) throws InvalidInputException {
        if (file == null) {
            throw new InvalidInputException("Input can't be null");
        }

        if (file.getId() < 0) {
            throw new InvalidInputException("Id must be equals 0 or greater");
        }

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