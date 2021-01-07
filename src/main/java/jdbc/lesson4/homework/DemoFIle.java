package jdbc.lesson4.homework;

import jdbc.lesson4.homework.controller.FileController;
import jdbc.lesson4.homework.controller.StorageController;
import jdbc.lesson4.homework.exception.IncompatibleFormatsException;
import jdbc.lesson4.homework.exception.InvalidInputException;
import jdbc.lesson4.homework.model.File;
import jdbc.lesson4.homework.model.Storage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DemoFIle {
    private static final FileController FILE_CONTROLLER = new FileController();
    private static final StorageController STORAGE_CONTROLLER = new StorageController();

    private static final Storage storage1 = new Storage(3, "txt, jpg", "Ukraine", 500);
    private static final Storage storage2 = new Storage(4, "txt, pdf", "Ukraine", 500);

    private static final File file1 = new File(1, "test1", "txt", 50);
    private static final File file2 = new File(2, "test2", "pdf", 50);
    private static final File file3 = new File(3, "test3", "jpg", 50);

    public static void main(String[] args) throws Exception {
        initStorages();
//
//        testPutFile();
//        testDeleteFile();
//        testTransferFile();
//
//        FILE_CONTROLLER.transferAll(storage1, storage2);
//
//        testPutAllValidValues();
//        testPutAllInvalidFormat();
//        testPutAllInvalidFormatForStorageTo();

        FILE_CONTROLLER.putAll(storage1, generateFiles(1, "txt", "big_size", 200));

    }


    public static List<File> generateFiles(long id, String format, String name, long size) {
        List<File> files = new ArrayList<>();
        long amountToGenerate = id + 5;
        for (; id < amountToGenerate; id++) {
            files.add(new File(id, name + id, format, size));
        }

        return files;
    }

    private static void testPutAllInvalidFormatForStorageTo() throws Exception {
        List<File> files = new ArrayList<>();
        for (long id = 30; id < 35; id++) {
            files.add(new File(id, "putAll" + id, "jpg", 50));
        }

        FILE_CONTROLLER.putAll(storage1, files);
        FILE_CONTROLLER.transferAll(storage1, storage2);
    }

    private static void testPutAllInvalidFormat() throws Exception {
        List<File> files = new ArrayList<>();
        for (long id = 70; id < 75; id++) {
            files.add(new File(id, "putAll_invalid" + id, "pdf", 50));
        }

        FILE_CONTROLLER.putAll(storage1, files);
    }

    private static void testPutAllValidValues() throws Exception {
        List<File> files = new ArrayList<>();
        for (long id = 6; id < 12; id++) {
            files.add(new File(id, "putAll" + id, "txt", 50));
        }

        FILE_CONTROLLER.putAll(storage1, files);
    }

    private static void testTransferFile() throws Exception {
        FILE_CONTROLLER.put(storage1, file1);
        FILE_CONTROLLER.transferFile(storage1, storage2, file1.getId());
        FILE_CONTROLLER.delete(storage2, file1);
    }

    private static void testDeleteFile() throws Exception {
        FILE_CONTROLLER.put(storage1, file1);
        FILE_CONTROLLER.delete(storage1, file1);

        //non-existing file
        FILE_CONTROLLER.delete(storage1, file1);
    }

    private static void initStorages() throws InvalidInputException, SQLException {
        Storage[] storages = new Storage[]{storage1, storage2};
        for (Storage storage : storages) {
            STORAGE_CONTROLLER.save(storage);
        }
    }

    private static void testPutFile() throws Exception {
        File[] files = new File[]{file1, file2, file3};

        for (File file : files) {
            try {
                FILE_CONTROLLER.put(storage1, file);
            } catch (IncompatibleFormatsException e) {
                e.printStackTrace();
            }
        }
    }
}
