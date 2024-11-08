import java.util.List;

public class Fila {
    private Etiqueta etiquetaFila;
    private List<Celda<?>> celdas;
    private List<Etiqueta> etiquetasCol;
    
    public Fila(Etiqueta etiquetaFila, List<Celda<?>> celdas, List<Etiqueta> etiquetasCol) {
        this.etiquetaFila = etiquetaFila;
        this.celdas = celdas;
        this.etiquetasCol = etiquetasCol;
    }

    public Etiqueta getEtiquetaFila(){
        return etiquetaFila;
    }

    public List<Celda<?>> getCeldasFila(){
        return celdas;
    }

    public List<Etiqueta> getEtiquetasColumnas(){
        return etiquetasCol;
    }

    @Override
    public String toString() {
        String salida = etiquetaFila + " | ";
        for(Celda<?> c : celdas){
            salida += c + " | ";
        }
        return salida;
    }







    
}


