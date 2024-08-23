package user.domain.application.ports.output.repository.client.query;

import java.util.List;
import org.springframework.http.ResponseEntity;
import user.test.record.ExceptionResponseRecord;

public interface ClientQueryRepository {

  ResponseEntity<ExceptionResponseRecord> getClient(String id);

  ResponseEntity<List<ExceptionResponseRecord>> getCliets();
}
