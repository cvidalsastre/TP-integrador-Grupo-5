// Clase Filtro debería ser algo así
public class Filtro<T extends Comparable<T>> {
    private Etiqueta columna;
    private T valorReferencia;
    private String operador; // "<", ">", "="

    public Filtro(Etiqueta columna, T valorReferencia, String operador) {
        this.columna = columna;
        this.valorReferencia = valorReferencia;
        this.operador = operador;
    }

    public Etiqueta getColumna() {
        return columna;
    }

    public boolean evaluar(Celda<T> celda) {
        if (celda.esNA()) {
            return false;
        }
        int comparacion = celda.getValor().compareTo(valorReferencia);
        switch (operador) {
            case "<":
                return comparacion < 0;
            case ">":
                return comparacion > 0;
            case "=":
                return comparacion == 0;
            default:
                throw new IllegalArgumentException("Operador no soportado: " + operador);
        }
    }
}
