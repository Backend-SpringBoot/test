package core.proyecto.base.controller.account.command.impl;

import core.domain.application.ports.input.account.command.AccountCommandService;
import core.proyecto.base.controller.account.command.AccountCommandRestController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import core.record.ExceptionResponseRecord;
import core.record.request.AccountRequestRecord;

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
public class AccountCommandRestControllerImpl implements AccountCommandRestController {

  private final AccountCommandService accountCommandService;


  @Override
  public ResponseEntity<ExceptionResponseRecord> createorUpdate(
      AccountRequestRecord accountRequestRecord) {
    return accountCommandService.createOrUpdate(accountRequestRecord);
  }

  @Override
  public ResponseEntity<ExceptionResponseRecord> delete(String id) {
    return accountCommandService.delete(id);
  }
}