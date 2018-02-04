package at.fhv.ohe.slider;

public class SliderCreateException extends RuntimeException {
    public SliderCreateException() {
        super();
    }

    public SliderCreateException(String message) {
        super(message);
    }

    public SliderCreateException(String message, Throwable cause) {
        super(message, cause);
    }

    public SliderCreateException(Throwable cause) {
        super(cause);
    }

    protected SliderCreateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
