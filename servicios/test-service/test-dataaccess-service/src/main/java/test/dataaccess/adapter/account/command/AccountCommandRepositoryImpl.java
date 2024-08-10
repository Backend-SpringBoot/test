package test.dataaccess.adapter.account.command;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.dataaccess.entities.AccountEntity;
import test.dataaccess.entities.ClientEntity;
import test.dataaccess.mappers.AccountMapper;
import test.dataaccess.repository.AccountJpaRepository;
import test.dataaccess.repository.ClientJpaRepository;
import test.domain.application.ports.output.repository.account.command.AccountCommandRepository;
import test.test.record.ExceptionResponseRecord;
import test.test.record.request.AccountRequestRecord;
import test.test.record.response.AccountResponseRecord;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.StampedLock;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountCommandRepositoryImpl implements AccountCommandRepository {

    private final AccountJpaRepository accountJpaRepository;
    private final ClientJpaRepository clientJpaRepository;

    @Override
    @Transactional
    public ResponseEntity<ExceptionResponseRecord> createOrUpdate(
            AccountRequestRecord accountRequestRecord) {

        if (!isValidTransactionType(getSupportedTransactionTypes(), accountRequestRecord.accountType())) {
            return createErrorResponse("Tipo de transacción no soportada");
        }

        Optional<ClientEntity> clientOptional = clientJpaRepository.findByIdNumber(
                accountRequestRecord.clientIdNumber());
        if (clientOptional.isEmpty()) {
            return createErrorResponse("Usuario no registrado");
        }

        ClientEntity client = clientOptional.get();
        AccountEntity entity = getAccountEntity(accountRequestRecord, client);

        AccountResponseRecord saved = AccountMapper.INSTANCE.entityToResponseRecord(
                accountJpaRepository.save(entity));
        return createSuccessResponse("Cuenta almacenada para el usuario: " + client.getName(),
                saved);
    }

    @Override
    @Transactional
    public ResponseEntity<ExceptionResponseRecord> delete(Integer id) {
        return accountJpaRepository.findById(id)
                .map(this::deleteAccount)
                .orElseGet(() -> createErrorResponse("No se encontró ningún registro"));
    }

    private ResponseEntity<ExceptionResponseRecord> deleteAccount(AccountEntity account) {
        accountJpaRepository.deleteById(account.getId());
        return createSuccessResponse("Cuenta : " + account.getAccountType() + " eliminada",
                AccountMapper.INSTANCE.entityToResponseRecord(account));
    }

    private ExceptionResponseRecord CreateException(String message, Object o) {
        return ExceptionResponseRecord.builder()
                .httpStatus(HttpStatus.ACCEPTED)
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

    private AccountEntity getAccountEntity(AccountRequestRecord accountRequestRecord,
                                           ClientEntity client) {
        Optional<AccountEntity> accountOptional = accountJpaRepository.findByAccountNumber(
                accountRequestRecord.accountNumber());
        return accountOptional.isPresent() ? updateExistingAccount(accountRequestRecord)
                : createNewAccount(accountRequestRecord, client);
    }

    private AccountEntity createNewAccount(AccountRequestRecord accountRequestRecord,
                                           ClientEntity client) {
        AccountEntity entity = AccountMapper.INSTANCE.requestRecordToEntity(accountRequestRecord);
        entity.setClient(client);
        return entity;
    }

    public boolean isValidTransactionType(List<String> TRANSACTIONS_TYPE, String accountType) {
        return TRANSACTIONS_TYPE.contains(accountType);
    }

    private List<String> getSupportedTransactionTypes() {
        return Arrays.asList("Ahorros", "Corriente");
    }
}
