
/**
 * Interfaz que define el comportamiento para ordenar una estructura tabular.
 * Proporciona la capacidad de ordenar una tabla basada en una columna
 * específica
 * y en el orden deseado (ascendente o descendente).
 */
public interface Ordenable {

     /**
      * Ordena una tabla según los valores de una columna especificada.
      *
      * @param <T>             Tipo de los valores en la columna, que debe ser
      *                        comparable.
      * @param etiquetaColumna Etiqueta de la columna por la cual se realizará la
      *                        ordenación.
      * @param ascendente      Indica si el orden debe ser ascendente (true) o
      *                        descendente (false).
      * @return Una nueva tabla con las filas ordenadas según el criterio
      *         especificado.
      * @throws IllegalArgumentException Si la etiqueta de la columna no existe o no
      *                                  es válida para ordenar.
      */
     <T extends Comparable<T>> Tabla ordenarPor(Etiqueta etiquetaColumna, boolean ascendente);
}
