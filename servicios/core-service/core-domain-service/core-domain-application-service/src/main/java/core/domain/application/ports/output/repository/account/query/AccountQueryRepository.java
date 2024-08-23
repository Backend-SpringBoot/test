package core.domain.application.ports.output.repository.account.query;

import core.record.ExceptionResponseRecord;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface AccountQueryRepository {

  ResponseEntity<ExceptionResponseRecord> getAccount(String id);

  ResponseEntity<List<ExceptionResponseRecord>> getAccounts();

  ResponseEntity<List<ExceptionResponseRecord>> findByIdNumber(String id);
}
