
/**
 * Interfaz que define operaciones avanzadas sobre una tabla, incluyendo
 * ordenamiento, imputación de valores faltantes y muestreo aleatorio.
 */
public interface OperacionesAvanzadas {

    /**
     * Ordena las filas de la tabla según los valores en una columna específica.
     *
     * @param columna    El índice de la columna por la que se ordenará.
     * @param ascendente Si true, ordena en orden ascendente; si false, en orden
     *                   descendente.
     */
    void ordenarPorColumna(int columna, boolean ascendente);

    /**
     * Imputa los valores faltantes en la tabla con un valor especificado.
     *
     * @param valor El valor con el que se imputarán los datos faltantes.
     */
    void imputar(Object valor);

    /**
     * Selecciona una muestra aleatoria de filas de la tabla.
     *
     * @param porcentaje El porcentaje de filas que se seleccionará.
     * @return Una nueva tabla con las filas seleccionadas.
     */
    Tabla muestreo(double porcentaje);
}
