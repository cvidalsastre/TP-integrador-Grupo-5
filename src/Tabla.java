/*
1.Información Básica

Cantidad de Filas y Columnas
Etiquetas de Filas y Columnas
Tipos de Datos de Columnas

Este ejemplo trabaja con lista de listas o con hashmap?
 */

import java.util.List;

//La interfaz Tabla define las operaciones básicas, como establecer y obtener datos.

public interface Tabla {

    // Devuelven el número de filas y columnas.
    int getCantidadFilas();

    int getCantidadColumnas();

    // Devuelven las etiquetas de filas y columnas como listas.
    List<String> getEtiquetasFilas();

    List<String> getEtiquetasColumnas();

    // Devuelve el tipo de datos esperado para una columna.
    Class<?> getTipoColumna(int columna);

    // Métodos para establecer etiquetas y tipos.
    void setEtiquetaFila(int fila, String etiqueta);

    void setEtiquetaColumna(int columna, String etiqueta);

    void setTipoColumna(int columna, Class<?> tipo);

    // Establece un dato en una celda.
    void setDato(int fila, int columna, Object dato);

    // Obtiene el dato de una celda.
    Object getDato(int fila, int columna);

    // Métodos para selección y filtrado (en caso de ser necesarios en la interfaz)
    Tabla seleccionar(List<Integer> filasSeleccionadas, List<Integer> columnasSeleccionadas);

    Tabla filtrar(String columnaFiltro, String condicion);

    // Métodos adicionales (si se usan en la implementación)
    List<Object> obtenerFila(int fila);

    List<Object> obtenerColumna(int columna);

    Object obtenerCelda(int fila, int columna);
}