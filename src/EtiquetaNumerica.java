// Etiqueta numérica 
public class EtiquetaNumerica extends Etiqueta {
    private int valor;

    public EtiquetaNumerica(int valor) {
        this.valor = valor;
    }

    @Override
    public Integer getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return Integer.toString(valor);
    }

    
}

