package src.main.java.com.trade_accounting.exceptions;

public class NotFoundEntityException extends RuntimeException {
    public NotFoundEntityException(String message) {
        super(message);
    }
}
