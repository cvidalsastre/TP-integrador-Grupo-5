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
