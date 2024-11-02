import java.util.ArrayList;
import java.util.List;

public class Tabla implements Visualizable {
    private List<Columna<?>> columnas;
    private List<Etiqueta> etiquetasFilas;
    private List<Etiqueta> etiquetasColumnas;

    // Constructores
    public Tabla() {
        this.columnas = new ArrayList<>();
        this.etiquetasFilas = new ArrayList<>();
        this.etiquetasColumnas = new ArrayList<>();
    }

    // Metodos

    // Agregar una columna nueva
    public void agregarColumna(Class<?> tipoDeDato) {
        Etiqueta etiquetaColumna = new EtiquetaNumerica(getCantidadColumnas() + 1); // Etiqueta secuencial automática
        agregarColumna(tipoDeDato, etiquetaColumna);
    }

    // Sobrecarga de método para agregar columna con etiqueta específica
    public void agregarColumna(Class<?> tipoDeDato, Etiqueta etiquetaColumna) {
        Columna<?> nuevaColumna = new Columna<>(etiquetaColumna, tipoDeDato);
        columnas.add(nuevaColumna);
        etiquetasColumnas.add(etiquetaColumna);

        // Añadir celdas "NA" en la nueva columna si ya existen filas
        for (int i = 0; i < etiquetasFilas.size(); i++) {
            nuevaColumna.agregarCelda(new Celda<>(null)); // Asignar NA
        }
    }

    public void eliminarColumna(Etiqueta e) {
        int indiceColumnaABorrar = getIndex(e, etiquetasColumnas);
        columnas.remove(indiceColumnaABorrar);
        etiquetasColumnas.remove(indiceColumnaABorrar);

    }

    // Agregar una fila nueva con etiqueta automática si no se especifica
    public void agregarFila() {

        Etiqueta etiquetaFila = new EtiquetaNumerica(getCantidadFilas());
        List<Celda<?>> celdas = new ArrayList<>();

        for (Columna<?> columna : columnas) {
            celdas.add(crearCeldaNula(columna.getTipoDeDato()));
        }

        agregarFila(celdas, etiquetaFila);
    }

    // Método para agregar una fila sin necesidad de especificar etiqueta
    public void agregarFila(List<Celda<?>> celdas) {
        Etiqueta etiquetaFila = new EtiquetaNumerica(getCantidadFilas()); // Generar automáticamente la etiqueta
        agregarFila(celdas, etiquetaFila); // Llama al método sobrecargado
    }

    public void agregarFila(List<Celda<?>> celdas, Etiqueta etiquetaFila) {
        if (celdas.size() != columnas.size()) {
            throw new IllegalArgumentException("El número de celdas no coincide con el número de columnas");
        }

        etiquetasFilas.add(etiquetaFila);

        for (int i = 0; i < columnas.size(); i++) {
            Columna<?> columna = columnas.get(i);
            Celda<?> celda = celdas.get(i);

            // Verifica si el tipo de la celda es correcto
            if (celda.getValor() != null && !columna.getTipoDeDato().isInstance(celda.getValor())) {
                throw new IllegalArgumentException("El valor de la celda no corresponde al tipo de dato de la columna");
            }

            columna.agregarCelda(celda);
        }
    }

    

    // Método auxiliar para crear una celda nula de un tipo específico
    private <T> Celda<T> crearCeldaNula(Class<T> tipo) {
        return new Celda<>(null); // Crea una celda nula del tipo adecuado
    }

    public void eliminarFila(Etiqueta e) {
        // Si la etiqueta e no existe getIndex tira excepción
        int indiceFilaABorrar = getIndex(e, etiquetasFilas);
        for (int nroColumna = 0; nroColumna < columnas.size(); nroColumna++) {
            columnas.get(nroColumna).getCeldas().remove(indiceFilaABorrar);
        }
        etiquetasFilas.remove(indiceFilaABorrar); 
    }

     // Obtener una fila con los N primeras columnas
     // Ver si getFila puede llamarla con columnas.size()

     public List<Celda<?>> getFilaAcotada(Etiqueta etiquetaFila, int cantColumnas) {
        if (cantColumnas > getCantidadColumnas() || cantColumnas <= 0){
            throw new IllegalArgumentException("La cantidad de columnnas debe ser mayor que 0 y menor que la cantidad de columnas de la tabla.");
        }
        int indexFila = getIndex(etiquetaFila, etiquetasFilas);
        List<Celda<?>> fila = new ArrayList<>();
        for (Columna<?> col : columnas.subList(0, cantColumnas)) {
            fila.add(col.getCeldas().get(indexFila));
        }
        return fila;
    }

