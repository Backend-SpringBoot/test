package core.proyecto.base.controller.transaction.query.impl;

import core.domain.application.ports.input.transaction.query.TransactionQueryService;
import core.proyecto.base.controller.transaction.query.TransactionQueryRestController;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import core.record.ExceptionResponseRecord;

/**
 * -- AQUI AÑADIR LA DESCRIPCION DE LA IMPLMENTACIÓN DE LA INTERFACE --.
 *
 * <p>Historial de cambios:
 *
 * <ul>
 *   <li>1.0.0 - Descripción del cambio inicial - developer - 6/25/24
 *       <!-- Añadir nuevas entradas de cambios aquí -->
 * </ul>
 *
 * @author developer
 * @version 1.0.0
 * @since 6/25/24
 */
@RestController
@RequiredArgsConstructor
public class TransactionQueryRestControllerImpl implements TransactionQueryRestController {

  private final TransactionQueryService transactionQueryService;


  @Override
  public ResponseEntity<ExceptionResponseRecord> getTransaction(String id) {
    return transactionQueryService.getTransaction(id);
  }

  @Override
  public ResponseEntity<List<ExceptionResponseRecord>> getTransactions() {
    return transactionQueryService.getTransactions();
  }

  @Override
  public ResponseEntity<List<ExceptionResponseRecord>> getMovimientosPorRangoFechas(
      LocalDateTime startDate, LocalDateTime endDate, String idNumber, String movementType) {
    return transactionQueryService.getMovimientosPorRangoFechas(startDate, endDate, idNumber,
        movementType);
  }
}