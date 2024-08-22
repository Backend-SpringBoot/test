package core.domain.application.ports.input.account.command;

import org.springframework.http.ResponseEntity;
import core.record.ExceptionResponseRecord;
import core.record.request.AccountRequestRecord;

public interface AccountCommandService {

  ResponseEntity<ExceptionResponseRecord> createOrUpdate(AccountRequestRecord accountRequestRecord);

  ResponseEntity<ExceptionResponseRecord> delete(String id);

}
