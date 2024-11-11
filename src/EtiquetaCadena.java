/**
 * Clase que representa una etiqueta basada en cadenas de texto.
 * Hereda de la clase abstracta {@link Etiqueta}.
 * Es utilizada como identificador único para filas o columnas con valores de
 * tipo String.
 */
public class EtiquetaCadena extends Etiqueta {
    private String valor;

    /**
     * Constructor que inicializa la etiqueta con un valor de tipo String.
     *
     * @param valor El valor de la etiqueta.
     */
    public EtiquetaCadena(String valor) {
        this.valor = valor;
    }

    /**
     * Devuelve el valor asociado a la etiqueta.
     *
     * @return El valor de la etiqueta como cadena de texto.
     */
    @Override
    public String getValor() {
        return valor;
    }

    /**
     * Devuelve la representación en forma de cadena de la etiqueta.
     * 
     * @return El valor de la etiqueta como cadena de texto.
     */
    @Override
    public String toString() {
        return valor;
    }
}
