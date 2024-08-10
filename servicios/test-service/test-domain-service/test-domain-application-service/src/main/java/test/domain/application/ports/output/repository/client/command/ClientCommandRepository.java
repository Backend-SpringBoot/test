package test.domain.application.ports.output.repository.client.command;

import org.springframework.http.ResponseEntity;
import test.test.record.ExceptionResponseRecord;
import test.test.record.request.ClientRequestRecord;

public interface ClientCommandRepository {

    ResponseEntity<ExceptionResponseRecord> createOrUpdate(ClientRequestRecord clientRequestRecord);

    ResponseEntity<ExceptionResponseRecord> delete(String idNumber);
}
