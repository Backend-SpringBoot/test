package user.test.record.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

/**
 * -- AQUI AÑADIR LA DESCRIPCION DEL RECORD --.
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
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ClientRequestRecord(
        Integer id,
        String password,
        String status,
        String name,
        String gender,
        Integer age,
        String idNumber,
        String address,
        String phone
) {

}