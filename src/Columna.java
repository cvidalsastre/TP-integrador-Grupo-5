import java.util.ArrayList;
import java.util.List;

public class Columna<T> {

    private List<Celda<T>> celdas;
    private Etiqueta etiqueta;
    private Class<T> tipoDeDato;

    // Constructor
    public Columna(Etiqueta etiqueta, Class<T> tipoDeDato) {
        this.etiqueta = etiqueta;
        this.tipoDeDato = tipoDeDato;
        this.celdas = new ArrayList<>();
    }

    public void agregarCelda(Celda<?> celda) {
        if (celda.getValor() != null && !tipoDeDato.isInstance(celda.getValor())) {
            throw new IllegalArgumentException("El valor de la celda no corresponde al tipo de dato de la columna");
        }
        // Cast seguro, ya que hemos verificado el tipo en tiempo de ejecución
        @SuppressWarnings("unchecked")
        Celda<T> celdaCast = (Celda<T>) celda;
        celdas.add(celdaCast);
    }

    public Etiqueta getEtiqueta() {
        return etiqueta;
    }

    public List<Celda<T>> getCeldas() {
        return celdas;
    }

    public Class<T> getTipoDeDato() {
        return tipoDeDato;
    }

    // Método para establecer una nueva lista de celdas (necesario para
    // ordenamiento)
    public void setCeldas(List<Celda<T>> nuevasCeldas) {
        this.celdas = nuevasCeldas;
    }

    public int getCantidadCeldas(){
        int cont = 0;
        for(Celda<T> celda : celdas){
            cont++;
        }
        return cont;
    }



    @Override
    public String toString() {
        String resultado = "Columna{";
        resultado += "etiqueta=" + etiqueta;
        resultado += ", tipoDeDato=" + tipoDeDato.getSimpleName();
        resultado += ", celdas=[";

        if (!celdas.isEmpty()) {
            for (int i = 0; i < celdas.size(); i++) {
                resultado += celdas.get(i).getValor();
                if (i < celdas.size() - 1) {
                    resultado += ", ";
                }
            }
        } else {
            resultado += "Sin celdas";
        }
        resultado += "]}";
        return resultado;
    }

}