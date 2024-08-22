package core.domain.application.ports.output.repository.transaction.command;

import org.springframework.http.ResponseEntity;
import core.record.ExceptionResponseRecord;
import core.record.request.TransactionRequestRecord;

public interface TransactionCommandRepository {

    ResponseEntity<ExceptionResponseRecord> createOrUpdate(
            TransactionRequestRecord transactionRequestRecord);

    void delete(Integer id);
}
