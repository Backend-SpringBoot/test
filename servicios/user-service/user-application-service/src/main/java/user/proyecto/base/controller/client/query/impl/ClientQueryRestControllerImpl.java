package user.proyecto.base.controller.client.query.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import user.domain.application.ports.input.client.query.UserQueryService;
import user.proyecto.base.controller.client.query.ClientQueryRestController;
import user.test.record.ExceptionResponseRecord;

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
public class ClientQueryRestControllerImpl implements ClientQueryRestController {

  private final UserQueryService userQueryService;

  @Override
  public ResponseEntity<ExceptionResponseRecord> getClient(String id) {
    return userQueryService.getClient(id);
  }

  @Override
  public ResponseEntity<List<ExceptionResponseRecord>> getClients() {
    return userQueryService.getClients();
  }
}