package user.domain.application.ports.input.client.query;

import java.util.List;
import org.springframework.http.ResponseEntity;
import user.test.record.ExceptionResponseRecord;

public interface UserQueryService {

  ResponseEntity<ExceptionResponseRecord> getClient(String id);

  ResponseEntity<List<ExceptionResponseRecord>> getClients();

}
