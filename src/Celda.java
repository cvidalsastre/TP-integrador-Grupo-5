/**
 * Clase que representa una celda dentro de una tabla. Cada celda contiene un
 * valor genérico
 * que puede ser de cualquier tipo definido en tiempo de compilación.
 *
 * @param <T> El tipo de dato que almacena la celda.
 */
public class Celda<T> {

    /**
     * Valor almacenado en la celda.
     */
    private T valor;

    /**
     * Constructor que inicializa la celda con un valor especificado.
     *
     * @param valor Valor inicial de la celda.
     */
    public Celda(T valor) {
        this.valor = valor;
    }

    /**
     * Constructor que inicializa la celda sin valor (valor nulo).
     */
    public Celda() {
        this.valor = null;
    }

    /**
     * Obtiene el valor almacenado en la celda.
     *
     * @return El valor de la celda, o {@code null} si la celda está vacía.
     */
    public T getValor() {
        return valor;
    }

    /**
     * Obtiene el tipo del valor almacenado en la celda como un {@code String}.
     * Si la celda está vacía, se devuelve "NA".
     *
     * @return El nombre del tipo de dato almacenado en la celda, o "NA" si está
     *         vacía.
     */
    public String getTipo() {
        if (esNA()) {
            return "NA";
        }
        return valor.getClass().getSimpleName();
    }

    /**
     * Verifica si la celda está vacía (es decir, si su valor es nulo).
     *
     * @return {@code true} si la celda no tiene valor, {@code false} en caso
     *         contrario.
     */
    public boolean esNA() {
        return valor == null;
    }

    /**
     * Cambia el valor almacenado en la celda.
     *
     * @param valorNuevo El nuevo valor que se asignará a la celda.
     */
    public void cambiarValor(T valorNuevo) {
        this.valor = valorNuevo;
    }

    /**
     * Establece el valor de la celda como nulo, indicando que está vacía.
     */
    public void volverNA() {
        this.valor = null;
    }

    /**
     * Convierte el contenido de la celda a una representación en {@code String}.
     * Si la celda está vacía, se devuelve "NA".
     *
     * @return Una representación textual del valor de la celda.
     */
    @Override
    public String toString() {
        if (esNA()) {
            return "NA";
        }
        return String.valueOf(valor);
    }
}
