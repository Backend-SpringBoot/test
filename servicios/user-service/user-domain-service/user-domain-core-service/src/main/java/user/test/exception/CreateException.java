package user.test.exception;

import user.test.exception.global.GlobalException;

public class CreateException extends GlobalException {

    public CreateException(String message) {
        super(message);
    }

    public CreateException(String message, Throwable cause) {
        super(message, cause);
    }
}
