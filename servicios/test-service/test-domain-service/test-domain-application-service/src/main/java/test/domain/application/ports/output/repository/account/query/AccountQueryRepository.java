package test.domain.application.ports.output.repository.account.query;

import org.springframework.http.ResponseEntity;
import test.test.record.ExceptionResponseRecord;

import java.util.List;

public interface AccountQueryRepository {

    ResponseEntity<ExceptionResponseRecord> getAccount(String id);

    ResponseEntity<List<ExceptionResponseRecord>> getAccounts();
}
