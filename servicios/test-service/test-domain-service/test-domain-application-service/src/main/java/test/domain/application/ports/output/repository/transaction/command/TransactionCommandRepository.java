package test.domain.application.ports.output.repository.transaction.command;

import org.springframework.http.ResponseEntity;
import test.test.record.ExceptionResponseRecord;
import test.test.record.request.TransactionRequestRecord;

public interface TransactionCommandRepository {

    ResponseEntity<ExceptionResponseRecord> createOrUpdate(
            TransactionRequestRecord transactionRequestRecord);

    void delete(Integer id);
}
