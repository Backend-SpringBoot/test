package test.domain.application.ports.input.transaction.query;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import test.test.record.ExceptionResponseRecord;

public interface TransactionQueryService {

    ResponseEntity<ExceptionResponseRecord> getTransaction(String id);

    ResponseEntity<List<ExceptionResponseRecord>> getTransactions();

    ResponseEntity<List<ExceptionResponseRecord>> getMovimientosPorRangoFechas(
            LocalDateTime startDate, LocalDateTime endDate);

}
