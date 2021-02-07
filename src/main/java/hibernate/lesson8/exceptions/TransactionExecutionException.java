package hibernate.lesson8.exceptions;

public class TransactionExecutionException extends Exception{
    public TransactionExecutionException(String message) {
        super(message);
    }
}
