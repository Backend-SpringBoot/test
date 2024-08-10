package test.domain.application.ports.input.transaction.command;

import org.springframework.http.ResponseEntity;
import test.test.record.ExceptionResponseRecord;
import test.test.record.request.TransactionRequestRecord;

public interface TransactionCommandService {

    ResponseEntity<ExceptionResponseRecord> createOrUpdate(
            TransactionRequestRecord transactionRequestRecord);

    void delete(Integer id);

}
