package lesson4.homework;

import lesson4.homework.controller.FileController;
import lesson4.homework.controller.StorageController;
import lesson4.homework.dao.StorageDAO;
import lesson4.homework.exception.InvalidInputException;
import lesson4.homework.model.Storage;

import java.sql.SQLException;

public class DemoStorage {
    private static final StorageController STORAGE_CONTROLLER = new StorageController();
    private static final FileController FILE_CONTROLLER = new FileController();

    private static final long[] ID = new long[]{-1, 1, 5};

    public static void main(String[] args) throws Exception {
        Storage storage1 = new Storage(1, "txt, jpg", "Ukraine", 500);
        Storage storage2 = new Storage(2, "txt, pdf", "Ukraine", 500);
        Storage[] storages = new Storage[]{storage1, storage2};

        saveStorageTest(storages);
        findStorageByIdTest();

        testUpdateMaxSize(storage1);
        testUpdateCountry(storage1);
        testUpdateFormats(storage1);
        testUpdateId(storage1);
        deleteStorageTest(storages);

        FILE_CONTROLLER.putAll(storage1, DemoFIle.generateFiles(1, "txt", "test", 10));
        storage1.setStorageMaxSize(10);
        STORAGE_CONTROLLER.update(storage1);
    }

    private static void testUpdateId(Storage storage) throws SQLException {
        for (long id : ID) {
            try {
                storage.setId(id);
                STORAGE_CONTROLLER.update(storage);
                System.out.println(STORAGE_CONTROLLER.findById(storage.getId()));
            } catch (InvalidInputException e) {
                e.printStackTrace();
            }
        }
    }

    private static void testUpdateFormats(Storage storage) throws SQLException {
        String[] countries = new String[]{"pdf, wtf", null, "", "txt, jpg"};
        for (String country : countries) {
            try {
                storage.setFormatsSupported(country);
                STORAGE_CONTROLLER.update(storage);
                System.out.println(STORAGE_CONTROLLER.findById(storage.getId()));
            } catch (InvalidInputException e) {
                e.printStackTrace();
            }
        }
    }

    private static void testUpdateCountry(Storage storage) throws SQLException {
        String[] countries = new String[]{null, "Russia", "", "Ukraine"};
        for (String country : countries) {
            try {
                storage.setStorageCountry(country);
                STORAGE_CONTROLLER.update(storage);
                System.out.println(STORAGE_CONTROLLER.findById(storage.getId()));
            } catch (InvalidInputException e) {
                e.printStackTrace();
            }
        }
    }

    private static void testUpdateMaxSize(Storage storage) throws SQLException {
        long[] maxSizes = new long[]{600, 0, -400, 500};
        for (long size : maxSizes) {
            try {
                storage.setStorageMaxSize(size);
                STORAGE_CONTROLLER.update(storage);
                System.out.println(STORAGE_CONTROLLER.findById(storage.getId()));
            } catch (InvalidInputException e) {
                e.printStackTrace();
            }
        }
    }

    private static void findStorageByIdTest() throws SQLException {

        for (long id : ID) {
            try {
                Storage storage = STORAGE_CONTROLLER.findById(id);
                System.out.println(storage);
            } catch (InvalidInputException e) {
                e.printStackTrace();
            }
        }
    }

    private static void deleteStorageTest(Storage[] storages) throws InvalidInputException, SQLException {
        for (Storage storage : storages) {
            STORAGE_CONTROLLER.delete(storage.getId());
        }
    }

    private static void saveStorageTest(Storage[] storages) throws InvalidInputException, SQLException {
        for (Storage storage : storages) {
            STORAGE_CONTROLLER.save(storage);
        }
    }
}
