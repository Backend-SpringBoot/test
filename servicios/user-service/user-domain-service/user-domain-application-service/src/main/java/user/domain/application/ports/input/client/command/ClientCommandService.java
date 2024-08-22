package user.domain.application.ports.input.client.command;

import org.springframework.http.ResponseEntity;
import user.test.record.ExceptionResponseRecord;
import user.test.record.request.ClientRequestRecord;

public interface ClientCommandService {

    ResponseEntity<ExceptionResponseRecord> createOrUpdate(ClientRequestRecord clientRequestRecord);

    ResponseEntity<ExceptionResponseRecord> delete(String idNumber);

}
