package core.dataaccess.adapter.transaction.query;

import core.dataaccess.entities.TransactionEntity;
import core.dataaccess.mappers.TransactionMapper;
import core.dataaccess.repository.TransactionJpaRepository;
import core.domain.application.ports.output.repository.transaction.query.TransactionQueryRepository;
import core.record.ExceptionResponseRecord;
import core.record.response.TransactionResponseRecord;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
      LocalDateTime startDate, LocalDateTime endDate, String idNumber, String movementType) {

    List<TransactionEntity> movimientosEntities = transactionJpaRepository.findByTransacitionDateBetweenAndClientAndMovementType(
        startDate, endDate, idNumber, movementType);

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
        .httpStatus(200)
        .message(message)
        .data(o)
        .build();
  }
}
