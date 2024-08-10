package test.domain.application.ports.input.client.command;

import org.springframework.http.ResponseEntity;
import test.test.record.ExceptionResponseRecord;
import test.test.record.request.ClientRequestRecord;

public interface ClientCommandService {

    ResponseEntity<ExceptionResponseRecord> createOrUpdate(ClientRequestRecord clientRequestRecord);

    ResponseEntity<ExceptionResponseRecord> delete(String idNumber);

}
