package lesson4.homework.service;

import lesson4.homework.dao.FileDao;
import lesson4.homework.exception.InvalidInputException;
import lesson4.homework.model.File;

public class FileService implements Service<File> {
    private final static FileDao fileDao = new FileDao();

    @Override
    public File save(File file) throws InvalidInputException {
        validateFile(file);
        return fileDao.save(file);
    }

    @Override
    public void delete(long id) throws InvalidInputException {
        checkId(id);
        fileDao.delete(id);
    }

    @Override
    public File update(File file) throws InvalidInputException {
        validateFile(file);
        return fileDao.update(file);
    }

    @Override
    public File findById(long id) throws InvalidInputException {
        checkId(id);
        return fileDao.findById(id);
    }

    private void validateFile(File file) throws InvalidInputException {
        checkForNull(file);
        checkId(file.getId());
        checkForNull(file.getStorage());

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
}
