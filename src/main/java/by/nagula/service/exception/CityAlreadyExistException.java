package by.nagula.service.exception;

public class CityAlreadyExistException extends RuntimeException {

    public CityAlreadyExistException() {
    }

    public CityAlreadyExistException(String message) {
        super(message);
    }

    public CityAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public CityAlreadyExistException(Throwable cause) {
        super(cause);
    }

    public CityAlreadyExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
