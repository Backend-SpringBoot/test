package core.domain.application.services.transaction.query;

import core.domain.application.ports.input.transaction.query.TransactionQueryService;
import core.domain.application.ports.output.repository.transaction.query.TransactionQueryRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import core.record.ExceptionResponseRecord;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TransactionQueryServiceImpl implements TransactionQueryService {

  private final TransactionQueryRepository transactionQueryRepository;

  @Override
  public ResponseEntity<ExceptionResponseRecord> getTransaction(String id) {
    return transactionQueryRepository.getTransaction(id);
  }

  @Override
  public ResponseEntity<List<ExceptionResponseRecord>> getTransactions() {
    return transactionQueryRepository.getTransactions();
  }

  @Override
  public ResponseEntity<List<ExceptionResponseRecord>> getMovimientosPorRangoFechas(
      LocalDateTime startDate, LocalDateTime endDate, String idNumber, String movementType) {
    return transactionQueryRepository.getMovimientosPorRangoFechas(startDate, endDate, idNumber,
        movementType);
  }
}
