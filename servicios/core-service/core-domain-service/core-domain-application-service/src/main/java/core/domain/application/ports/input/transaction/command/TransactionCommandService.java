package core.domain.application.ports.input.transaction.command;

import org.springframework.http.ResponseEntity;
import core.record.ExceptionResponseRecord;
import core.record.request.TransactionRequestRecord;

public interface TransactionCommandService {

    ResponseEntity<ExceptionResponseRecord> createOrUpdate(
            TransactionRequestRecord transactionRequestRecord);

    void delete(Integer id);

}
