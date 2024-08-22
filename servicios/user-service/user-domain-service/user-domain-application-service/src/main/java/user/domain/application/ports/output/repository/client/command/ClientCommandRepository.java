package user.domain.application.ports.output.repository.client.command;

import org.springframework.http.ResponseEntity;
import user.test.record.ExceptionResponseRecord;
import user.test.record.request.ClientRequestRecord;

public interface ClientCommandRepository {

    ResponseEntity<ExceptionResponseRecord> createOrUpdate(ClientRequestRecord clientRequestRecord);

    ResponseEntity<ExceptionResponseRecord> delete(String idNumber);
}
