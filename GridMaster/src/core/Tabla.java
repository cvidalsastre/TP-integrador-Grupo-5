package core;

import java.util.List;

public interface Tabla {

    /**
     * Obtiene el número total de filas en la tabla.
     * 
     * @return El número de filas.
     */
    int getCantidadFilas();

    /**
     * Obtiene el número total de columnas en la tabla.
     * 
     * @return El número de columnas.
     */
    int getCantidadColumnas();

    /**
     * Obtiene las etiquetas de todas las filas.
     * 
     * @return Una lista con las etiquetas de las filas.
     */
    List<String> getEtiquetasFilas();

    /**
     * Obtiene las etiquetas de todas las columnas.
     * 
     * @return Una lista con las etiquetas de las columnas.
     */
    List<String> getEtiquetasColumnas();

    /**
     * Obtiene el tipo de datos de una columna específica.
     * 
     * @param columna El índice de la columna.
     * @return El tipo de datos de la columna.
     */
    Class<?> getTipoColumna(int columna);

    Object getDato(int fila, int columna); // Método que debe ser implementado
}
