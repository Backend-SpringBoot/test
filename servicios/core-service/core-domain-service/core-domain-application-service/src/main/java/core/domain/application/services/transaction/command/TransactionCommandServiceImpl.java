package core.domain.application.services.transaction.command;

import core.domain.application.ports.input.transaction.command.TransactionCommandService;
import core.domain.application.ports.output.repository.transaction.command.TransactionCommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import core.record.ExceptionResponseRecord;
import core.record.request.TransactionRequestRecord;

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
