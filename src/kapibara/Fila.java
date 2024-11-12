package kapibara;
import java.util.List;

/**
 * Clase que representa una fila de una estructura tabular.
 * Cada fila contiene una etiqueta única que la identifica, una lista de celdas
 * que contienen
 * los datos de la fila y una lista de etiquetas asociadas a las columnas
 * correspondientes.
 */
public class Fila {

    private Etiqueta etiquetaFila; // Etiqueta única para identificar la fila
    private List<Celda<?>> celdas; // Lista de celdas que contienen los valores de la fila
    private List<Etiqueta> etiquetasCol; // Lista de etiquetas de las columnas asociadas

    /**
     * Constructor que inicializa una fila con su etiqueta, celdas y etiquetas de
     * columnas.
     *
     * @param etiquetaFila Etiqueta que identifica de manera única la fila.
     * @param celdas       Lista de celdas que contienen los valores de la fila.
     * @param etiquetasCol Lista de etiquetas asociadas a las columnas de la fila.
     */
    public Fila(Etiqueta etiquetaFila, List<Celda<?>> celdas, List<Etiqueta> etiquetasCol) {
        this.etiquetaFila = etiquetaFila;
        this.celdas = celdas;
        this.etiquetasCol = etiquetasCol;
    }

    /**
     * Devuelve la etiqueta que identifica la fila.
     *
     * @return Etiqueta de la fila.
     */
    public Etiqueta getEtiquetaFila() {
        return etiquetaFila;
    }

    /**
     * Devuelve la lista de celdas que contienen los valores de la fila.
     *
     * @return Lista de celdas de la fila.
     */
    public List<Celda<?>> getCeldasFila() {
        return celdas;
    }

    /**
     * Devuelve la lista de etiquetas asociadas a las columnas de la fila.
     *
     * @return Lista de etiquetas de las columnas.
     */
    public List<Etiqueta> getEtiquetasColumnas() {
        return etiquetasCol;
    }

    /**
     * Devuelve una representación en forma de cadena de la fila, mostrando su
     * etiqueta y
     * los valores de las celdas.
     *
     * @return Representación de la fila en formato de texto.
     */
    @Override
    public String toString() {
        String salida = "*" + etiquetaFila + "*" + " | ";
        for (Celda<?> c : celdas) {
            salida += c + " | ";
        }
        return salida;
    }
}
