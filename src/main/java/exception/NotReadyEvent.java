package exception;

public class NotReadyEvent extends RuntimeException {
    public NotReadyEvent(String message) {
        super(message);
    }
}
