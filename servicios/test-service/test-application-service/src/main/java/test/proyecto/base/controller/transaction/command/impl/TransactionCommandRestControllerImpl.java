package test.proyecto.base.controller.transaction.command.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import test.domain.application.ports.input.transaction.command.TransactionCommandService;
import test.proyecto.base.controller.transaction.command.TransactionCommandRestController;
import test.test.record.ExceptionResponseRecord;
import test.test.record.request.TransactionRequestRecord;

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
public class TransactionCommandRestControllerImpl implements TransactionCommandRestController {

    private final TransactionCommandService transactionCommandService;


    @Override
    public ResponseEntity<ExceptionResponseRecord> createorUpdate(
            TransactionRequestRecord transactionRequestRecord) {
        return transactionCommandService.createOrUpdate(transactionRequestRecord);
    }

    @Override
    public void delete(Integer id) {
        transactionCommandService.delete(id);
    }
}