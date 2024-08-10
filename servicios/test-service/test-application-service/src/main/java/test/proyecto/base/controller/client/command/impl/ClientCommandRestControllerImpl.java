package test.proyecto.base.controller.client.command.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import test.domain.application.ports.input.client.command.ClientCommandService;
import test.proyecto.base.controller.client.command.ClientCommandRestController;
import test.test.record.ExceptionResponseRecord;
import test.test.record.request.ClientRequestRecord;

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
public class ClientCommandRestControllerImpl implements ClientCommandRestController {

    private final ClientCommandService clientCommandService;


    @Override
    public ResponseEntity<ExceptionResponseRecord> createorUpdate(
            ClientRequestRecord clientRequestRecord) {
        return clientCommandService.createOrUpdate(clientRequestRecord);
    }

    @Override
    public ResponseEntity<ExceptionResponseRecord> delete(String idNumber) {
        return clientCommandService.delete(idNumber);
    }
}