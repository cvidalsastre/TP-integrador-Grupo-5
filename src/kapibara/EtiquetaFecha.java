package kapibara;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Clase que representa una etiqueta basada en fechas.
 * Hereda de la clase abstracta {@link Etiqueta}.
 * Es utilizada como identificador único para filas o columnas con valores de tipo LocalDate.
 */
public class EtiquetaFecha extends Etiqueta {
    private LocalDate valor;
    private static final DateTimeFormatter FORMATO_DEFAULT = DateTimeFormatter.ISO_LOCAL_DATE;

    /**
     * Constructor que inicializa la etiqueta con un valor de tipo LocalDate.
     *
     * @param valor El valor de la etiqueta como objeto LocalDate.
     */
    public EtiquetaFecha(LocalDate valor) {
        this.valor = valor;
    }

    /**
     * Constructor que inicializa la etiqueta desde una cadena en formato ISO_LOCAL_DATE.
     *
     * @param valor El valor de la etiqueta como cadena.
     */
    public EtiquetaFecha(String valor) {
        this.valor = LocalDate.parse(valor, FORMATO_DEFAULT);
    }

    /**
     * Devuelve el valor asociado a la etiqueta.
     *
     * @return El valor de la etiqueta como objeto LocalDate.
     */
    @Override
    public LocalDate getValor() {
        return valor;
    }

    /**
     * Devuelve la representación en forma de cadena de la etiqueta.
     *
     * @return El valor de la etiqueta como una cadena en formato ISO_LOCAL_DATE.
     */
    @Override
    public String toString() {
        return valor.format(FORMATO_DEFAULT);
    }

    /**
     * Permite cambiar el formato de salida para las fechas.
     *
     * @param formato El nuevo formato de salida.
     * @return Una cadena que representa la fecha en el formato especificado.
     */
    public String toString(DateTimeFormatter formato) {
        return valor.format(formato);
    }
}