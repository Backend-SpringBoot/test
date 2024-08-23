package core.dataaccess.adapter.account.command;

import core.dataaccess.entities.AccountEntity;
import core.dataaccess.mappers.AccountMapper;
import core.dataaccess.repository.AccountJpaRepository;
import core.domain.application.ports.output.remote.UserRemoteService;
import core.domain.application.ports.output.repository.account.command.AccountCommandRepository;
import core.record.ExceptionResponseRecord;
import core.record.request.AccountRequestRecord;
import core.record.response.AccountResponseRecord;
import core.record.response.ClientResponseRecord;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.locks.StampedLock;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountCommandRepositoryImpl implements AccountCommandRepository {

  private final AccountJpaRepository accountJpaRepository;
  private final UserRemoteService userRemoteService;

  @Override
  @Transactional
  public ResponseEntity<ExceptionResponseRecord> createOrUpdate(
      AccountRequestRecord accountRequestRecord) {
    if (!isValidTransactionType(getSupportedTransactionTypes(),
        accountRequestRecord.accountType())) {
      return createErrorResponse("Tipo de transacción no soportada");
    }
    ExceptionResponseRecord clientResponse = userRemoteService.getClient(
        accountRequestRecord.idNumber());
    ClientResponseRecord client = toClientResponseRecord(clientResponse.data());
    if (Objects.isNull(client)) {
      return createErrorResponse("Usuario no registrado");
    }
    AccountEntity entity = getAccountEntity(accountRequestRecord);
    AccountResponseRecord saved = AccountMapper.INSTANCE.entityToResponseRecord(
        accountJpaRepository.save(entity));
    return createSuccessResponse("Cuenta almacenada para el usuario: " + client.name(),
        saved);
  }

  @Override
  @Transactional
  public ResponseEntity<ExceptionResponseRecord> delete(String id) {
    return accountJpaRepository.findByAccountNumber(id)
        .map(this::deleteAccount)
        .orElseGet(() -> createErrorResponse("No se encontró ningún registro"));
  }

  private ClientResponseRecord toClientResponseRecord(Object data) {
    Map<String, Object> userMap = (Map<String, Object>) data;
    return ClientResponseRecord.builder()
        .name((String) userMap.get("name"))
        .idNumber((String) userMap.get("idNumber"))
        .build();
  }

  private ResponseEntity<ExceptionResponseRecord> deleteAccount(AccountEntity account) {
    accountJpaRepository.deleteById(account.getId());
    return createSuccessResponse("Cuenta : " + account.getAccountType() + " eliminada",
        AccountMapper.INSTANCE.entityToResponseRecord(account));
  }

  private ExceptionResponseRecord CreateException(String message, Object o) {
    return ExceptionResponseRecord.builder()
        .httpStatus(200)
        .message(message)
        .data(o)
        .build();
  }

  private AccountEntity updateExistingAccount(AccountRequestRecord requestRecord) {
    Optional<AccountEntity> accountOptional =
        accountJpaRepository
            .findByAccountNumber(requestRecord.accountNumber());
    AccountEntity existingEntity = accountOptional.get();
    final StampedLock lock = existingEntity.getLock();
    long stamp = lock.writeLock();
    try {
      existingEntity.setAccountNumber(requestRecord.accountNumber());
      existingEntity.setAccountType(requestRecord.accountType());
      existingEntity.setInitialBalance(requestRecord.initialBalance());
      existingEntity.setEstado(requestRecord.status());

    } finally {
      lock.unlockWrite(stamp);
    }
    return existingEntity;
  }

  private ResponseEntity<ExceptionResponseRecord> createErrorResponse(String message) {
    return new ResponseEntity<>(CreateException(message, null), HttpStatus.OK);
  }

  private ResponseEntity<ExceptionResponseRecord> createSuccessResponse(String message,
      AccountResponseRecord saved) {
    return new ResponseEntity<>(CreateException(message, saved), HttpStatus.OK);
  }

  private AccountEntity getAccountEntity(AccountRequestRecord accountRequestRecord) {
    Optional<AccountEntity> accountOptional = accountJpaRepository.findByAccountNumber(
        accountRequestRecord.accountNumber());
    return accountOptional.isPresent() ? updateExistingAccount(accountRequestRecord)
        : createNewAccount(accountRequestRecord);
  }

  private AccountEntity createNewAccount(AccountRequestRecord accountRequestRecord) {
    AccountEntity entity = AccountMapper.INSTANCE.requestRecordToEntity(accountRequestRecord);
    return entity;
  }

  public boolean isValidTransactionType(List<String> TRANSACTIONS_TYPE, String accountType) {
    return TRANSACTIONS_TYPE.contains(accountType);
  }

  private List<String> getSupportedTransactionTypes() {
    return Arrays.asList("Ahorros", "Corriente");
  }
}


