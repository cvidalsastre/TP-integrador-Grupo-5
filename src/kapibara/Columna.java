package kapibara;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa una columna en una estructura tabular.
 * Cada columna tiene una etiqueta única, un tipo de dato asociado y una lista
 * de celdas.
 *
 * @param <T> El tipo de dato que almacenará esta columna (por ejemplo, Integer,
 *            String, etc.).
 */
public class Columna<T> {

    /**
     * Lista de celdas contenidas en la columna.
     */
    private List<Celda<T>> celdas;

    /**
     * Etiqueta que identifica a la columna.
     */
    private Etiqueta etiqueta;

    /**
     * Tipo de dato de los valores almacenados en las celdas de la columna.
     */
    private Class<T> tipoDeDato;

    /**
     * Constructor para inicializar una columna con una etiqueta y un tipo de dato.
     *
     * @param etiqueta   La etiqueta que identifica a la columna.
     * @param tipoDeDato El tipo de dato de las celdas que contendrá la columna.
     */
    public Columna(Etiqueta etiqueta, Class<T> tipoDeDato) {
        this.etiqueta = etiqueta;
        this.tipoDeDato = tipoDeDato;
        this.celdas = new ArrayList<>();
    }

    /**
     * Agrega una celda a la columna, verificando que el tipo del valor coincida
     * con el tipo de dato de la columna.
     *
     * @param celda La celda que se desea agregar.
     * @throws IllegalArgumentException Si el tipo del valor en la celda no coincide
     *                                  con el tipo de dato de la columna.
     */
    public void agregarCelda(Celda<?> celda) {
        if (celda.getValor() != null && !tipoDeDato.isInstance(celda.getValor())) {
            throw new IllegalArgumentException("El valor de la celda no corresponde al tipo de dato de la columna");
        }
        // Cast seguro porque se verifica el tipo en tiempo de ejecución.
        @SuppressWarnings("unchecked")
        Celda<T> celdaCast = (Celda<T>) celda;
        celdas.add(celdaCast);
    }

    /**
     * Obtiene la etiqueta de la columna.
     *
     * @return La etiqueta de la columna.
     */
    public Etiqueta getEtiqueta() {
        return etiqueta;
    }

    /**
     * Obtiene la lista de celdas contenidas en la columna.
     *
     * @return La lista de celdas.
     */
    public List<Celda<T>> getCeldas() {
        return celdas;
    }

    /**
     * Obtiene el tipo de dato asociado a la columna.
     *
     * @return La clase que representa el tipo de dato de la columna.
     */
    public Class<T> getTipoDeDato() {
        return tipoDeDato;
    }

    /**
     * Establece una nueva lista de celdas para la columna.
     * Este método es útil en operaciones como el ordenamiento.
     *
     * @param nuevasCeldas La nueva lista de celdas que se asignará a la columna.
     */
    public void setCeldas(List<Celda<T>> nuevasCeldas) {
        this.celdas = nuevasCeldas;
    }

    /**
     * Cuenta la cantidad de celdas en la columna.
     *
     * @return El número de celdas en la columna.
     */
    public int getCantidadCeldas() {
        return celdas.size();
    }

    /**
     * Representación textual de la columna, incluyendo su etiqueta, tipo de dato,
     * y los valores almacenados en las celdas.
     *
     * @return Una cadena que describe la columna.
     */
    @Override
    public String toString() {
        StringBuilder resultado = new StringBuilder("Columna{");
        resultado.append("etiqueta=").append(etiqueta);
        resultado.append(", tipoDeDato=").append(tipoDeDato.getSimpleName());
        resultado.append(", celdas=[");

        if (!celdas.isEmpty()) {
            for (int i = 0; i < celdas.size(); i++) {
                resultado.append(celdas.get(i).getValor());
                if (i < celdas.size() - 1) {
                    resultado.append(", ");
                }
            }
        } else {
            resultado.append("Sin celdas");
        }
        resultado.append("]}");
        return resultado.toString();
    }
}
