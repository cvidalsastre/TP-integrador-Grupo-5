// Clase abstracta para una Etiqueta
public abstract class Etiqueta {
    public abstract Object getValor();  // Cada tipo de etiqueta debe devolver un valor
}

// Etiqueta numérica (por defecto)
public class EtiquetaNumerica extends Etiqueta {
    private int valor;

    public EtiquetaNumerica(int valor) {
        this.valor = valor;
    }

    @Override
    public Integer getValor() {
        return valor;
    }
}

// Etiqueta de cadena
public class EtiquetaCadena extends Etiqueta {
    private String valor;

    public EtiquetaCadena(String valor) {
        this.valor = valor;
    }

    @Override
    public String getValor() {
        return valor;
    }
}