/**
 * <p> Proyecto expel-consulta-causas-clex.
 * <p> Clase ProvidenciaUtil 12/2/2024.
 * <p> Copyright 2024 Consejo de la Judicatura.
 * <p> Todos los derechos reservados.
 */
package core.remote.Util;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * -- AQUI AÑADIR LA DESCRIPCION DE LA CLASE --.
 *
 * <p>Historial de cambios:
 *
 * <ul>
 *   <li>1.0.0 - Descripción del cambio inicial - Francisco.Zabala - 12/2/2024
 *       <!-- Añadir nuevas entradas de cambios aquí -->
 * </ul>
 *
 * @author Francisco.Zabala
 * @version 1.0.0 $
 * @since 12/2/2024
 */
public final class KeycloakUtil {

  private KeycloakUtil() {
  }

  public static Long convertirACodigo(Object obj) {

    obj = (obj instanceof String) ? Integer.parseInt(obj.toString()) : obj;
    return obj != null ? ((Integer) obj).longValue() : 0L;
  }

  public static String convertirAString(Object obj) {
    return obj != null ? obj.toString() : "";
  }

  public static String convertirAStringConDefault(Object obj, String defaultValue) {
    return obj != null ? obj.toString() : defaultValue;
  }

  public static Long convertirALong(Object obj) {
    return obj != null ? ((Integer) obj).longValue() : 0L;
  }

  public static LocalDateTime convertirAFecha(Object objFecha) {
    if (objFecha instanceof Timestamp timestampFecha) {
      return timestampFecha.toLocalDateTime();
    }

    return LocalDate.of(1900, 1, 1).atStartOfDay();
  }

  public static LocalDateTime combinarFechaHora(Object objFecha, Object objHora) {
    LocalDate fecha = LocalDate.of(1900, 1, 1);
    LocalTime hora = LocalTime.MIDNIGHT;
    if (objFecha instanceof Timestamp timestampFecha) {
      fecha = timestampFecha.toLocalDateTime().toLocalDate();
    }
    if (objHora instanceof Timestamp timestampHora) {
      hora = timestampHora.toLocalDateTime().toLocalTime();
    }
    return LocalDateTime.of(fecha, hora);
  }

  public static String formatearNombreProvidencia(Object objTipo, Object objNombreProvidencia) {
    String tipo = objTipo != null ? objTipo.toString() : "";
    String nombreProvidencia = objNombreProvidencia != null ? objNombreProvidencia.toString() : "";

    if (!tipo.isEmpty()) {
      return (nombreProvidencia + " (" + tipo + ")").replaceAll("\\s+", " ");
    } else {
      return nombreProvidencia;
    }
  }

  public static String determinarActividad(Object objTipo, Object objRazonNotificacion,
      Object objProvidencia) {
    String tipo = objTipo != null ? objTipo.toString() : "";
    String razonNotificacion = objRazonNotificacion != null ? objRazonNotificacion.toString() : "";
    String providencia = objProvidencia != null ? objProvidencia.toString() : "";

    String resultado;

    if ("RAZON DE NOTIFICACION".equals(tipo)) {
      resultado = !razonNotificacion.isEmpty() ? razonNotificacion : providencia;
    } else {
      resultado = providencia;
    }

    return resultado.replace("\u0001", "");
  }
}