package test.proyecto.base.controller.account.query;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import test.test.record.ExceptionResponseRecord;

import java.util.List;

/**
 * -- AQUI AÑADIR LA DESCRIPCION DE LA INTERFACE --.
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
@RequestMapping("/query/accounts")
@Validated
public interface AccountQueryRestController {

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Obtiene una account")
    ResponseEntity<ExceptionResponseRecord> getAccount(@PathVariable("id") String id);


    @GetMapping("/accounts")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Obtiene lista de cuentas")
    ResponseEntity<List<ExceptionResponseRecord>> getAccounts();
}