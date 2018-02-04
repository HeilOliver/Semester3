package at.fhv.ohe.graph.exception;

public class GraphParseException extends Exception {

    public GraphParseException() {
    }

    public GraphParseException(String message) {
        super(message);
    }

    public GraphParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public GraphParseException(Throwable cause) {
        super(cause);
    }

    public GraphParseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
