package test.dataaccess.adapter.account.query;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.dataaccess.entities.AccountEntity;
import test.dataaccess.mappers.AccountMapper;
import test.dataaccess.repository.AccountJpaRepository;
import test.domain.application.ports.output.repository.account.query.AccountQueryRepository;
import test.test.record.ExceptionResponseRecord;
import test.test.record.response.AccountResponseRecord;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountQueryRepositoryImpl implements AccountQueryRepository {

    private final AccountJpaRepository accountJpaRepository;

    @Override
    public ResponseEntity<ExceptionResponseRecord> getAccount(String id) {
        return accountJpaRepository.findByAccountNumber(id)
                .map(this::getAccountResponse)
                .orElseGet(() -> createErrorResponse("No se encontró ningún registro"));
    }

    @Override
    public ResponseEntity<List<ExceptionResponseRecord>> getAccounts() {
        List<AccountEntity> accountEntities = accountJpaRepository.findAll();
        List<AccountResponseRecord> data = AccountMapper.INSTANCE.entitiesToResponseRecords(
                accountEntities);
        return new ResponseEntity<>(List.of(CreateException("Correcto", data)), HttpStatus.OK);
    }

    private ResponseEntity<ExceptionResponseRecord> getAccountResponse(AccountEntity account) {
        AccountResponseRecord data = AccountMapper.INSTANCE.entityToResponseRecord(account);
        return createSuccessResponse("Correcto!", data);
    }

    private ExceptionResponseRecord CreateException(String message, Object data) {
        return ExceptionResponseRecord.builder()
                .httpStatus(HttpStatus.ACCEPTED)
                .message(message)
                .data(data)
                .build();
    }

    private ResponseEntity<ExceptionResponseRecord> createErrorResponse(String message) {
        return new ResponseEntity<>(CreateException(message, null), HttpStatus.OK);
    }

    private ResponseEntity<ExceptionResponseRecord> createSuccessResponse(String message,
                                                                          AccountResponseRecord data) {
        return new ResponseEntity<>(CreateException(message, data), HttpStatus.OK);
    }
}
