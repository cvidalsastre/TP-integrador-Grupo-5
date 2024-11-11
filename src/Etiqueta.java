/**
 * Clase abstracta que representa una etiqueta en una tabla.
 * Las etiquetas son identificadores únicos para filas o columnas.
 * Cada implementación concreta debe definir cómo devolver su valor asociado.
 */
public abstract class Etiqueta {

    /**
     * Devuelve el valor asociado a la etiqueta.
     * Este valor puede ser de cualquier tipo y dependerá de la implementación
     * concreta.
     *
     * @return El valor de la etiqueta.
     */
    public abstract Object getValor();
}
