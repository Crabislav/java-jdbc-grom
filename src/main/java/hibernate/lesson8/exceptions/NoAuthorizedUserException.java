package hibernate.lesson8.exceptions;

public class NoAuthorizedUserException extends UserException {

    public NoAuthorizedUserException(String message) {
        super(message);
    }
}
