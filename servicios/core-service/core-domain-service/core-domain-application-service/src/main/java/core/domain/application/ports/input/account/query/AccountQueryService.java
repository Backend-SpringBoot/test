package core.domain.application.ports.input.account.query;

import java.util.List;
import org.springframework.http.ResponseEntity;
import core.record.ExceptionResponseRecord;

public interface AccountQueryService {

    ResponseEntity<ExceptionResponseRecord> getAccount(String id);

    ResponseEntity<List<ExceptionResponseRecord>> getAccounts();

}
