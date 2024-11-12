package kapibara;
import java.util.List;
import enums.OperacionEstadistica;

/**
 * Interfaz que define operaciones de agrupamiento y sumarización sobre una
 * estructura tabular.
 */
public interface Agrupable {

    /**
     * Agrupa los datos de la tabla según las columnas especificadas y aplica una
     * operación de sumarización
     * estadística (e.g., suma, promedio, máximo).
     *
     * @param etiquetasColumnasAgrupamiento Lista de etiquetas que identifican las
     *                                      columnas para el agrupamiento.
     * @param operacion                     Operación estadística a aplicar a cada
     *                                      grupo (e.g., suma, promedio).
     * @return Una nueva tabla con los resultados del agrupamiento y la
     *         sumarización.
     */
    Tabla agruparYSumarizar(List<Etiqueta> etiquetasColumnasAgrupamiento, OperacionEstadistica operacion);

}
