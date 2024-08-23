package core.proyecto.base.controller.account.query.impl;

import core.domain.application.ports.input.account.query.AccountQueryService;
import core.proyecto.base.controller.account.query.AccountQueryRestController;
import core.record.ExceptionResponseRecord;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

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
public class AccountQueryRestControllerImpl implements AccountQueryRestController {

  private final AccountQueryService accountQueryService;

  @Override
  public ResponseEntity<ExceptionResponseRecord> getAccount(String id) {
    return accountQueryService.getAccount(id);
  }

  @Override
  public ResponseEntity<List<ExceptionResponseRecord>> getAccounts() {
    return accountQueryService.getAccounts();
  }

  @Override
  public ResponseEntity<List<ExceptionResponseRecord>> findByIdNumber(String id) {
    return accountQueryService.findByIdNumber(id);
  }
}