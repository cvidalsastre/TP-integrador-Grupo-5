import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class Tabla implements Visualizable{

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
        Etiqueta etiquetaColumna = new EtiquetaNumerica(getCantidadColumnas()); // Etiqueta secuencial automática
        agregarColumna(tipoDeDato, etiquetaColumna);
    }

    // Sobrecarga de método para agregar columna con etiqueta específica
    // 5/11/24 agregamos la condición de que no existe la etiqueta a agregar
    public void agregarColumna(Class<?> tipoDeDato, Etiqueta etiquetaColumna) {
        if (tieneLaEtiqueta(etiquetaColumna, etiquetasColumnas)) {
            throw new IllegalArgumentException("Ya existe una columna con esa etiqueta.");
        }
        Columna<?> nuevaColumna = new Columna<>(etiquetaColumna, tipoDeDato);
        columnas.add(nuevaColumna);
        etiquetasColumnas.add(etiquetaColumna);

        // Añadir celdas "NA" en la nueva columna si ya existen filas
        for (int i = 0; i < etiquetasFilas.size(); i++) {
            nuevaColumna.agregarCelda(new Celda<>(null)); // Asignar NA
        }
    }

    public void agregarColumna(Class<?> tipoDeDato, Columna<?> nuevaColumna) {
        Etiqueta etiquetaColumna = new EtiquetaNumerica(getCantidadColumnas()); // Etiqueta secuencial automática
        if (tieneLaEtiqueta(etiquetaColumna, etiquetasColumnas)) {
            throw new IllegalArgumentException("Ya existe una columna con esa etiqueta.");
        }
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
        if (tieneLaEtiqueta(etiquetaFila, etiquetasFilas)) {
            throw new IllegalArgumentException("Ya existe una fila con esa etiqueta.");
        }
        if (celdas.size() != columnas.size()) {
            throw new IllegalArgumentException("El número de celdas no coincide con el número de columnas");
        }

        for (int i = 0; i < columnas.size(); i++) {
            Columna<?> columna = columnas.get(i);
            Celda<?> celda = celdas.get(i);

            // Verifica si el tipo de la celda es correcto
            if (celda.getValor() != null && !columna.getTipoDeDato().isInstance(celda.getValor())) {
                throw new IllegalArgumentException("El valor de la celda no corresponde al tipo de dato de la columna");
            }

            columna.agregarCelda(celda);
        }
        // Si está todo OK agregamos la etiqueta de Fila
        etiquetasFilas.add(etiquetaFila);

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
        if (cantColumnas > getCantidadColumnas() || cantColumnas <= 0) {
            throw new IllegalArgumentException(
                    "La cantidad de columnnas debe ser mayor que 0 y menor o igual que la cantidad de columnas de la tabla.");
        }
        int indexFila = getIndex(etiquetaFila, etiquetasFilas);
        List<Celda<?>> fila = new ArrayList<>();
        for (Columna<?> col : columnas.subList(0, cantColumnas)) {
            fila.add(col.getCeldas().get(indexFila));
        }
        return fila;
    }

    /*
     * private String filaACadena(List<Celda<?>> fila, int maxLargoCadena) {
     * String salida = " | ";
     * for (int nroColumna = 0; nroColumna < fila.size(); nroColumna++) {
     * Celda<?> celda = fila.get(nroColumna);
     * if (celda.getTipo().equals("String")) {
     * int longitudMenor = Math.min(celda.toString().length(), maxLargoCadena);
     * salida += celda.toString().substring(0, longitudMenor);
     * if(longitudMenor < celda.toString().length()){
     * salida += "...";
     * }
     * } else {
     * salida += celda.toString();
     * }
     * salida += " | ";
     * }
     * return salida;
     * }
     */

    private String filaACadena(List<Celda<?>> fila, int maxLargoCadena) {
        String salida = " | ";
        for (int nroColumna = 0; nroColumna < fila.size(); nroColumna++) {
            Celda<?> celda = fila.get(nroColumna);
            String textoCelda = celda.toString();

            if (celda.getTipo().equals("String")) {
                int longitudMenor = Math.min(textoCelda.length(), maxLargoCadena);
                textoCelda = textoCelda.substring(0, longitudMenor);
                if (longitudMenor < celda.toString().length()) {
                    textoCelda += "...";
                }
            }

            // Centramos el texto en el ancho máximo de la celda
            String textoCentrado = centrarTexto(textoCelda, maxLargoCadena);

            salida += textoCentrado + " | ";
        }
        return salida;
    }

    private String encabezadosACadena(List<Etiqueta> encabezados, int maxLargoCadena) {
        String salida = " | ";
        for (Etiqueta etiqueta : encabezados) {
            String texto = etiqueta.toString();

            // Centramos el texto de los encabezados
            String textoCentrado = centrarTexto(texto, maxLargoCadena);

            // Añadimos el encabezado centrado
            salida += textoCentrado + " | ";
        }
        return salida;
    }

    // Verificación simplificada: Ahora hay una única verificación al inicio para
    // asegurarse de que maxFilas, maxColumnas, y maxLargoCadena son mayores que 0.
    public void visualizar(int maxFilas, int maxColumnas, int maxLargoCadena) {
        System.out.println("maxFilas: " + maxFilas);
        System.out.println("maxColumnas: " + maxColumnas);
        System.out.println("maxLargoCadena: " + maxLargoCadena);
        System.out.println("Cantidad de filas: " + getCantidadFilas());
        System.out.println("Cantidad de columnas: " + getCantidadColumnas());

        if (maxFilas <= 0 || maxColumnas <= 0 || maxLargoCadena <= 0) {
            throw new IllegalArgumentException(
                    "Los parámetros maxFilas, maxColumnas y maxLargoCadena deben ser mayores que 0.");
        }

        if (maxFilas > getCantidadFilas()) {
            maxFilas = getCantidadFilas(); // Ajustar para mostrar todas las filas disponibles
        }

        if (maxColumnas > getCantidadColumnas()) {
            maxColumnas = getCantidadColumnas(); // Ajustar para mostrar todas las columnas disponibles
        }

        // Imprimir los encabezados centrados
        System.out.println(encabezadosACadena(etiquetasColumnas.subList(0, maxColumnas), maxLargoCadena));

        // Imprimir las filas de datos
        for (Etiqueta e : etiquetasFilas.subList(0, maxFilas)) {
            System.out.println(filaACadena(getFilaAcotada(e, maxColumnas), maxLargoCadena));
        }
    }

    public void editarCelda(Etiqueta etiquetaFila, Etiqueta etiquetaColumna, String valor) {
        Class<?> tipoColumna = getColumna(etiquetaColumna).getTipoDeDato();

        if (valor != null && !tipoColumna.isInstance(valor)) {
            throw new IllegalArgumentException("El tipo de dato de la celda no es el mismo que el del nuevo valor.");
        }
        int indiceFila = getIndex(etiquetaFila, etiquetasFilas);
        getColumna(etiquetaColumna).getCeldas().get(indiceFila).cambiarValor(valor);

    }

    public void editarCelda(Etiqueta etiquetaFila, Etiqueta etiquetaColumna, Number valor) {
        Class<?> tipoColumna = getColumna(etiquetaColumna).getTipoDeDato();

        if (valor != null && !tipoColumna.isInstance(valor)) {
            throw new IllegalArgumentException("El tipo de dato de la celda no es el mismo que el del nuevo valor.");
        }
        int indiceFila = getIndex(etiquetaFila, etiquetasFilas);
        getColumna(etiquetaColumna).getCeldas().get(indiceFila).cambiarValor(valor);

    }

    public void editarCelda(Etiqueta etiquetaFila, Etiqueta etiquetaColumna, boolean valor) {
        Class<?> tipoColumna = getColumna(etiquetaColumna).getTipoDeDato();

        if (!tipoColumna.isInstance(valor)) {
            throw new IllegalArgumentException("El tipo de dato de la celda no es el mismo que el del nuevo valor.");
        }
        int indiceFila = getIndex(etiquetaFila, etiquetasFilas);
        getColumna(etiquetaColumna).getCeldas().get(indiceFila).cambiarValor(valor);

    }

    public void volverNACelda(Etiqueta etiquetaFila, Etiqueta etiquetaColumna) {
        int indiceFila = getIndex(etiquetaFila, etiquetasFilas);
        getColumna(etiquetaColumna).getCeldas().get(indiceFila).volverNA();
    }

    public Celda<?> getCelda(Etiqueta etiquetaFila, Etiqueta etiquetaColumna) {
        int indiceFila = getIndex(etiquetaFila, etiquetasFilas);
        return getColumna(etiquetaColumna).getCeldas().get(indiceFila);
    }

    public List<List<Celda<?>>> muestreo(float porcentaje) {
        if (porcentaje < 0.0 || porcentaje > 100.0) {
            throw new IllegalArgumentException("El porcentaje va de 0 a 100.");
        }
        int tamanioMuesta = (int) ((porcentaje / 100) * getCantidadFilas());
        return elegirNFilas(tamanioMuesta, etiquetasFilas);
    }

    private List<List<Celda<?>>> elegirNFilas(int tamanio, List<Etiqueta> etiquetasFilas) {
        List<List<Celda<?>>> filasRandom = new ArrayList<>();
        // se copia la lista de etiqueta de las filas
        List<Etiqueta> copiaEtiquetasFilas = new ArrayList<>(etiquetasFilas);

        // mezcla
        Collections.shuffle(copiaEtiquetasFilas);

        copiaEtiquetasFilas = copiaEtiquetasFilas.subList(0, Math.min(tamanio, copiaEtiquetasFilas.size()));

        for (Etiqueta e : copiaEtiquetasFilas) {
            filasRandom.add(getFila(e));
        }
        return filasRandom;
    }

    private boolean estanTodasLasEtiquetas(List<Etiqueta> seleccion, List<Etiqueta> etiquetas) {
        for (Etiqueta e : seleccion) {
            if (!tieneLaEtiqueta(e, etiquetas)) {
                return false;
            }
        }
        return true;
    }

    private boolean tieneLaEtiqueta(Etiqueta e, List<Etiqueta> etiquetas) {
        for (Etiqueta label : etiquetas) {
            if (e.getValor().equals(label.getValor())) {
                return true;
            }
        }
        return false;
    }

    private List<Etiqueta> conservarLasQueComparten(List<Etiqueta> seleccion, List<Etiqueta> etiquetas) {
        List<Etiqueta> copiaEtiquetas = new ArrayList<>(etiquetas);
        for (Etiqueta e : etiquetas) {
            if (!tieneLaEtiqueta(e, seleccion)) {
                copiaEtiquetas.remove(e);
            }
        }
        return copiaEtiquetas;
    }

    private int contarApariciones(Etiqueta buscada, List<Etiqueta> etiquetas) {
        int numeroDeApariciones = 0;
        for (Etiqueta e : etiquetas) {
            if (buscada.getValor().equals(e.getValor())) {
                numeroDeApariciones++;
            }
        }
        return numeroDeApariciones;
    }

    private boolean tieneRepetidos(List<Etiqueta> etiquetas) {
        for (Etiqueta e : etiquetas) {
            if (contarApariciones(e, etiquetas) > 1) {
                return true;
            }
        }
        return false;
    }

    // Suponemos que las etiquetas podrían tener un orden distinto al de la tabla
    public List<List<Celda<?>>> seleccionParcial(List<Etiqueta> seleccionEtiquetasFilas,
            List<Etiqueta> seleccionEtiquetasColumnas) {

        if (tieneRepetidos(seleccionEtiquetasColumnas) || tieneRepetidos(seleccionEtiquetasFilas)) {
            throw new IllegalArgumentException("Las listas de etiquetas no pueden tener elementos repetidos.");
        }

        // se chequea que no haya equipo que no son las de la tabla
        if (!estanTodasLasEtiquetas(seleccionEtiquetasFilas, etiquetasFilas) ||
                !estanTodasLasEtiquetas(seleccionEtiquetasColumnas, etiquetasColumnas)) {
            throw new IllegalArgumentException("Alguna/s de la/s etiqueta/s seleccionada/s no pertenece/n a la tabla.");
        }

        List<List<Celda<?>>> tablaRebanada = new ArrayList<>();
        // Esto garantiza que las etiquetas tengan el mismo orden que en la tabla
        List<Etiqueta> etiquetasColumnasSelec = conservarLasQueComparten(seleccionEtiquetasColumnas, etiquetasColumnas);
        List<Etiqueta> etiquetasFilasSelec = conservarLasQueComparten(seleccionEtiquetasFilas, etiquetasFilas);
        for (Etiqueta e : etiquetasFilasSelec) {
            tablaRebanada.add(getFilaAcotada(e, etiquetasColumnasSelec));
        }

        return tablaRebanada;
    }

    private List<Celda<?>> getFilaAcotada(Etiqueta etiquetaFila, List<Etiqueta> etiquetasColumnasSel) {

        if (!tieneLaEtiqueta(etiquetaFila, etiquetasFilas)) {
            throw new IllegalArgumentException("La etiqueta de la fila no existe en la tabla");
        }

        if (!estanTodasLasEtiquetas(etiquetasColumnasSel, etiquetasColumnas)) {
            throw new IllegalArgumentException(
                    "Hay una etiqueta de columna de las elegidas que no se encuentra en la tabla.");
        }
        int indexFila = getIndex(etiquetaFila, etiquetasFilas);
        List<Celda<?>> fila = new ArrayList<>();
        for (Columna<?> col : columnas) {
            Etiqueta e = col.getEtiqueta();
            if (tieneLaEtiqueta(e, etiquetasColumnasSel)) {
                fila.add(col.getCeldas().get(indexFila));
            }

        }
        return fila;
    }

    public List<List<Celda<?>>> head(int cantidadFilas) {
        if (cantidadFilas < 0) {
            throw new IllegalArgumentException("La cantidad de filas debe ser mayor o igual a cero.");
        }
        List<List<Celda<?>>> primerasFilas = new ArrayList<>();
        List<Etiqueta> etiquetasPrimFilas = etiquetasFilas.subList(0, Math.min(cantidadFilas, etiquetasFilas.size()));
        for (Etiqueta e : etiquetasPrimFilas) {
            primerasFilas.add(getFila(e));
        }
        return primerasFilas;
    }

    public List<List<Celda<?>>> tail(int cantidadFilas) {
        if (cantidadFilas < 0) {
            throw new IllegalArgumentException("La cantidad de filas debe ser mayor o igual a cero.");
        }
        List<List<Celda<?>>> ultimasFilas = new ArrayList<>();
        int cantidadEquitasFilas = etiquetasFilas.size();
        List<Etiqueta> etiquetasUltimasFilas = etiquetasFilas
                .subList(cantidadEquitasFilas - Math.min(cantidadFilas, cantidadEquitasFilas), cantidadEquitasFilas);
        for (Etiqueta e : etiquetasUltimasFilas) {
            ultimasFilas.add(getFila(e));
        }
        return ultimasFilas;
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

    // No sé si sirve
    public Fila getFila2(Etiqueta etiquetaFila) {
        int indexFila = getIndex(etiquetaFila, etiquetasFilas);
        List<Celda<?>> celdasFilas = new ArrayList<>();
        for (Columna<?> col : columnas) {
            celdasFilas.add(col.getCeldas().get(indexFila));
        }
        return new Fila(etiquetaFila, celdasFilas, etiquetasColumnas);
    }

    private Fila getFilaAcotada2(Etiqueta etiquetaFila, List<Etiqueta> etiquetasColumnasSel) {
        if (!tieneLaEtiqueta(etiquetaFila, etiquetasFilas)) {
            throw new IllegalArgumentException("La etiqueta de la fila no existe en la tabla");
        }

        if (!estanTodasLasEtiquetas(etiquetasColumnasSel, etiquetasColumnas)) {
            throw new IllegalArgumentException(
                    "Hay una etiqueta de columna de las elegidas que no se encuentra en la tabla.");
        }
        int indexFila = getIndex(etiquetaFila, etiquetasFilas);
        List<Celda<?>> celdasFila = new ArrayList<>();
        for (Columna<?> col : columnas) {
            Etiqueta e = col.getEtiqueta();
            if (tieneLaEtiqueta(e, etiquetasColumnasSel)) {
                celdasFila.add(col.getCeldas().get(indexFila));
            }

        }
        return new Fila(etiquetaFila, celdasFila, etiquetasColumnasSel);
    }

    public List<Fila> head2(int cantidadFilas) {
        if (cantidadFilas < 0) {
            throw new IllegalArgumentException("La cantidad de filas debe ser mayor o igual a cero.");
        }
        List<Fila> primerasFilas = new ArrayList<>();
        List<Etiqueta> etiquetasPrimFilas = etiquetasFilas.subList(0, Math.min(cantidadFilas, etiquetasFilas.size()));
        for (Etiqueta e : etiquetasPrimFilas) {
            Fila nueva_fila = new Fila(e, getFila2(e).getCeldasFila(), etiquetasColumnas);
            primerasFilas.add(nueva_fila);
        }
        return primerasFilas;
    }

    public List<Fila> tail2(int cantidadFilas) {
        if (cantidadFilas < 0) {
            throw new IllegalArgumentException("La cantidad de filas debe ser mayor o igual a cero.");
        }
        List<Fila> ultimasFilas = new ArrayList<>();
        int cantidadEquitasFilas = etiquetasFilas.size();
        List<Etiqueta> etiquetasUltimasFilas = etiquetasFilas
                .subList(cantidadEquitasFilas - Math.min(cantidadFilas, cantidadEquitasFilas), cantidadEquitasFilas);
        for (Etiqueta e : etiquetasUltimasFilas) {
            Fila nueva_fila = new Fila(e, getFila2(e).getCeldasFila(), etiquetasColumnas);
            ultimasFilas.add(nueva_fila);
        }
        return ultimasFilas;
    }

    public void imprimirEncabezados2(List<Etiqueta> etiquetas) {
        String salida = " | ";

        for (int i = 0; i < etiquetas.size(); i++) {
            salida += etiquetas.get(i);
            salida += " | ";
        }

        System.out.println(salida);
    }

    // Método para imprimir todas las filas y encabezados
    public void imprimirFilas2(List<Fila> filas) {
        if (!filas.isEmpty()) {

            // Imprimir los encabezados
            imprimirEncabezados2(etiquetasColumnas);

            // Imprimir las filas de datos, cada una centrada
            for (Fila fila : filas) {
                System.out.println(fila);
            }
        }
    }

    private String filaACadena2(Fila fila, int maxColumnas, int maxLargoCadena) {
        String salida = fila.getEtiquetaFila().toString() + " | ";
        maxColumnas = Math.min(maxColumnas, fila.getCeldasFila().size());

        for (int nroColumna = 0; nroColumna < maxColumnas; nroColumna++) {
            Celda<?> celda = fila.getCeldasFila().get(nroColumna);
            String textoCelda = celda.toString();

            if (celda.getTipo().equals("String")) {
                int longitudMenor = Math.min(textoCelda.length(), maxLargoCadena);
                textoCelda = textoCelda.substring(0, longitudMenor);
                if (longitudMenor < celda.toString().length()) {
                    textoCelda += "...";
                }
            }
            salida += textoCelda;
            salida += " | ";
        }
        return salida;
    }

    // Verificación simplificada: Ahora hay una única verificación al inicio para
    // asegurarse de que maxFilas, maxColumnas, y maxLargoCadena son mayores que 0.
    public void visualizar2(int maxFilas, int maxColumnas, int maxLargoCadena) {
        System.out.println("maxFilas: " + maxFilas);
        System.out.println("maxColumnas: " + maxColumnas);
        System.out.println("maxLargoCadena: " + maxLargoCadena);
        System.out.println("Cantidad de filas: " + getCantidadFilas());
        System.out.println("Cantidad de columnas: " + getCantidadColumnas());

        if (maxFilas <= 0 || maxColumnas <= 0 || maxLargoCadena <= 0) {
            throw new IllegalArgumentException(
                    "Los parámetros maxFilas, maxColumnas y maxLargoCadena deben ser mayores que 0.");
        }

        if (maxFilas > getCantidadFilas()) {
            maxFilas = getCantidadFilas(); // Ajustar para mostrar todas las filas disponibles
        }

        if (maxColumnas > getCantidadColumnas()) {
            maxColumnas = getCantidadColumnas(); // Ajustar para mostrar todas las columnas disponibles
        }

        // Imprimir los encabezados centrados
        imprimirEncabezados2(etiquetasColumnas.subList(0, maxColumnas));

        // Imprimir las filas de datos
        for (Etiqueta e : etiquetasFilas.subList(0, maxFilas)) {
            System.out.println(filaACadena2(getFila2(e), maxColumnas, maxLargoCadena));
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////
    // Función auxiliar para calcular el ancho máximo de cada columna (incluyendo
    ////////////////////////////////////////////////////////////////////////////////////// los
    ////////////////////////////////////////////////////////////////////////////////////// encabezados)
    private int[] calcularAnchos(List<List<Celda<?>>> filas, List<Etiqueta> encabezados) {
        int numColumnas = encabezados.size(); // Número de columnas es igual al tamaño de los encabezados
        int[] anchos = new int[numColumnas];

        // Calcular el ancho máximo de cada columna (tomando en cuenta los encabezados y
        // las celdas)
        for (int i = 0; i < numColumnas; i++) {
            // Compara el tamaño del encabezado con las celdas en esa columna
            String header = encabezados.get(i).toString();
            anchos[i] = header.length(); // Inicializa con el largo del encabezado

            // Recorre las filas y actualiza el ancho máximo para cada columna
            for (List<Celda<?>> fila : filas) {
                String valor = fila.get(i).toString();
                anchos[i] = Math.max(anchos[i], valor.length()); // Actualiza el máximo
            }
        }
        return anchos;
    }

    // Función para centrar un texto dentro de un campo de tamaño fijo
    private String centrarTexto(String texto, int ancho) {
        // Calcular los espacios a la izquierda y derecha para centrar el texto
        int espaciosIzq = (ancho - texto.length()) / 2;
        int espaciosDer = ancho - texto.length() - espaciosIzq;

        // Crear la cadena centrada usando concatenación de cadenas
        String resultado = "";

        // Agregar los espacios a la izquierda
        for (int i = 0; i < espaciosIzq; i++) {
            resultado += " ";
        }

        // Agregar el texto centrado
        resultado += texto;

        // Agregar los espacios a la derecha
        for (int i = 0; i < espaciosDer; i++) {
            resultado += " ";
        }

        return resultado;
    }

    // Método para imprimir los encabezados de manera centrada
    private void imprimirEncabezados(List<Etiqueta> etiquetas, int[] anchos) {
        String salida = " | ";

        for (int i = 0; i < etiquetas.size(); i++) {
            // Centrar el texto del encabezado según el ancho de la columna
            String textoCentrado = centrarTexto(etiquetas.get(i).toString(), anchos[i]);
            salida += textoCentrado;
            salida += " | ";
        }

        System.out.println(salida);

        // Imprimir una línea separadora para mayor claridad
        String separador = " | ";
        for (int i = 0; i < etiquetas.size(); i++) {
            for (int j = 0; j < anchos[i]; j++) {
                separador += "-";
            }
            separador += " | ";
        }
        System.out.println(separador);
    }

    // Método para imprimir una fila de celdas de manera centrada
    private void imprimirFila(List<Celda<?>> fila, int[] anchos) {
        String salida = " | ";

        for (int nroColumna = 0; nroColumna < fila.size(); nroColumna++) {
            String valorCelda = fila.get(nroColumna).toString();

            // Centrar el texto de la celda según el ancho de la columna
            String textoCentrado = centrarTexto(valorCelda, anchos[nroColumna]);
            salida += textoCentrado;
            salida += " | ";
        }
        System.out.println(salida);
    }

    // Método para imprimir todas las filas y encabezados
    public void imprimirFilas(List<Etiqueta> etiquetasColumnas, List<List<Celda<?>>> filas) {
        if (!filas.isEmpty()) {
            // Calcular los anchos máximos de cada columna
            int[] anchos = calcularAnchos(filas, etiquetasColumnas);

            // Imprimir los encabezados
            imprimirEncabezados(etiquetasColumnas, anchos);

            // Imprimir las filas de datos, cada una centrada
            for (List<Celda<?>> fila : filas) {
                imprimirFila(fila, anchos);
            }
        }
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

    /*
     * // metodos para agrupamiento
     * 
     * @Override
     * public Tabla agruparPor(List<Etiqueta> etiquetasColumnas) {
     * return OperacionesTabla.agruparPor(this, etiquetasColumnas);
     * }
     */

    @Override
    public Tabla aplicarOperaciones(Map<List<Object>, List<Integer>> grupos, String operacion) {
        return OperacionesTabla.aplicarOperaciones(this, grupos, operacion);
    }

    // Implementar un Método de Ordenación
    public void ordenarPor(List<Etiqueta> etiquetasColumnas, boolean ascendente) {
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < etiquetasFilas.size(); i++) {
            indices.add(i);
        }

        indices.sort((indice1, indice2) -> {
            for (Etiqueta etiqueta : etiquetasColumnas) {
                Columna<?> columna = getColumna(etiqueta);
                Comparable<Object> valor1 = (Comparable<Object>) columna.getCeldas().get(indice1).getValor();
                Comparable<Object> valor2 = (Comparable<Object>) columna.getCeldas().get(indice2).getValor();

                int comparacion = (valor1 == null ? (valor2 == null ? 0 : -1)
                        : (valor2 == null ? 1 : valor1.compareTo(valor2)));
                if (comparacion != 0) {
                    return ascendente ? comparacion : -comparacion;
                }
            }
            return 0;
        });

        // Reordenar las celdas en cada columna según los nuevos índices
        for (Columna<?> columna : columnas) {
            List<Celda<?>> celdasOrdenadas = new ArrayList<>();
            for (int indice : indices) {
                celdasOrdenadas.add(columna.getCeldas().get(indice));
            }

            // Aquí aseguramos el tipo genérico correcto para cada columna
            setCeldasGenerico(columna, celdasOrdenadas);
        }

        // Reordenar etiquetas de filas
        List<Etiqueta> etiquetasFilasOrdenadas = new ArrayList<>();
        for (int indice : indices) {
            etiquetasFilasOrdenadas.add(etiquetasFilas.get(indice));
        }
        etiquetasFilas = etiquetasFilasOrdenadas;
    }

    @SuppressWarnings("unchecked")
    private <T> void setCeldasGenerico(Columna<T> columna, List<Celda<?>> celdasOrdenadas) {
        columna.setCeldas((List<Celda<T>>) (List<?>) celdasOrdenadas);
    }

    // método filtrar en Tabla debe trabajar con la clase Filtro parametrizada
    public <T extends Comparable<T>> Tabla filtrar(List<Filtro<T>> filtros) {
        Tabla resultado = new Tabla();
        // Copiar la estructura de columnas a la tabla resultado
        for (Columna<?> columna : this.columnas) {
            resultado.agregarColumna(columna.getTipoDeDato(), columna.getEtiqueta());
        }

        for (int i = 0; i < getCantidadFilas(); i++) {
            boolean cumpleTodos = true;
            for (Filtro<T> filtro : filtros) {
                Columna<T> columna = (Columna<T>) getColumna(filtro.getColumna());
                Celda<T> celda = columna.getCeldas().get(i);
                if (!filtro.evaluar(celda)) {
                    cumpleTodos = false;
                    break;
                }
            }

            if (cumpleTodos) {
                List<Celda<?>> nuevaFila = new ArrayList<>();
                for (Columna<?> columna : this.columnas) {
                    nuevaFila.add(columna.getCeldas().get(i));
                }
                resultado.agregarFila(nuevaFila);
            }
        }
        return resultado;
    }

    private Etiqueta copiarEtiqueta(EtiquetaCadena etiqueta) {
        Etiqueta copiaEtiqueta = new EtiquetaCadena(etiqueta.getValor());
        return copiaEtiqueta;
    }

    private Etiqueta copiarEtiqueta(EtiquetaNumerica etiqueta) {
        Etiqueta copiaEtiqueta = new EtiquetaNumerica(etiqueta.getValor());
        return copiaEtiqueta;
    }

    // Método para realizar una copia independiente
    public Tabla copiar() {
        Tabla copia = new Tabla();

        // Copiar etiquetas de las columnas
        for (Etiqueta etiqueta : this.etiquetasColumnas) {
            copia.etiquetasColumnas.add(this.copiarEtiqueta(etiqueta)));
            //copia.etiquetasColumnas.add(etiqueta.copiar()); // Asumiendo que Etiqueta tiene un método copiar()
        }

        // Copiar etiquetas de las filas
        for (Etiqueta etiqueta : etiquetasFilas) {
            copia.etiquetasFilas.add(etiqueta.copiar()); // Asumiendo que Etiqueta tiene un método copiar()
        }

        // Copiar columnas
        for (Columna<?> columna : columnas) {
            Columna<?> copiaColumna = new Columna<>(columna.getEtiqueta().copiar(), columna.getTipoDeDato());
            for (Celda<?> celda : columna.getCeldas()) {
                copiaColumna.agregarCelda(new Celda<>(celda.getValor())); // Copiamos cada celda
            }
            copia.columnas.add(copiaColumna);
        }

        return copia;
    }

}