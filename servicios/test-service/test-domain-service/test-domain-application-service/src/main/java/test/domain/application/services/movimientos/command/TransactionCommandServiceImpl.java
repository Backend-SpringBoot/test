package test.domain.application.services.movimientos.command;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.domain.application.ports.input.transaction.command.TransactionCommandService;
import test.domain.application.ports.output.repository.transaction.command.TransactionCommandRepository;
import test.test.record.ExceptionResponseRecord;
import test.test.record.request.TransactionRequestRecord;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TransactionCommandServiceImpl implements TransactionCommandService {

    private final TransactionCommandRepository transactionCommandRepository;

    @Override
    @Transactional
    public ResponseEntity<ExceptionResponseRecord> createOrUpdate(
            TransactionRequestRecord transactionRequestRecord) {
        return transactionCommandRepository.createOrUpdate(transactionRequestRecord);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        transactionCommandRepository.delete(id);
    }
}
