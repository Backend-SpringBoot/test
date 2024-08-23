package core.record;

import lombok.Builder;

@Builder
public record ExceptionResponseRecord(
    Integer httpStatus,
    String message,
    Object data
) {

}