    private String filaACadena(List<Celda<?>> fila, int maxLargoCadena){
        String salida = " | ";
        for (int nroColumna = 0; nroColumna < fila.size(); nroColumna++){
            Celda<?> celda = fila.get(nroColumna);
            if (celda.getTipo().equals("String")){
                int longitudMenor = Math.min(celda.toString().length(), maxLargoCadena);
                salida += celda.toString().substring(0, longitudMenor);
            }else{
                salida += celda.toString();
            }
            salida += " | ";
        }
        return salida;
    }

    public void visualizar(int maxFilas, int maxColumnas, int maxLargoCadena){
        boolean columnasOk = maxColumnas > 0 && maxColumnas <= getCantidadColumnas();
        boolean filasOk = maxFilas > 0 && maxFilas <= getCantidadFilas();
        boolean largoCadenaOk = maxLargoCadena > 0;
        if (!columnasOk || !filasOk || !largoCadenaOk){
            throw new IllegalArgumentException("Los parámetros ingresados no permiten visualizar la tabla");
        }
        for(Etiqueta e: etiquetasFilas.subList(0, maxColumnas)){
            System.out.println(filaACadena(getFila(e), maxLargoCadena));
        }
    }

    
    public void editarCelda(Etiqueta etiquetaFila, Etiqueta etiquetaColumna, String valor ) {
        Class<?> tipoColumna = getColumna(etiquetaColumna).getTipoDeDato();

        if (valor != null && !tipoColumna.isInstance(valor)) {
            throw new IllegalArgumentException("El tipo de dato de la celda no es el mismo que el del nuevo valor.");
        }
        int indiceFila = getIndex(etiquetaFila, etiquetasFilas);
        getColumna(etiquetaColumna).getCeldas().get(indiceFila).cambiarValor(valor);
        

    }
    public void  editarCelda(Etiqueta etiquetaFila, Etiqueta etiquetaColumna, Number valor){
        Class<?> tipoColumna = getColumna(etiquetaColumna).getTipoDeDato();

        if (valor != null && !tipoColumna.isInstance(valor)) {
            throw new IllegalArgumentException("El tipo de dato de la celda no es el mismo que el del nuevo valor.");
        }
        int indiceFila = getIndex(etiquetaFila, etiquetasFilas);
        getColumna(etiquetaColumna).getCeldas().get(indiceFila).cambiarValor(valor);
        
    }

    public void  editarCelda(Etiqueta etiquetaFila, Etiqueta etiquetaColumna, boolean valor){
        Class<?> tipoColumna = getColumna(etiquetaColumna).getTipoDeDato();

        if (!tipoColumna.isInstance(valor)) {
            throw new IllegalArgumentException("El tipo de dato de la celda no es el mismo que el del nuevo valor.");
        }
        int indiceFila = getIndex(etiquetaFila, etiquetasFilas);
        getColumna(etiquetaColumna).getCeldas().get(indiceFila).cambiarValor(valor);
        
    }

    public void volverNACelda(Etiqueta etiquetaFila, Etiqueta etiquetaColumna){
        int indiceFila = getIndex(etiquetaFila, etiquetasFilas);
        getColumna(etiquetaColumna).getCeldas().get(indiceFila).volverNA();
    }

    public Celda<?> getCelda(Etiqueta etiquetaFila, Etiqueta etiquetaColumna){
        int indiceFila = getIndex(etiquetaFila, etiquetasFilas);
        return getColumna(etiquetaColumna).getCeldas().get(indiceFila);
    }

    public void guardarTabla() {

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

    public <T> Columna<T> getColumna(Etiqueta etiquetaColumna) {
        for (Columna<?> col : columnas) {
            if (col.getEtiqueta().getValor().equals(etiquetaColumna.getValor())) {
                return (Columna<T>) col; // Cast explícito pero con supresión de warnings
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

    public int getCantidadFilas() {
        return etiquetasFilas.size();
    }

    public List<Etiqueta> getEtiquetasColumnas() {
        return etiquetasColumnas;
    }

    public int getCantidadColumnas() {
        return columnas.size();
    }
}