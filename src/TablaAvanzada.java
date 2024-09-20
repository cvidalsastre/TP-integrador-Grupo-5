import java.util.*;

/**
 * Implementación de la interfaz OperacionesAvanzadas para realizar operaciones
 * avanzadas sobre una tabla.
 */
public class TablaAvanzada extends TablaImpl implements OperacionesAvanzadas {

    public TablaAvanzada(int filas, int columnas) {
        super(filas, columnas);
    }

    public TablaAvanzada() {
        super();
    }

    /**
     * Ordena las filas de la tabla según los valores en una columna específica.
     *
     * @param columna    El índice de la columna por la que se ordenará.
     * @param ascendente Si true, ordena en orden ascendente; si false, en orden
     *                   descendente.
     */
    @Override
    public void ordenarPorColumna(int columna, boolean ascendente) {
        datos.sort((fila1, fila2) -> {
            Object valor1 = fila1.get(columna);
            Object valor2 = fila2.get(columna);

            if (valor1 instanceof Comparable && valor2 instanceof Comparable) {
                @SuppressWarnings("unchecked")
                Comparable<Object> comparableValor1 = (Comparable<Object>) valor1;
                @SuppressWarnings("unchecked")
                Comparable<Object> comparableValor2 = (Comparable<Object>) valor2;

                return ascendente ? comparableValor1.compareTo(comparableValor2)
                        : comparableValor2.compareTo(comparableValor1);
            } else {
                throw new IllegalArgumentException("Los valores en la columna no son comparables.");
            }
        });
    }

    /**
     * Imputa los valores faltantes en la tabla con un valor especificado.
     *
     * @param valor El valor con el que se imputarán los datos faltantes.
     */
    @Override
    public void imputar(Object valor) {
        for (List<Object> fila : datos) {
            for (int j = 0; j < fila.size(); j++) {
                if (fila.get(j).equals(NA)) {
                    fila.set(j, valor);
                }
            }
        }
    }

    /**
     * Selecciona una muestra aleatoria de filas de la tabla.
     *
     * @param porcentaje El porcentaje de filas que se seleccionará.
     * @return Una nueva tabla con las filas seleccionadas.
     */
    @Override
    public TablaImpl muestreo(double porcentaje) {
        Random random = new Random();
        int numFilas = (int) (getCantidadFilas() * porcentaje / 100);
        TablaImpl muestra = new TablaImpl(numFilas, getCantidadColumnas());

        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < getCantidadFilas(); i++) {
            indices.add(i);
        }
        Collections.shuffle(indices, random); // Mezcla los índices para la selección aleatoria

        for (int i = 0; i < numFilas; i++) {
            int filaOriginal = indices.get(i);
            for (int j = 0; j < getCantidadColumnas(); j++) {
                muestra.setDato(i, j, getDato(filaOriginal, j));
            }
        }
        return muestra;
    }
}
