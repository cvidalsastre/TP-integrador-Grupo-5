import java.util.List;

public interface ModificacionTabla {

    // Método para crear una copia profunda de la tabla
    Tabla copiaProfunda();

    // Método para agregar una columna desde una secuencia de datos
    void agregarColumnaDesdeSecuencia(List<Object> secuencia);

    // Método para eliminar una columna
    void eliminarColumna(int columna);

    // Método para eliminar una fila
    void eliminarFila(int fila);
}
