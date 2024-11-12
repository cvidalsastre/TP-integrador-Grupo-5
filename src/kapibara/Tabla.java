package kapibara;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import enums.OperacionEstadistica;

public class Tabla implements Visualizable, Agrupable, Ordenable, Filtrable {

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

    // Agregar columnas a partir de una columna ya creada :
    // SIN ETIQUETA
    public void agregarColumnaYC(Columna<?> nuevaColumna) {
        Etiqueta etiquetaColumna = new EtiquetaNumerica(getCantidadColumnas()); // Etiqueta secuencial automática
        agregarColumnaYC(etiquetaColumna, nuevaColumna);
    }

    // CON ETIQUETA
    public void agregarColumnaYC(Etiqueta etiquetaColumna, Columna<?> nuevaColumna) {
        if (tieneLaEtiqueta(etiquetaColumna, etiquetasColumnas)) {
            throw new IllegalArgumentException("Ya existe una columna con esa etiqueta.");
        }
        if (nuevaColumna.getCantidadCeldas() == this.getCantidadFilas()) {
            columnas.add(nuevaColumna);
            etiquetasColumnas.add(etiquetaColumna);
        } else if (nuevaColumna.getCantidadCeldas() > this.getCantidadFilas()) {
            for (int i = 0; i < nuevaColumna.getCantidadCeldas() - this.getCantidadFilas(); i++) {
                this.agregarFila();
            }
            columnas.add(nuevaColumna);
            etiquetasColumnas.add(etiquetaColumna);
        } else if (nuevaColumna.getCantidadCeldas() < this.getCantidadColumnas()) {
            for (int i = (nuevaColumna.getCantidadCeldas()); i < this.getCantidadColumnas(); i++) {
                nuevaColumna.agregarCelda(new Celda<>(null));
            }
            columnas.add(nuevaColumna);
            etiquetasColumnas.add(etiquetaColumna);
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

    public List<Celda<?>> getFilaAcotadaList(Etiqueta etiquetaFila, int cantColumnas) {
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
            filasRandom.add(getListaCeldas(e));
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
            tablaRebanada.add(getFilaAcotadaList(e, etiquetasColumnasSelec));
        }

        return tablaRebanada;
    }

    private List<Celda<?>> getFilaAcotadaList(Etiqueta etiquetaFila, List<Etiqueta> etiquetasColumnasSel) {

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

    public List<List<Celda<?>>> headLista(int cantidadFilas) {
        if (cantidadFilas < 0) {
            throw new IllegalArgumentException("La cantidad de filas debe ser mayor o igual a cero.");
        }
        List<List<Celda<?>>> primerasFilas = new ArrayList<>();
        List<Etiqueta> etiquetasPrimFilas = etiquetasFilas.subList(0, Math.min(cantidadFilas, etiquetasFilas.size()));
        for (Etiqueta e : etiquetasPrimFilas) {
            primerasFilas.add(getListaCeldas(e));
        }
        return primerasFilas;
    }

    public List<List<Celda<?>>> tailList(int cantidadFilas) {
        if (cantidadFilas < 0) {
            throw new IllegalArgumentException("La cantidad de filas debe ser mayor o igual a cero.");
        }
        List<List<Celda<?>>> ultimasFilas = new ArrayList<>();
        int cantidadEquitasFilas = etiquetasFilas.size();
        List<Etiqueta> etiquetasUltimasFilas = etiquetasFilas
                .subList(cantidadEquitasFilas - Math.min(cantidadFilas, cantidadEquitasFilas), cantidadEquitasFilas);
        for (Etiqueta e : etiquetasUltimasFilas) {
            ultimasFilas.add(getListaCeldas(e));
        }
        return ultimasFilas;
    }

    

    // Obtener una fila completa
    public List<Celda<?>> getListaCeldas(Etiqueta etiquetaFila) {
        int indexFila = getIndex(etiquetaFila, etiquetasFilas);
        List<Celda<?>> fila = new ArrayList<>();
        for (Columna<?> col : columnas) {
            fila.add(col.getCeldas().get(indexFila));
        }
        return fila;
    }

    public List<Celda<?>> getListaCeldas(int indexFila) {
        List<Celda<?>> fila = new ArrayList<>();
        for (Columna<?> col : columnas) {
            fila.add(col.getCeldas().get(indexFila));
        }
        return fila;
    }

    // No sé si sirve
    public Fila getFila(Etiqueta etiquetaFila) {
        int indexFila = getIndex(etiquetaFila, etiquetasFilas);
        List<Celda<?>> celdasFilas = new ArrayList<>();
        for (Columna<?> col : columnas) {
            celdasFilas.add(col.getCeldas().get(indexFila));
        }
        return new Fila(etiquetaFila, celdasFilas, etiquetasColumnas);
    }

    private Fila getFilaAcotada(Etiqueta etiquetaFila, List<Etiqueta> etiquetasColumnasSel) {
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

    public List<Fila> head(int cantidadFilas) {
        if (cantidadFilas < 0) {
            throw new IllegalArgumentException("La cantidad de filas debe ser mayor o igual a cero.");
        }
        List<Fila> primerasFilas = new ArrayList<>();
        List<Etiqueta> etiquetasPrimFilas = etiquetasFilas.subList(0, Math.min(cantidadFilas, etiquetasFilas.size()));
        for (Etiqueta e : etiquetasPrimFilas) {
            Fila nueva_fila = new Fila(e, getFila(e).getCeldasFila(), etiquetasColumnas);
            primerasFilas.add(nueva_fila);
        }
        return primerasFilas;
    }

    public List<Fila> tail(int cantidadFilas) {
        if (cantidadFilas < 0) {
            throw new IllegalArgumentException("La cantidad de filas debe ser mayor o igual a cero.");
        }
        List<Fila> ultimasFilas = new ArrayList<>();
        int cantidadEquitasFilas = etiquetasFilas.size();
        List<Etiqueta> etiquetasUltimasFilas = etiquetasFilas
                .subList(cantidadEquitasFilas - Math.min(cantidadFilas, cantidadEquitasFilas), cantidadEquitasFilas);
        for (Etiqueta e : etiquetasUltimasFilas) {
            Fila nueva_fila = new Fila(e, getFila(e).getCeldasFila(), etiquetasColumnas);
            ultimasFilas.add(nueva_fila);
        }
        return ultimasFilas;
    }

    public List<Fila> muestreo2(float porcentaje) {
        if (porcentaje < 0.0 || porcentaje > 100.0) {
            throw new IllegalArgumentException("El porcentaje va de 0 a 100.");
        }
        int tamanioMuesta = (int) ((porcentaje / 100) * getCantidadFilas());
        return elegirNFilas2(tamanioMuesta, etiquetasFilas);
    }

    private List<Fila> elegirNFilas2(int tamanio, List<Etiqueta> etiquetasFilas) {
        List<Fila> filasRandom = new ArrayList<>();
        // se copia la lista de etiqueta de las filas
        List<Etiqueta> copiaEtiquetasFilas = new ArrayList<>(etiquetasFilas);

        // mezcla
        Collections.shuffle(copiaEtiquetasFilas);

        copiaEtiquetasFilas = copiaEtiquetasFilas.subList(0, Math.min(tamanio, copiaEtiquetasFilas.size()));

        for (Etiqueta e : copiaEtiquetasFilas) {
            Fila nuevaFila = new Fila(e, getFila(e).getCeldasFila(), etiquetasColumnas);
            filasRandom.add(nuevaFila);
        }
        return filasRandom;
    }

    public List<Fila> seleccionParcial2(List<Etiqueta> seleccionEtiquetasFilas,
            List<Etiqueta> seleccionEtiquetasColumnas) {

        if (tieneRepetidos(seleccionEtiquetasColumnas) || tieneRepetidos(seleccionEtiquetasFilas)) {
            throw new IllegalArgumentException("Las listas de etiquetas no pueden tener elementos repetidos.");
        }

        // se chequea que no haya equipo que no son las de la tabla
        if (!estanTodasLasEtiquetas(seleccionEtiquetasFilas, etiquetasFilas) ||
                !estanTodasLasEtiquetas(seleccionEtiquetasColumnas, etiquetasColumnas)) {
            throw new IllegalArgumentException("Alguna/s de la/s etiqueta/s seleccionada/s no pertenece/n a la tabla.");
        }

        List<Fila> tablaRebanada = new ArrayList<>();
        // Esto garantiza que las etiquetas tengan el mismo orden que en la tabla
        List<Etiqueta> etiquetasColumnasSelec = conservarLasQueComparten(seleccionEtiquetasColumnas, etiquetasColumnas);
        List<Etiqueta> etiquetasFilasSelec = conservarLasQueComparten(seleccionEtiquetasFilas, etiquetasFilas);
        for (Etiqueta e : etiquetasFilasSelec) {
            System.out.println("pppp" + etiquetasColumnasSelec);

            tablaRebanada.add(getFilaAcotada(e, etiquetasColumnasSelec));
        }
        return tablaRebanada;
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
    public void imprimirFilas(List<Fila> filas) {
        if (!filas.isEmpty()) {
            // Imprimir los encabezados
            imprimirEncabezados2(filas.get(0).getEtiquetasColumnas());

            // Imprimir las filas de datos, cada una centrada
            for (Fila fila : filas) {
                System.out.println(fila);
            }
        }
    }

    private String filaACadena(Fila fila, int maxColumnas, int maxLargoCadena) {
        String salida = "*" + fila.getEtiquetaFila().toString() + "*" + " | ";
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
        imprimirEncabezados2(etiquetasColumnas.subList(0, maxColumnas));

        // Imprimir las filas de datos
        for (Etiqueta e : etiquetasFilas.subList(0, maxFilas)) {
            System.out.println(filaACadena(getFila(e), maxColumnas, maxLargoCadena));
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
                return (Columna<T>) col; //
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

    // Implementar un Método de Ordenación
    @Override
    public <T extends Comparable<T>> Tabla ordenarPor(Etiqueta etiquetaColumna, boolean ascendente) {
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < etiquetasFilas.size(); i++) {
            indices.add(i);
        }

        // Obtener la columna a ordenar
        Columna<T> columna = getColumna(etiquetaColumna);

        // Ordenar los índices basados en los valores de la columna
        indices.sort((indice1, indice2) -> {
            T valor1 = columna.getCeldas().get(indice1).getValor();
            T valor2 = columna.getCeldas().get(indice2).getValor();

            int comparacion = (valor1 == null ? (valor2 == null ? 0 : -1)
                    : (valor2 == null ? 1 : valor1.compareTo(valor2)));
            return ascendente ? comparacion : -comparacion;
        });

        // Crear una nueva tabla para almacenar los resultados ordenados
        Tabla tablaOrdenada = new Tabla();

        // Copiar las columnas a la nueva tabla
        for (Columna<?> col : columnas) {
            tablaOrdenada.agregarColumna(col.getTipoDeDato(), col.getEtiqueta());
        }

        // Reordenar las celdas en cada columna según los nuevos índices y agregarlas a
        // la nueva tabla
        for (Columna<?> col : columnas) {
            reordenarCeldas(tablaOrdenada, col, indices);
        }

        // Reordenar etiquetas de filas y agregarlas a la nueva tabla
        List<Etiqueta> etiquetasFilasOrdenadas = new ArrayList<>();
        for (int indice : indices) {
            etiquetasFilasOrdenadas.add(etiquetasFilas.get(indice));
        }
        tablaOrdenada.etiquetasFilas = etiquetasFilasOrdenadas;

        return tablaOrdenada;
    }

    // Método para reordenar las celdas de una columna según los nuevos índices y
    // agregarlas a la nueva tabla
    private <T> void reordenarCeldas(Tabla tablaOrdenada, Columna<T> columnaOriginal, List<Integer> indices) {
        List<Celda<T>> celdasOrdenadas = new ArrayList<>();
        for (int indice : indices) {
            celdasOrdenadas.add(columnaOriginal.getCeldas().get(indice));
        }
        Columna<T> columnaOrdenada = tablaOrdenada.getColumna(columnaOriginal.getEtiqueta());
        columnaOrdenada.setCeldas(celdasOrdenadas);
    }
     @Override
    public <T extends Comparable<T>> Tabla filtrar(Etiqueta etiquetaColumna, T valorReferencia, String operador) {
        Tabla resultado = new Tabla();
        // Copiar la estructura de columnas a la tabla resultado
        for (Columna<?> columna : this.getColumnas()) {
            resultado.agregarColumna(columna.getTipoDeDato(), columna.getEtiqueta());
        }

        Columna<T> columna = getColumna(etiquetaColumna);
        if (columna == null) {
            throw new IllegalArgumentException("Columna no encontrada: " + etiquetaColumna);
        }

        for (int i = 0; i < getCantidadFilas(); i++) {
            Celda<T> celda = columna.getCeldas().get(i);
            if (evaluarCelda(celda, valorReferencia, operador)) {
                List<Celda<?>> nuevaFila = new ArrayList<>();
                for (Columna<?> col : this.getColumnas()) {
                    nuevaFila.add(col.getCeldas().get(i));
                }
                resultado.agregarFila(nuevaFila);
            }
        }
        return resultado;
    }

    private <T extends Comparable<T>> boolean evaluarCelda(Celda<T> celda, T valorReferencia, String operador) {
        if (celda.esNA()) {
            return false;
        }

        T valorCelda = celda.getValor(); // Obtener el valor de la celda
        if (celda.getTipo().equals(String.class)) {
            String valorCeldaStr = (String) valorCelda;
            String valorReferenciaStr = valorReferencia.toString();
            return valorCeldaStr.equals(valorReferenciaStr);
        }

        if (valorCelda instanceof Number && valorReferencia instanceof Number) {
            Double valorCeldaDouble = ((Number) valorCelda).doubleValue();
            Double valorReferenciaDouble = ((Number) valorReferencia).doubleValue();
            int comparacion = valorCeldaDouble.compareTo(valorReferenciaDouble);
            switch (operador) {
                case "<":
                    return comparacion < 0;
                case ">":
                    return comparacion > 0;
                case "=":
                    return comparacion == 0;
                default:
                    throw new IllegalArgumentException("Operador no soportado: " + operador);
            }
        }

        int comparacion = valorCelda.compareTo(valorReferencia);
        switch (operador) {
            case "<":
                return comparacion < 0;
            case ">":
                return comparacion > 0;
            case "=":
                return comparacion == 0;
            default:
                throw new IllegalArgumentException("Operador no soportado: " + operador);
        }
    }

    /////////////////// ROCIO *********************************
    public Tabla copiarTabla() {
        // Crear una nueva instancia de Tabla
        Tabla copia = new Tabla();

        // Copiar las etiquetas de filas
        List<Etiqueta> nuevasEtiquetasFilas = new ArrayList<>();
        for (Etiqueta etiqueta : this.etiquetasFilas) {
            nuevasEtiquetasFilas.add(copiarEtiqueta(etiqueta)); // Usamos el método copiarEtiqueta
        }
        copia.etiquetasFilas = nuevasEtiquetasFilas;

        // Copiar las columnas
        for (Columna<?> columnaOriginal : this.columnas) {
            // Crear una nueva columna con el mismo tipo y etiqueta
            Columna<?> nuevaColumna = new Columna<>(copiarEtiqueta(columnaOriginal.getEtiqueta()),
                    columnaOriginal.getTipoDeDato());

            // Copiar las celdas de la columna original a la nueva columna
            List<? extends Celda<?>> celdasOriginales = columnaOriginal.getCeldas(); // Captura celdas con cualquier
                                                                                     // tipo
            // List<Celda<?>> nuevasCeldas = new ArrayList<>();

            for (Celda<?> celdaOriginal : celdasOriginales) {
                // Crear una nueva celda con el mismo valor
                // nuevasCeldas.add(copiarCelda(celdaOriginal));
                Celda<?> nuevaCelda = new Celda<>(celdaOriginal.getValor());
                // nuevasCeldas.add(nuevaCelda);
                nuevaColumna.agregarCelda(nuevaCelda);
            }

            // Agregar la nueva columna a la tabla copiada
            copia.columnas.add(nuevaColumna);
        }

        // Copiar las etiquetas de las columnas
        List<Etiqueta> nuevasEtiquetasColumnas = new ArrayList<>();
        for (Etiqueta etiqueta : this.etiquetasColumnas) {
            nuevasEtiquetasColumnas.add(copiarEtiqueta(etiqueta)); // Usamos el método copiarEtiqueta
        }
        copia.etiquetasColumnas = nuevasEtiquetasColumnas;

        return copia;
    }

    // Método auxiliar para copiar una etiqueta
    private Etiqueta copiarEtiqueta(Etiqueta etiqueta) {
        if (etiqueta instanceof EtiquetaNumerica) {
            // Copiar una etiqueta numérica
            EtiquetaNumerica etiquetaNumerica = (EtiquetaNumerica) etiqueta;
            return new EtiquetaNumerica(etiquetaNumerica.getValor());
        } else if (etiqueta instanceof EtiquetaCadena) {
            // Copiar una etiqueta de cadena
            EtiquetaCadena etiquetaCadena = (EtiquetaCadena) etiqueta;
            return new EtiquetaCadena(etiquetaCadena.getValor());
        } else {
            throw new IllegalArgumentException("Tipo de etiqueta no soportado");
        }
    }

    public static Tabla leerDesdeCsv(String path) {
        return Tabla.leerDesdeCsv(path, true, ",");

    }

    public static Tabla leerDesdeCsv(String path, boolean includeHeaders, String dataSeparator) {
        long startTime = System.nanoTime();
        CsvReader reader = new CsvReader();
        List<List<String>> listaDeColumnasCsv = reader.leerCSV(path, includeHeaders, dataSeparator);
        List<Class<?>> tiposDeColumnas = reader.identificarTipos(listaDeColumnasCsv, includeHeaders);

        Tabla tabla = new Tabla();

        // Agregar columnas a la tabla
        for (int j = 0; j < listaDeColumnasCsv.size(); j++) {
            Class<?> tipo = tiposDeColumnas.get(j);
            Etiqueta etiquetaColumna = new EtiquetaCadena(listaDeColumnasCsv.get(j).get(0)); // Usar encabezado como
                                                                                             // etiqueta
            tabla.agregarColumna(tipo, etiquetaColumna);
        }

        // Agregar filas a la tabla, omitiendo la fila de encabezado si está presente
        int startRow = includeHeaders ? 1 : 0;
        for (int i = startRow; i < listaDeColumnasCsv.get(0).size(); i++) { // Iterar sobre filas
            List<Celda<?>> celdas = new ArrayList<>();
            for (int j = 0; j < listaDeColumnasCsv.size(); j++) { // Iterar sobre columnas
                String elemento = listaDeColumnasCsv.get(j).get(i);
                Class<?> tipo = tiposDeColumnas.get(j);

                try {
                    if (tipo == Boolean.class) {
                        Boolean valor = Boolean.parseBoolean(elemento);
                        celdas.add(new Celda<>(valor));
                    } else if (tipo == Double.class) {
                        Double valor = Double.parseDouble(elemento);
                        celdas.add(new Celda<>(valor));
                    } else if (tipo == Integer.class) {
                        Integer valor = Integer.parseInt(elemento);
                        celdas.add(new Celda<>(valor));
                    } else {
                        celdas.add(new Celda<>(elemento));
                    }
                } catch (NumberFormatException e) {
                    celdas.add(new Celda<>(null)); // Agregar null si falla la conversión
                }
            }
            tabla.agregarFila(celdas);
        }
        // Capturar el tiempo después de la ejecución
        long endTime = System.nanoTime();

        // Calcular la diferencia en segundos
        double duration = (endTime - startTime) / 1_000_000_000.0;

        // Imprimir el tiempo en el formato deseado
        System.out.printf("Tiempo de ejecución: %.3f segundos %n", duration);
        System.out.println("Se creo una tabla a partir de un csv de " + listaDeColumnasCsv.get(0).size() + "filas");
        return tabla;
    }

    public void guardarTabla(String rutaArchivo) {
        try {
            // Crear el directorio si no existe
            Files.createDirectories(Paths.get(rutaArchivo).getParent());

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
                // Escribir encabezados
                for (int i = 0; i < etiquetasColumnas.size(); i++) {
                    writer.write(etiquetasColumnas.get(i).getValor().toString());
                    if (i < etiquetasColumnas.size() - 1) {
                        writer.write(",");
                    }
                }
                writer.newLine();

                // Escribir filas
                for (int i = 0; i < getCantidadFilas(); i++) {
                    for (int j = 0; j < columnas.size(); j++) {
                        Celda<?> celda = columnas.get(j).getCeldas().get(i);
                        writer.write(celda.getValor() != null ? celda.getValor().toString() : "");
                        if (j < columnas.size() - 1) {
                            writer.write(",");
                        }
                    }
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Tabla agruparYSumarizar(List<Etiqueta> etiquetasColumnasAgrupamiento, OperacionEstadistica operacion) {
        // Crear una nueva tabla para almacenar los resultados
        Tabla tablaResultado = new Tabla();

        // Obtener las columnas que no son parte del agrupamiento y son numéricas
        List<Columna<?>> columnasNumericas = columnas.stream()
                .filter(col -> !etiquetasColumnasAgrupamiento.contains(col.getEtiqueta())
                        && Number.class.isAssignableFrom(col.getTipoDeDato()))
                .collect(Collectors.toList());

        // Crear un mapa para almacenar los grupos y sus filas
        Map<String, List<List<Celda<?>>>> grupos = new HashMap<>();

        // Agrupar las filas
        for (int i = 0; i < getCantidadFilas(); i++) {
            List<Celda<?>> fila = getListaCeldas(i);
            String claveGrupo = etiquetasColumnasAgrupamiento.stream()
                    .map(etiqueta -> fila.get(getIndex(etiqueta, etiquetasColumnas)).toString())
                    .collect(Collectors.joining(","));
            grupos.computeIfAbsent(claveGrupo, k -> new ArrayList<>()).add(fila);
        }

        // Agregar las columnas numéricas a la nueva tabla
        for (Columna<?> columna : columnasNumericas) {
            tablaResultado.agregarColumna(Number.class, columna.getEtiqueta());
        }

        // Aplicar la operación de sumarización a cada grupo
        for (Map.Entry<String, List<List<Celda<?>>>> entry : grupos.entrySet()) {
            String claveGrupo = entry.getKey();
            List<List<Celda<?>>> filasGrupo = entry.getValue();

            // Crear una nueva fila para almacenar los resultados del grupo
            List<Celda<?>> nuevaFila = new ArrayList<>();
            for (Columna<?> columna : columnasNumericas) {
                List<Number> valores = filasGrupo.stream()
                        .map(fila -> (Number) fila.get(getIndex(columna.getEtiqueta(), etiquetasColumnas)).getValor())
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList());

                Number resultado = aplicarOperacion(valores, operacion);
                nuevaFila.add(new Celda<>(resultado));
            }

            // Agregar la fila de resultados a la nueva tabla
            tablaResultado.agregarFila(nuevaFila, new EtiquetaCadena(claveGrupo));
        }

        return tablaResultado;
    }

    private Number aplicarOperacion(List<Number> valores, OperacionEstadistica operacion) {
        double media = valores.stream().mapToDouble(Number::doubleValue).average().orElse(Double.NaN);
        switch (operacion) {
            case SUMA:
                return valores.stream().mapToDouble(Number::doubleValue).sum();
            case MAXIMO:
                return valores.stream().mapToDouble(Number::doubleValue).max().orElse(Double.NaN);
            case MINIMO:
                return valores.stream().mapToDouble(Number::doubleValue).min().orElse(Double.NaN);
            case CUENTA:
                return valores.size();
            case MEDIA:
                return valores.stream().mapToDouble(Number::doubleValue).average().orElse(Double.NaN);
            case VARIANZA:
                return valores.stream().mapToDouble(Number::doubleValue).map(v -> Math.pow(v - media, 2)).average()
                        .orElse(Double.NaN);
            case DESVIO_ESTANDAR:
                double varianza = valores.stream().mapToDouble(Number::doubleValue).map(v -> Math.pow(v - media, 2))
                        .average().orElse(Double.NaN);
                return Math.sqrt(varianza);
            default:
                throw new IllegalArgumentException("Operación no soportada: " + operacion);
        }
    }

    public static Tabla concatenar(Tabla tabla1, Tabla tabla2) {
        // Verificar compatibilidad de las tablas
        if (tabla1.getCantidadColumnas() != tabla2.getCantidadColumnas()) {
            throw new IllegalArgumentException("Las tablas no tienen el mismo número de columnas.");
        }
        for (int i = 0; i < tabla1.getCantidadColumnas(); i++) {
            Columna<?> columna1 = tabla1.getColumna(tabla1.getEtiquetasColumnas().get(i));
            Columna<?> columna2 = tabla2.getColumna(tabla2.getEtiquetasColumnas().get(i));
            if (!columna1.getEtiqueta().getValor().equals(columna2.getEtiqueta().getValor()) ||
                    !columna1.getTipoDeDato().equals(columna2.getTipoDeDato())) {
                throw new IllegalArgumentException("Las columnas no coinciden en etiquetas o tipos de datos.");
            }
        }
        // Crear una nueva tabla para el resultado
        Tabla resultado = new Tabla();
        // Copiar las columnas
        for (Columna<?> columna : tabla1.getColumnas()) {
            resultado.agregarColumna(columna.getTipoDeDato(), columna.getEtiqueta());
        }
        // Copiar las filas de la primera tabla
        for (int i = 0; i < tabla1.getCantidadFilas(); i++) {
            List<Celda<?>> fila = tabla1.getListaCeldas(tabla1.getEtiquetasFilas().get(i));
            resultado.agregarFila(fila);
        }
        // Copiar las filas de la segunda tabla
        for (int i = 0; i < tabla2.getCantidadFilas(); i++) {
            List<Celda<?>> fila = tabla2.getListaCeldas(tabla2.getEtiquetasFilas().get(i));
            resultado.agregarFila(fila);
        }
        return resultado;
    }

}