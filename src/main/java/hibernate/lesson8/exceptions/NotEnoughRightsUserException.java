package hibernate.lesson8.exceptions;

public class NotEnoughRightsUserException extends UserException {
    public NotEnoughRightsUserException(String message) {
        super(message);
    }
}
