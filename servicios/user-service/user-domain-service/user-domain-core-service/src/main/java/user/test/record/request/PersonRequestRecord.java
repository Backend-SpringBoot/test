package user.test.record.request;

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
public record PersonRequestRecord(
        Integer id,
        String name,
        String gender,
        Integer age,
        String idNumber,
        String address,
        String status,
        String phone

) {

}