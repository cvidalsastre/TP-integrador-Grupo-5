package kapibara;
/**
 * Clase que representa una etiqueta basada en valores numéricos enteros.
 * Hereda de la clase abstracta {@link Etiqueta}.
 * Es utilizada como identificador único para filas o columnas con valores de
 * tipo entero.
 */
public class EtiquetaNumerica extends Etiqueta {
    private int valor;

    /**
     * Constructor que inicializa la etiqueta con un valor numérico entero.
     *
     * @param valor El valor numérico de la etiqueta.
     */
    public EtiquetaNumerica(int valor) {
        this.valor = valor;
    }

    /**
     * Devuelve el valor asociado a la etiqueta.
     *
     * @return El valor de la etiqueta como un objeto {@link Integer}.
     */
    @Override
    public Integer getValor() {
        return valor;
    }

    /**
     * Devuelve la representación en forma de cadena del valor de la etiqueta.
     * 
     * @return El valor de la etiqueta como cadena de texto.
     */
    @Override
    public String toString() {
        return Integer.toString(valor);
    }
}
