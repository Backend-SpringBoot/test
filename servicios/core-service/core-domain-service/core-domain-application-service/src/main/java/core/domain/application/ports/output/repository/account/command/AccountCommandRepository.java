package core.domain.application.ports.output.repository.account.command;

import org.springframework.http.ResponseEntity;
import core.record.ExceptionResponseRecord;
import core.record.request.AccountRequestRecord;

public interface AccountCommandRepository {

  ResponseEntity<ExceptionResponseRecord> createOrUpdate(AccountRequestRecord accountRequestRecord);

  ResponseEntity<ExceptionResponseRecord> delete(String id);
}
