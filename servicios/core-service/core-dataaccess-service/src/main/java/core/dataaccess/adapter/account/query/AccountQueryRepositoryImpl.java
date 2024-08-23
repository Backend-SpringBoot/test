package core.dataaccess.adapter.account.query;

import core.dataaccess.entities.AccountEntity;
import core.dataaccess.mappers.AccountMapper;
import core.dataaccess.repository.AccountJpaRepository;
import core.domain.application.ports.output.repository.account.query.AccountQueryRepository;
import core.record.ExceptionResponseRecord;
import core.record.response.AccountResponseRecord;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

  @Override
  public ResponseEntity<List<ExceptionResponseRecord>> findByIdNumber(String id) {
    List<AccountEntity> accounts = accountJpaRepository.findByIdNumber(id);
    List<AccountResponseRecord> data = AccountMapper.INSTANCE.entitiesToResponseRecords(
        accounts);
    return new ResponseEntity<>(List.of(CreateException("Correcto", data)), HttpStatus.OK);
  }

  private ResponseEntity<ExceptionResponseRecord> getAccountResponse(AccountEntity account) {
    AccountResponseRecord data = AccountMapper.INSTANCE.entityToResponseRecord(account);
    return createSuccessResponse("Correcto!", data);
  }

  private ExceptionResponseRecord CreateException(String message, Object data) {
    return ExceptionResponseRecord.builder()
        .httpStatus(200)
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
