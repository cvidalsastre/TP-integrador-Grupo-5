import java.util.ArrayList;
import java.util.List;

public class Columna<T> {
    
    private List<Celda<T>> columna;
    private T etiquetaColumna;

    //Constructor
    public Columna() {
        this.columna = new ArrayList<>();
    }

    public Columna(List<T> columna){
        this.columna = new ArrayList<>();
        for(T elemento : columna){
            columna.add(elemento);
        }        
    }

    


    



}
