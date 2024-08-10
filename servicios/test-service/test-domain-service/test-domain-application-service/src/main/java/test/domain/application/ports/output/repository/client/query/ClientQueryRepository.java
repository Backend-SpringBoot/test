package test.domain.application.ports.output.repository.client.query;

import org.springframework.http.ResponseEntity;
import test.test.record.ExceptionResponseRecord;

import java.util.List;

public interface ClientQueryRepository {

    ResponseEntity<ExceptionResponseRecord> getClient(String id);

    ResponseEntity<List<ExceptionResponseRecord>> getCliets();
}
