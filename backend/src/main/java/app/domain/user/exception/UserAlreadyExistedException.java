package app.domain.user.exception;

public class UserAlreadyExistedException extends RuntimeException {
    public UserAlreadyExistedException() {
        super();
    }

    public UserAlreadyExistedException(String message) {
        super(message);
    }
}
