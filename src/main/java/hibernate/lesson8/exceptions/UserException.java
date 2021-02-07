package hibernate.lesson8.exceptions;

public class UserException extends RuntimeException {
    public UserException(String message) {
        super(message);
    }
}
