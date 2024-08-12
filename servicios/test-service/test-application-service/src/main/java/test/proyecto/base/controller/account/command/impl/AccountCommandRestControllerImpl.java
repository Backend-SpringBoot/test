package test.proyecto.base.controller.account.command.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import test.domain.application.ports.input.account.command.AccountCommandService;
import test.proyecto.base.controller.account.command.AccountCommandRestController;
import test.test.record.ExceptionResponseRecord;
import test.test.record.request.AccountRequestRecord;

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