package test.proyecto.base.controller.account.query.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import test.domain.application.ports.input.account.query.AccountQueryService;
import test.proyecto.base.controller.account.query.AccountQueryRestController;
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
}