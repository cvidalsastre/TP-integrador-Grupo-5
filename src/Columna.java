import java.util.ArrayList;
import java.util.List;

public class Columna<T> {
    
    private List<Celda<T>> listaDeCeldas;
    private String etiquetaColumna;

    //Constructor
    public Columna() {
        this.listaDeCeldas = new ArrayList<>();
    }

    public Columna(List<T> columna){
        this.listaDeCeldas = new ArrayList<>();
        for(T elemento : columna){
            columna.add(elemento);
        }
    }

    public void cambiarNombreEtiqueta(String etiqueta){
        this.etiquetaColumna = etiqueta;
    }

    public T verContenidoCelda(int pos){
        return listaDeCeldas.get(pos).getValor();
    }

    //public String tipoDeDato(){
        
        //algo
    //}

    public int cantidadElementos(){
        return this.listaDeCeldas.size();
    }

    


    



}
