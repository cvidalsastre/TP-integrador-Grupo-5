import java.util.List;

/**
 * Interfaz para la funcionalidad de selección y filtrado de datos en una tabla.
 */
public interface SeleccionFiltrado {

    /**
     * Selecciona un subconjunto de la tabla basado en filas y columnas
     * especificadas.
     * 
     * @param filasSeleccionadas    Lista de índices de filas a seleccionar.
     * @param columnasSeleccionadas Lista de índices de columnas a seleccionar.
     * @return Una nueva tabla con los datos seleccionados.
     */
    Tabla seleccionar(List<Integer> filasSeleccionadas, List<Integer> columnasSeleccionadas);

    /**
     * Filtra las filas de la tabla basado en una condición aplicada a una columna
     * específica.
     * 
     * @param columnaFiltro Nombre de la columna en la que se aplicará el filtro.
     * @param condicion     Valor que se usará como condición para el filtrado.
     * @return Una nueva tabla con las filas que cumplen la condición.
     */
    Tabla filtrar(String columnaFiltro, String condicion);
}
