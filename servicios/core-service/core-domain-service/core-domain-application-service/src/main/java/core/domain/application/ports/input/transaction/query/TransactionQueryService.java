package core.domain.application.ports.input.transaction.query;

import core.record.ExceptionResponseRecord;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface TransactionQueryService {

  ResponseEntity<ExceptionResponseRecord> getTransaction(String id);

  ResponseEntity<List<ExceptionResponseRecord>> getTransactions();

  ResponseEntity<List<ExceptionResponseRecord>> getMovimientosPorRangoFechas(
      LocalDateTime startDate, LocalDateTime endDate, String idNumber, String movementType,
      String accountType);

}
