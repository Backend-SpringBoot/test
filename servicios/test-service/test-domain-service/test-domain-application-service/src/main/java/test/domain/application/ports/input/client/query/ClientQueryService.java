package test.domain.application.ports.input.client.query;

import java.util.List;

import org.springframework.http.ResponseEntity;
import test.test.record.ExceptionResponseRecord;

public interface ClientQueryService {

    ResponseEntity<ExceptionResponseRecord> getClient(String id);

    ResponseEntity<List<ExceptionResponseRecord>> getClients();

}
