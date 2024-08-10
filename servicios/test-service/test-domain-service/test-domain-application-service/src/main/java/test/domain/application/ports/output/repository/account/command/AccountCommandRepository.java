package test.domain.application.ports.output.repository.account.command;

import org.springframework.http.ResponseEntity;
import test.test.record.ExceptionResponseRecord;
import test.test.record.request.AccountRequestRecord;

public interface AccountCommandRepository {

    ResponseEntity<ExceptionResponseRecord> createOrUpdate(AccountRequestRecord accountRequestRecord);

    ResponseEntity<ExceptionResponseRecord> delete(Integer id);
}
