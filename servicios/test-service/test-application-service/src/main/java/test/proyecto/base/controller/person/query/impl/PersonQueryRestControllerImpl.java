package test.proyecto.base.controller.person.query.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import test.domain.application.ports.input.person.query.PersonQueryService;
import test.proyecto.base.controller.person.query.PersonQueryRestController;
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
public class PersonQueryRestControllerImpl implements PersonQueryRestController {

    private final PersonQueryService personQueryService;

    @Override
    public ResponseEntity<ExceptionResponseRecord> getUser(String id) {
        return personQueryService.getUser(id);
    }

    @Override
    public ResponseEntity<List<ExceptionResponseRecord>> getUsers() {
        return personQueryService.getUsers();
    }
}