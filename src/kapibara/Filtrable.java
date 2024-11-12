package kapibara;

public interface Filtrable{

    public <T extends Comparable<T>> Tabla filtrar(Etiqueta etiquetaColumna, T valorReferencia, String operador);

}
