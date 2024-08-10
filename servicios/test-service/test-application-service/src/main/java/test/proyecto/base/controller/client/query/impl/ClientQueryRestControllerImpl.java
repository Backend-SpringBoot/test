package test.proyecto.base.controller.client.query.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import test.domain.application.ports.input.client.query.ClientQueryService;
import test.proyecto.base.controller.client.query.ClientQueryRestController;
import test.test.record.ExceptionResponseRecord;

import java.util.List;

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

    private final ClientQueryService clientQueryService;

    @Override
    public ResponseEntity<ExceptionResponseRecord> getClient(String id) {
        return clientQueryService.getClient(id);
    }

    @Override
    public ResponseEntity<List<ExceptionResponseRecord>> getClients() {
        return clientQueryService.getClients();
    }
}