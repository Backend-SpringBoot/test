package core.dataaccess.adapter.transaction.command;

import core.dataaccess.entities.AccountEntity;
import core.dataaccess.entities.TransactionEntity;
import core.dataaccess.mappers.TransactionMapper;
import core.dataaccess.repository.AccountJpaRepository;
import core.dataaccess.repository.TransactionJpaRepository;
import core.domain.application.ports.output.remote.UserRemoteService;
import core.domain.application.ports.output.repository.account.command.AccountCommandRepository;
import core.domain.application.ports.output.repository.transaction.command.TransactionCommandRepository;
import core.record.ExceptionResponseRecord;
import core.record.request.AccountRequestRecord;
import core.record.request.TransactionRequestRecord;
import core.record.response.TransactionResponseRecord;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TransactionCommandRepositoryImpl implements TransactionCommandRepository {

  private final TransactionJpaRepository transactionJpaRepository;
  private final AccountJpaRepository accountJpaRepository;
  private final UserRemoteService userRemoteService;
  private final AccountCommandRepository accountCommandRepository;

  @Override
  @Transactional
  public ResponseEntity<ExceptionResponseRecord> createOrUpdate(
      TransactionRequestRecord transactionRequestRecord) {
    TransactionEntity entity;
    ExceptionResponseRecord client;
    AccountEntity account;
    double availableBalance;

    entity = TransactionMapper.INSTANCE.requestRecordToEntity(transactionRequestRecord);
    client = validateClient(transactionRequestRecord.idNumber());
    account = validateAccount(transactionRequestRecord.idNumber(),
        transactionRequestRecord.accountNumber(), transactionRequestRecord.accountType());

    if (client == null) {
      return new ResponseEntity<>(CreateException("Usuario no registrado", null), HttpStatus.OK);
    }
    if (account == null) {
      return new ResponseEntity<>(CreateException(
          "Cuenta no registrada para el usuario" + transactionRequestRecord.idNumber(), null),
          HttpStatus.OK);
    }
    entity.setInitialBalance(account.getInitialBalance());
    if (transactionRequestRecord.movementType().equals("Retiro")
        || transactionRequestRecord.movementType().equals("Deposito")) {
      availableBalance = calculateAvailableBalance(transactionRequestRecord, account);
      if (availableBalance == -1) {
        return new ResponseEntity<>(CreateException(
            "No existe saldo suficiente", null),
            HttpStatus.OK);
      }
      entity.setAvailableBalance(availableBalance);
      entity.setClient(transactionRequestRecord.idNumber());
      entity.setTransacitionDate(LocalDateTime.now());
      entity.setMovementType(transactionRequestRecord.movementType());
    } else {
      return new ResponseEntity<>(CreateException(
          "Tipo de transacción no sportada", null),
          HttpStatus.OK);
    }

    TransactionResponseRecord saved = TransactionMapper.INSTANCE.entityToResponseRecord(
        transactionJpaRepository.save(entity));
    updateAccount(availableBalance, account);
    ExceptionResponseRecord response = CreateException(
        "Movimiento registrado exitosamente", saved);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @Override
  @Transactional
  public void delete(Integer id) {
    transactionJpaRepository.deleteById(id);
  }

  private ExceptionResponseRecord validateClient(String idNumber) {
    return userRemoteService.getClient(idNumber);
  }

  private AccountEntity validateAccount(String idNumber, String accountNumber, String accountType) {
    Optional<AccountEntity> accountOptional = accountJpaRepository.findAccountByUserAndAccountNumber(
        idNumber, accountNumber, accountType);
    return accountOptional.orElse(null);
  }

  private double calculateAvailableBalance(TransactionRequestRecord transactionRequestRecord,
      AccountEntity account) {
    double saldo;
    if (transactionRequestRecord.movementType().equals("Retiro")
        && account.getInitialBalance() >= transactionRequestRecord.movement()) {
      saldo = account.getInitialBalance() - transactionRequestRecord.movement();
      return saldo;
    } else if (transactionRequestRecord.movementType().equals("Retiro")
        && account.getInitialBalance() <= transactionRequestRecord.movement()) {
      return -1;
    } else if (transactionRequestRecord.movementType().equals("Deposito")) {
      saldo = account.getInitialBalance() + transactionRequestRecord.movement();
      return saldo;
    } else {
      return 0;
    }
  }

  private void updateAccount(double availableBalance, AccountEntity account) {
    accountCommandRepository.createOrUpdate(AccountRequestRecord.builder()
        .accountNumber(account.getAccountNumber())
        .accountType(account.getAccountType())
        .initialBalance(availableBalance)
        .status(account.getEstado())
        .idNumber(account.getIdNumber())
        .build());
  }

  private ExceptionResponseRecord CreateException(String message, Object data) {
    return ExceptionResponseRecord.builder()
        .httpStatus(200)
        .message(message)
        .data(data)
        .build();
  }

}
