package core.domain.application.ports.output.repository.account.query;

import java.util.List;
import org.springframework.http.ResponseEntity;
import core.record.ExceptionResponseRecord;

public interface AccountQueryRepository {

    ResponseEntity<ExceptionResponseRecord> getAccount(String id);

    ResponseEntity<List<ExceptionResponseRecord>> getAccounts();
}
