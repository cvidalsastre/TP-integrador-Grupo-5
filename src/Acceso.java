import java.util.List;

/**
 * 
 * Acceso Indexado
 * Funcionalidades:
 * 
 * Acceso a una fila completa
 * Acceso a una columna completa
 * Acceso a una celda específica
 */

public interface Acceso {
    // Define una interfaz para las oper

    List<Object> obtenerFila(int fila);

    List<Object> obtenerColumna(int columna);

    Object obtenerCelda(int fila, int columna);
}
