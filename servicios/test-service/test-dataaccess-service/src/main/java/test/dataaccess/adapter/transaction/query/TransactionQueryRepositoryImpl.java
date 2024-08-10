package test.dataaccess.adapter.transaction.query;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.dataaccess.entities.TransactionEntity;
import test.dataaccess.mappers.TransactionMapper;
import test.dataaccess.repository.TransactionJpaRepository;
import test.domain.application.ports.output.repository.transaction.query.TransactionQueryRepository;
import test.test.record.ExceptionResponseRecord;
import test.test.record.response.TransactionResponseRecord;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TransactionQueryRepositoryImpl implements TransactionQueryRepository {

    private final TransactionJpaRepository transactionJpaRepository;

    @Override
    public ResponseEntity<ExceptionResponseRecord> getTransaction(String id) {
        Optional<TransactionEntity> movimientoOptional =
                transactionJpaRepository
                        .findByAccountNumber(id);
        if (movimientoOptional.isEmpty()) {
            return new ResponseEntity<>(CreateException("No existe registros", null), HttpStatus.OK);
        }
        TransactionEntity movimiento = movimientoOptional.get();
        TransactionResponseRecord data = TransactionMapper.INSTANCE.entityToResponseRecord(movimiento);
        return new ResponseEntity<>(CreateException("Correcto !", data), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ExceptionResponseRecord>> getTransactions() {
        List<TransactionEntity> movimientoEntities =
                transactionJpaRepository
                        .findAll();
        List<TransactionResponseRecord> data = TransactionMapper.INSTANCE.entitiesToResponseRecords(
                movimientoEntities);
        return new ResponseEntity<>(List.of(CreateException("Correcto", data)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ExceptionResponseRecord>> getMovimientosPorRangoFechas(
            LocalDateTime startDate, LocalDateTime endDate) {

        List<TransactionEntity> movimientosEntities = transactionJpaRepository.findByTransacitionDateBetween(
                startDate, endDate);

        if (movimientosEntities.isEmpty()) {
            return new ResponseEntity<>(List.of(
                    CreateException("No se encontraron transaction en el rango de fechas proporcionado",
                            null)), HttpStatus.OK);
        }
        List<TransactionResponseRecord> data = TransactionMapper.INSTANCE.entitiesToResponseRecords(
                movimientosEntities);
        return new ResponseEntity<>(List.of(CreateException("Correcto", data)), HttpStatus.OK);
    }

    private ExceptionResponseRecord CreateException(String message, Object o) {
        return ExceptionResponseRecord.builder()
                .httpStatus(HttpStatus.ACCEPTED)
                .message(message)
                .data(o)
                .build();
    }
}
