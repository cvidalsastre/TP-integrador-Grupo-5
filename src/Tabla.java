import java.util.ArrayList;
import java.util.List;

public class Tabla<T> {
    private List<Columna<?>> columnas;
    private List<Etiqueta> etiquetasFilas;
    private List<Etiqueta> etiquetasColumnas;
    private int contadorFilas;  // Contador para etiquetas numéricas automáticas en filas
    private int contadorColumnas;  // Contador para etiquetas numéricas automáticas en columnas

    //Constructores
    public Tabla(){
        this.columnas = new ArrayList<>();
        this.etiquetasFilas = new ArrayList<>();
        this.etiquetasColumnas = new ArrayList<>();
        this.contadorFilas = 0;
        this.contadorColumnas = 0;
    }


    //Metodos

    // Agregar una columna nueva
     @SuppressWarnings("hiding")
    public <T> void agregarColumna(Class<T> tipoDeDato) {
        Etiqueta etiquetaColumna = new EtiquetaNumerica(contadorColumnas);  // Etiqueta secuencial automática
        agregarColumna(tipoDeDato, etiquetaColumna);
        contadorColumnas++;
    }
    // Sobrecarga de método para agregar columna con etiqueta específica
    @SuppressWarnings("hiding")
    public <T> void agregarColumna(Class<T> tipoDeDato, Etiqueta etiquetaColumna) {
        Columna<T> nuevaColumna = new Columna<>(etiquetaColumna, tipoDeDato);
        columnas.add(nuevaColumna);
        etiquetasColumnas.add(etiquetaColumna);

        // Añadir celdas "NA" en la nueva columna si ya existen filas
        for (int i = 0; i < etiquetasFilas.size(); i++) {
            nuevaColumna.agregarCelda(new Celda<>(null));  // Asignar NA
        }
    }
    
    //*****************agregar una columna con celdas ya creadas***********************

    public void eliminarColumna(Columna<T> columna){

    }

    // Agregar una fila nueva con etiqueta automática si no se especifica
    public void agregarFila(List<Celda<?>> celdas) {
        Etiqueta etiquetaFila = new EtiquetaNumerica(contadorFilas);  // Etiqueta secuencial automática
        agregarFila(celdas, etiquetaFila);
        contadorFilas++;
    }
    // Sobrecarga de método para agregar fila con etiqueta específica
    public void agregarFila(List<Celda<?>> celdas, Etiqueta etiquetaFila) {

    }
    

    public void eliminarFila(){

    }

    public void editarCelda(){

    }

    public void guardarTabla(){
        
    }

    // Obtener una fila completa
    public List<Celda<?>> getFila(Etiqueta etiquetaFila) {
        int indexFila = getIndex(etiquetaFila, etiquetasFilas);
        List<Celda<?>> fila = new ArrayList<>();
        for (Columna<?> col : columnas) {
            fila.add(col.getCeldas().get(indexFila));
        }
        return fila;
    }

    
    @SuppressWarnings("unchecked")
    public <T> Columna<T> getColumna(Etiqueta etiquetaColumna) {
        for (Columna<?> col : columnas) {
            if (col.getEtiqueta().equals(etiquetaColumna)) {
                return (Columna<T>) col;  // Cast explícito pero con supresión de warnings
            }
        }
        throw new IllegalArgumentException("Columna no encontrada");
    }

    // Método auxiliar para obtener el índice basado en la etiqueta
    private int getIndex(Etiqueta etiqueta, List<Etiqueta> etiquetas) {
        for (int i = 0; i < etiquetas.size(); i++) {
            if (etiquetas.get(i).getValor().equals(etiqueta.getValor())) {
                return i;
            }
        }
        throw new IllegalArgumentException("Etiqueta no encontrada");
    }

    public List<Etiqueta> getEtiquetasFilas() {
        return etiquetasFilas;
    }

    public List<Columna<?>> getColumnas() {
        return columnas;
    }

    public void getCantidadFilas(){

    }

    public int getCantidadColumnas() {
        return columnas.size();
    }
}