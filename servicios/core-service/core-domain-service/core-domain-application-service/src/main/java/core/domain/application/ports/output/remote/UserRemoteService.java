package core.domain.application.ports.output.remote;

import core.record.ExceptionResponseRecord;
import org.springframework.http.ResponseEntity;

public interface UserRemoteService {

  ResponseEntity<ExceptionResponseRecord> getUser(String id);

  ResponseEntity<ExceptionResponseRecord> getClient(String id);

}
