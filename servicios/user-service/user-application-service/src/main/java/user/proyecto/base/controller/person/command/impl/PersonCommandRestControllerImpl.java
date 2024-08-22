package user.proyecto.base.controller.person.command.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import user.domain.application.ports.input.person.command.PersonCommandService;
import user.test.record.ExceptionResponseRecord;
import user.test.record.request.PersonRequestRecord;
import user.proyecto.base.controller.person.command.PersonCommandRestController;

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
public class PersonCommandRestControllerImpl implements PersonCommandRestController {

    private final PersonCommandService personCommandService;

    @Override
    public ResponseEntity<ExceptionResponseRecord> createorUpdate(
            PersonRequestRecord personRequestRecord) {
        return personCommandService.createOrUpdate(personRequestRecord);
    }

    @Override
    public ResponseEntity<ExceptionResponseRecord> delete(String id) {
        return personCommandService.delete(id);
    }
}