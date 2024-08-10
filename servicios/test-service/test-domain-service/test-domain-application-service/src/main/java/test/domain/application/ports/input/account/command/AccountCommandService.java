package test.domain.application.ports.input.account.command;

import org.springframework.http.ResponseEntity;
import test.test.record.ExceptionResponseRecord;
import test.test.record.request.AccountRequestRecord;

public interface AccountCommandService {

    ResponseEntity<ExceptionResponseRecord> createOrUpdate(AccountRequestRecord accountRequestRecord);

    ResponseEntity<ExceptionResponseRecord> delete(Integer id);

}
