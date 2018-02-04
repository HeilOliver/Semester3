package at.fhv.ohe.slider;

public class SliderMoveException extends RuntimeException {
    public SliderMoveException() {
        super();
    }

    public SliderMoveException(String message) {
        super(message);
    }

    public SliderMoveException(String message, Throwable cause) {
        super(message, cause);
    }

    public SliderMoveException(Throwable cause) {
        super(cause);
    }

    protected SliderMoveException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
