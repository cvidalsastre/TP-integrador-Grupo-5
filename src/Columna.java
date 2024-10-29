import java.util.ArrayList;
import java.util.List;



public class Columna<T> {
    
    private List<Celda<T>> tirita;
    private String etiquetaColumna;

    //Constructor
    public Columna(s) {
        this.columna = new ArrayList<>();
        this.etiquetaColumna = new String("");
    }

    public Columna(List<T> columna){
        this.columna = new ArrayList<>();
        for(T elemento : columna){
            columna.add(elemento);
        }
        this.etiquetaColumna = new String();        
    }

    public void cambiarNombreEtiqueta(String etiqueta){
        this.etiquetaColumna = etiqueta;
    }

    public T verContenidoCelda(int pos){
        return columna.get(pos).getValor();
    }

    public String tipoDato

    public int cantidadElementos(){
        return this.columna.size();
    }

    


    



}
