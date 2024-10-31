import java.util.ArrayList;
import java.util.List;

public class Columna<T> {
    
    private List<Celda<T>> celdas;
    private Etiqueta etiqueta;
    private Class<T> tipoDeDato;

    //Constructor
    public Columna(Etiqueta etiqueta, Class<T> tipoDeDato){
        this.etiqueta = etiqueta;
        this.tipoDeDato=tipoDeDato;
        this.celdas=new ArrayList<>();
    }

    public void agregarCelda(Celda<T> celda){
        // Validar el tipo de dato antes de agregar
        if (celda.getValor() != null && !tipoDeDato.isInstance(celda.getValor())) {
            throw new IllegalArgumentException("El valor de la celda no corresponde al tipo de dato de la columna");
        }
        celdas.add(celda);
    }

    public Etiqueta getEtiqueta(){
        return etiqueta;
    }

    public List<Celda<T>> getCeldas(){
        return celdas;
    }

    public Class<T> getTipoDeDato(){
        return tipoDeDato;
    }

}