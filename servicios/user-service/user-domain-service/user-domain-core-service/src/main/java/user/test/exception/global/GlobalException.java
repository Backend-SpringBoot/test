package user.test.exception.global;

public class GlobalException extends RuntimeException {

  public GlobalException(String message) {
    super(message);
  }

  public GlobalException(String message, Throwable cause) {
    super(message, cause);
  }
}
