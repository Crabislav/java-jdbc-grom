package lesson4.homework.controller;

import lesson4.homework.model.File;
import lesson4.homework.model.Storage;

import java.util.List;

public class FileController {
    //добавляет файл в хранилище. гарантируется что файл уже есть в условной БД
    public File put(Storage storage, File file) {
        return null;
    }

    //добавляет список файлов в хранилище. гарантируется что файл уже есть в условной БД
    public void putAll(Storage storage, List<File> files) {

    }

    public void delete(Storage storage, File file) {
    }

    //трансфер всех файлов
    public void transferAll(Storage storageFrom, Storage storageTo) {

    }

    //трансфер файла с хранилища storageFrom по его айди.
    public File transferFile(Storage storageFrom, Storage storageTo, long id) {
        return null;
    }
}