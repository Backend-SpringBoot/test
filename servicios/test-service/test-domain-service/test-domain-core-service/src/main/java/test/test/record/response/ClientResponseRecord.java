package test.test.record.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

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
public record ClientResponseRecord(
        Integer id,

        String name,
        String idNumber,
        List<AccountResponseRecord> accounts,
        @JsonIgnore String status,
        @JsonIgnore Integer idPersonaCrea,
        @JsonIgnore LocalDateTime fechaCrea,
        @JsonIgnore Integer idPersonaModifica,
        @JsonIgnore LocalDateTime fechaModifica,
        @JsonIgnore String ipCrea,
        @JsonIgnore String ipModifica,
        @JsonIgnore String equipoCrea,
        @JsonIgnore String equipoModifica,
        @JsonIgnore String motivoModifica) {

}