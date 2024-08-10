package test.domain.application.ports.output.repository.transaction.query;

import org.springframework.http.ResponseEntity;
import test.test.record.ExceptionResponseRecord;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionQueryRepository {

    ResponseEntity<ExceptionResponseRecord> getTransaction(String id);

    ResponseEntity<List<ExceptionResponseRecord>> getTransactions();

    ResponseEntity<List<ExceptionResponseRecord>> getMovimientosPorRangoFechas(
            LocalDateTime startDate, LocalDateTime endDate);
}
