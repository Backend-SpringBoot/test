package test.dataaccess.adapter.transaction.command;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.dataaccess.entities.AccountEntity;
import test.dataaccess.entities.ClientEntity;
import test.dataaccess.entities.TransactionEntity;
import test.dataaccess.mappers.TransactionMapper;
import test.dataaccess.repository.AccountJpaRepository;
import test.dataaccess.repository.ClientJpaRepository;
import test.dataaccess.repository.TransactionJpaRepository;
import test.domain.application.ports.output.repository.account.command.AccountCommandRepository;
import test.domain.application.ports.output.repository.transaction.command.TransactionCommandRepository;
import test.test.record.ExceptionResponseRecord;
import test.test.record.request.AccountRequestRecord;
import test.test.record.request.TransactionRequestRecord;
import test.test.record.response.TransactionResponseRecord;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TransactionCommandRepositoryImpl implements TransactionCommandRepository {

    private final TransactionJpaRepository transactionJpaRepository;
    private final AccountJpaRepository accountJpaRepository;
    private final ClientJpaRepository clientJpaRepository;
    private final AccountCommandRepository accountCommandRepository;

    @Override
    @Transactional
    public ResponseEntity<ExceptionResponseRecord> createOrUpdate(
            TransactionRequestRecord transactionRequestRecord) {
        TransactionEntity entity;
        ClientEntity client;
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
            entity.setClient(client.getName());
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

    private ClientEntity validateClient(String idNumber) {
        Optional<ClientEntity> clientOptional = clientJpaRepository.findByIdNumber(idNumber);
        return clientOptional.orElse(null);
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
                .clientIdNumber(account.getClient().getIdNumber())
                .build());
    }

    private ExceptionResponseRecord CreateException(String message, Object data) {
        return ExceptionResponseRecord.builder()
                .httpStatus(HttpStatus.ACCEPTED)
                .message(message)
                .data(data)
                .build();
    }

}
