package exception;

public class NotReadyEventException extends RuntimeException {
    public NotReadyEventException(String message) {
        super(message);
    }
}
