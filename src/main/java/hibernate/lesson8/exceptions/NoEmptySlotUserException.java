package hibernate.lesson8.exceptions;

public class NoEmptySlotUserException extends UserException{
    public NoEmptySlotUserException(String message) {
        super(message);
    }
}
