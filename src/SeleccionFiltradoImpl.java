import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación de la interfaz SeleccionFiltrado para la clase Tabla.
 */
public class SeleccionFiltradoImpl implements SeleccionFiltrado {

    private final TablaImpl tabla;

    public SeleccionFiltradoImpl(TablaImpl tabla) {
        this.tabla = tabla;
    }

    @Override
    public Tabla seleccionar(List<Integer> filasSeleccionadas, List<Integer> columnasSeleccionadas) {
        // Crear una nueva tabla para los datos seleccionados
        Tabla seleccion = new TablaImpl(filasSeleccionadas.size(), columnasSeleccionadas.size());

        // Copiar los datos seleccionados
        for (int i = 0; i < filasSeleccionadas.size(); i++) {
            for (int j = 0; j < columnasSeleccionadas.size(); j++) {
                seleccion.setDato(i, j, tabla.getDato(filasSeleccionadas.get(i), columnasSeleccionadas.get(j)));
            }
        }

        return seleccion;
    }

    @Override
    public Tabla filtrar(String columnaFiltro, String condicion) {
        // Encontrar el índice de la columna a filtrar
        int columnaIndex = tabla.getEtiquetasColumnas().stream()
                .filter(etiqueta -> etiqueta.equals(columnaFiltro))
                .collect(Collectors.toList())
                .indexOf(columnaFiltro);

        if (columnaIndex == -1) {
            throw new IllegalArgumentException("Columna no encontrada");
        }

        // Crear una nueva tabla para los datos filtrados
        Tabla filtrada = new TablaImpl(tabla.getCantidadFilas(), tabla.getCantidadColumnas());

        // Filtrar las filas que cumplen con la condición
        for (int i = 0; i < tabla.getCantidadFilas(); i++) {
            Object valor = tabla.getDato(i, columnaIndex);
            if (valor.toString().equals(condicion)) {
                for (int j = 0; j < tabla.getCantidadColumnas(); j++) {
                    filtrada.setDato(i, j, tabla.getDato(i, j));
                }
            }
        }

        return filtrada;
    }
}
