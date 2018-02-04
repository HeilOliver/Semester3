package at.fhv.ohe.eightTile.Field;

public class FieldCreateException extends RuntimeException {

    private FieldCreateException() {
    }

    public FieldCreateException(String message) {
        super(message);
    }

    private FieldCreateException(String message, Throwable cause) {
        super(message, cause);
    }

    private FieldCreateException(Throwable cause) {
        super(cause);
    }

    private FieldCreateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
