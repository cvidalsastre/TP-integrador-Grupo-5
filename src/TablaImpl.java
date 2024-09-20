import java.util.*;

/**
 * TablaImpl es una implementación concreta de la interfaz Tabla.
 * 
 * Estructura de Datos:
 * - datos: Lista de listas que representa las celdas de la tabla.
 * - etiquetasFilas, etiquetasColumnas: HashMap para las etiquetas.
 * - tiposColumnas: HashMap para los tipos de datos de cada columna.
 * 
 * Métodos:
 * - getCantidadFilas(), getCantidadColumnas(): Devuelven el número de filas y
 * columnas.
 * - getEtiquetasFilas(), getEtiquetasColumnas(): Devuelven las etiquetas de
 * filas y columnas.
 * - getTipoColumna(int columna): Devuelve el tipo de datos para una columna
 * específica.
 * - setDato(int fila, int columna, Object dato): Establece el dato en una celda
 * y verifica su tipo.
 * - getDato(int fila, int columna): Obtiene el dato de una celda.
 */
public class TablaImpl implements Tabla, Acceso {

    // Almacena los datos de la tabla
    protected List<List<Object>> datos;
    // Etiquetas de las filas
    protected Map<Integer, String> etiquetasFilas;
    // Etiquetas de las columnas
    protected Map<Integer, String> etiquetasColumnas;
    // Tipos de datos para cada columna
    protected Map<Integer, Class<?>> tiposColumnas;
    // Valor especial para datos faltantes
    protected static final Object NA = "NA";

    /**
     * Constructor de la tabla con dimensiones especificadas.
     * 
     * @param filas    Número de filas
     * @param columnas Número de columnas
     */
    public TablaImpl(int filas, int columnas) {
        datos = new ArrayList<>(filas);
        etiquetasFilas = new HashMap<>();
        etiquetasColumnas = new HashMap<>();
        tiposColumnas = new HashMap<>();

        // Inicializa las filas de la tabla con el valor NA
        for (int i = 0; i < filas; i++) {
            datos.add(new ArrayList<>(Collections.nCopies(columnas, NA)));
            etiquetasFilas.put(i, "Fila" + i);
        }
        // Inicializa las columnas de la tabla con el tipo Object y etiquetas
        // predeterminadas
        for (int j = 0; j < columnas; j++) {
            tiposColumnas.put(j, Object.class); // Inicialmente tipo Object
            etiquetasColumnas.put(j, "Columna" + j);
        }
    }

    /**
     * Constructor por defecto para crear una tabla vacía.
     */
    public TablaImpl() {
        datos = new ArrayList<>();
        etiquetasFilas = new HashMap<>();
        etiquetasColumnas = new HashMap<>();
        tiposColumnas = new HashMap<>();
    }

    @Override
    public int getCantidadFilas() {
        return datos.size();
    }

    @Override
    public int getCantidadColumnas() {
        return datos.isEmpty() ? 0 : datos.get(0).size();
    }

    @Override
    public List<String> getEtiquetasFilas() {
        return new ArrayList<>(etiquetasFilas.values());
    }

    @Override
    public List<String> getEtiquetasColumnas() {
        return new ArrayList<>(etiquetasColumnas.values());
    }

    @Override
    public Class<?> getTipoColumna(int columna) {
        return tiposColumnas.get(columna);
    }

    @Override
    public void setEtiquetaFila(int fila, String etiqueta) {
        etiquetasFilas.put(fila, etiqueta);
    }

    @Override
    public void setEtiquetaColumna(int columna, String etiqueta) {
        etiquetasColumnas.put(columna, etiqueta);
    }

    @Override
    public void setTipoColumna(int columna, Class<?> tipo) {
        tiposColumnas.put(columna, tipo);
    }

    @Override
    public void setDato(int fila, int columna, Object dato) {
        if (dato == null || dato.equals(NA)) {
            datos.get(fila).set(columna, NA);
        } else if (getTipoColumna(columna).isInstance(dato)) {
            datos.get(fila).set(columna, dato);
        } else {
            throw new IllegalArgumentException("Tipo de dato no válido para la columna.");
        }
    }

    @Override
    public Object getDato(int fila, int columna) {
        return datos.get(fila).get(columna);
    }

    @Override
    public List<Object> obtenerFila(int fila) {
        return new ArrayList<>(datos.get(fila));
    }

    @Override
    public List<Object> obtenerColumna(int columna) {
        List<Object> columnaDatos = new ArrayList<>();
        for (List<Object> fila : datos) {
            columnaDatos.add(fila.get(columna));
        }
        return columnaDatos;
    }

    @Override
    public Object obtenerCelda(int fila, int columna) {
        return datos.get(fila).get(columna);
    }

    @Override
    public Tabla seleccionar(List<Integer> filasSeleccionadas, List<Integer> columnasSeleccionadas) {
        TablaImpl seleccion = new TablaImpl(filasSeleccionadas.size(), columnasSeleccionadas.size());
        for (int i = 0; i < filasSeleccionadas.size(); i++) {
            for (int j = 0; j < columnasSeleccionadas.size(); j++) {
                seleccion.setDato(i, j, this.getDato(filasSeleccionadas.get(i), columnasSeleccionadas.get(j)));
            }
        }
        return seleccion;
    }

    @Override
    public Tabla filtrar(String columnaFiltro, String condicion) {
        int columnaIndex = etiquetasColumnas.entrySet().stream()
                .filter(e -> e.getValue().equals(columnaFiltro))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Columna no encontrada"));

        TablaImpl filtrada = new TablaImpl(getCantidadFilas(), getCantidadColumnas());
        for (int i = 0; i < getCantidadFilas(); i++) {
            Object valor = getDato(i, columnaIndex);
            if (valor != null && valor.toString().equals(condicion)) {
                for (int j = 0; j < getCantidadColumnas(); j++) {
                    filtrada.setDato(i, j, getDato(i, j));
                }
            }
        }
        return filtrada;
    }
}
