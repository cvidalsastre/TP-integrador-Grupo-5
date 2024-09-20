import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class ModificacionTablaImpl extends TablaImpl {

    // Constructor para crear la tabla con filas y columnas iniciales
    public ModificacionTablaImpl(int filas, int columnas) {
        super(filas, columnas);
    }

    // Método para crear una copia profunda de la tabla
    public Tabla copiaProfunda() {
        TablaImpl copia = new ModificacionTablaImpl(getCantidadFilas(), getCantidadColumnas());
        for (int i = 0; i < getCantidadFilas(); i++) {
            for (int j = 0; j < getCantidadColumnas(); j++) {
                copia.setDato(i, j, this.getDato(i, j));
            }
        }
        copia.etiquetasFilas = new HashMap<>(this.etiquetasFilas);
        copia.etiquetasColumnas = new HashMap<>(this.etiquetasColumnas);
        return copia;
    }

    // Método para agregar una columna desde una secuencia
    public void agregarColumnaDesdeSecuencia(List<Object> secuencia) {
        if (secuencia.size() != getCantidadFilas()) {
            throw new IllegalArgumentException("La secuencia no coincide con el número de filas.");
        }

        // Crear nuevos datos para las filas que incluirán la nueva columna
        List<List<Object>> nuevosDatos = new ArrayList<>();
        for (int i = 0; i < getCantidadFilas(); i++) {
            List<Object> nuevaFila = new ArrayList<>(datos.get(i));
            nuevaFila.add(secuencia.get(i)); // Añadir el elemento de la secuencia a cada fila
            nuevosDatos.add(nuevaFila);
        }

        // Actualizar la matriz de datos
        this.datos = nuevosDatos;
        int nuevaColumnaIndex = etiquetasColumnas.size(); // Índice de la nueva columna
        etiquetasColumnas.put(nuevaColumnaIndex, "NuevaColumna"); // Añadir etiqueta
    }

    // Método para eliminar una columna por índice
    public void eliminarColumna(int columnaIndex) {
        for (List<Object> fila : datos) {
            fila.remove(columnaIndex);
        }
        etiquetasColumnas.remove(columnaIndex);

        // Ajustar las etiquetas de columna (opcional, si se requiere reorganización)
        Map<Integer, String> nuevasEtiquetas = new HashMap<>();
        int nuevaIndex = 0;
        for (Map.Entry<Integer, String> entry : etiquetasColumnas.entrySet()) {
            if (entry.getKey() != columnaIndex) {
                nuevasEtiquetas.put(nuevaIndex++, entry.getValue());
            }
        }
        etiquetasColumnas = nuevasEtiquetas;
    }

    // Método para eliminar una fila por índice
    public void eliminarFila(int filaIndex) {
        datos.remove(filaIndex);
        etiquetasFilas.remove(filaIndex);

        // Ajustar las etiquetas de fila (opcional, si se requiere reorganización)
        Map<Integer, String> nuevasEtiquetas = new HashMap<>();
        int nuevaIndex = 0;
        for (Map.Entry<Integer, String> entry : etiquetasFilas.entrySet()) {
            if (entry.getKey() != filaIndex) {
                nuevasEtiquetas.put(nuevaIndex++, entry.getValue());
            }
        }
        etiquetasFilas = nuevasEtiquetas;
    }

    // Conversión de Object[][] a List<List<Object>>
    public List<List<Object>> convertirMatrizALista(Object[][] matriz) {
        List<List<Object>> lista = new ArrayList<>();
        for (Object[] fila : matriz) {
            lista.add(new ArrayList<>(Arrays.asList(fila)));
        }
        return lista;
    }
}
