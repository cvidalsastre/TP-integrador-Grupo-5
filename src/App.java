import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {

    public static void main(String[] args) {
        // Ejecutar cada prueba de funcionalidad
        probarInformacionBasica();
        probarAccesoIndexado();
        probarFormatosCargaDescarga();
        probarVisualizacion();
        probarGeneracionYModificacion();
        probarSeleccionYFiltrado();
        probarOperacionesAvanzadas();
    }

    private static void probarInformacionBasica() {
        // Crear una tabla con 3 filas y 3 columnas
        TablaImpl tabla = new TablaImpl(3, 3);

        // Imprimir cantidad de filas y columnas
        System.out.println("INFORMACION BASICA:");
        System.out.println("Cantidad de filas: " + tabla.getCantidadFilas());
        System.out.println("Cantidad de columnas: " + tabla.getCantidadColumnas());

        // Ingesta de datos
        tabla.setDato(0, 0, "dato1");
        tabla.setDato(1, 1, 42);
        tabla.setDato(2, 2, 3.14);

        // Imprimir datos
        System.out.println("Dato en (0,0): " + tabla.getDato(0, 0));
        System.out.println("Dato en (1,1): " + tabla.getDato(1, 1));

        // Imprimir etiquetas
        System.out.println("Etiquetas de filas: " + tabla.getEtiquetasFilas());
        System.out.println("Etiquetas de columnas: " + tabla.getEtiquetasColumnas());

        // Tipo de dato de la columna
        System.out.println("Tipo de datos de columna 1: " + tabla.getTipoColumna(1));
    }

    private static void probarAccesoIndexado() {
        // Crear una tabla con 3 filas y 3 columnas
        TablaImpl tabla = new TablaImpl(3, 3);

        // Ingesta de datos
        tabla.setDato(0, 0, "dato1");
        tabla.setDato(1, 1, 42);
        tabla.setDato(2, 2, 3.14);

        // Imprimir acceso indexado
        System.out.println("\nACCESO INDEXADO:");
        System.out.println("Celda en (0,0): " + tabla.obtenerCelda(0, 0));
        System.out.println("Celda en (1,1): " + tabla.obtenerCelda(1, 1));
        System.out.println("Celda en (2,2): " + tabla.obtenerCelda(2, 2));

        // Imprimir fila y columna completa
        System.out.println("Fila 1: " + tabla.obtenerFila(1));
        System.out.println("Columna 2: " + tabla.obtenerColumna(2));
    }

    private static void probarFormatosCargaDescarga() {
        // Crear una tabla con 3 filas y 3 columnas
        TablaImpl tabla = new TablaImpl(3, 3);

        // Instanciar el gestor de CSV
        GestorCSV gestorCSV = new GestorCSV();

        // Cargar datos desde archivo CSV
        try {
            System.out.println("\nCARGA Y DESCARGA:");
            System.out.println("Cargando datos desde archivo CSV camion.csv");
            gestorCSV.cargarDesdeCSV(tabla,
                    "C:/Users/dbaron_lightech/Documents/algoritmos/practica/tp/data/camion.csv",
                    ",", true);

            // Imprimir los datos cargados
            for (int i = 0; i < tabla.getCantidadFilas(); i++) {
                System.out.println("Fila " + i + ": " + tabla.obtenerFila(i));
            }
        } catch (IOException e) {
            System.out.println("Error al cargar archivo CSV: " + e.getMessage());
        }

        // Guardar en archivo CSV
        try {
            System.out.println("Guardando datos en archivo CSV camion1.csv");
            gestorCSV.guardarComoCSV(tabla,
                    "C:/Users/dbaron_lightech/Documents/algoritmos/practica/tp/data/camion1.csv",
                    ",", true);
        } catch (IOException e) {
            System.out.println("Error al guardar archivo CSV: " + e.getMessage());
        }
    }

    private static void probarVisualizacion() {
        // Crear una tabla con 3 filas y 3 columnas
        TablaImpl tabla1 = new TablaImpl(3, 3);

        // Instanciar el visualizador con la tabla
        Visualizador visualizador = new Visualizador(tabla1);

        // Ejemplo de carga de datos
        tabla1.setDato(0, 0, "primero");
        tabla1.setDato(1, 1, 2);
        tabla1.setDato(2, 2, 1.24);

        // Mostrar los datos con visualización
        System.out.println("\nVISUALIZACION:");
        visualizador.visualizar(3, 3, 10); // Muestra hasta 3 filas, 3 columnas, con hasta 10 caracteres por celda
    }

    private static void probarGeneracionYModificacion() {
        // Crear una tabla con 3 filas y 3 columnas
        ModificacionTablaImpl tabla = new ModificacionTablaImpl(3, 3);

        // Agregar datos a la tabla
        tabla.setDato(0, 0, "dato1");
        tabla.setDato(1, 1, 42);
        tabla.setDato(2, 2, 3.14);

        // Imprimir datos antes de modificaciones
        System.out.println("\nGENERACION Y MODIFICACION:");
        System.out.println("Datos iniciales:");
        for (int i = 0; i < tabla.getCantidadFilas(); i++) {
            System.out.println("Fila " + i + ": " + tabla.obtenerFila(i));
        }

        // Crear una copia profunda
        Tabla copia = tabla.copiaProfunda();
        System.out.println("\nCopia profunda:");
        for (int i = 0; i < copia.getCantidadFilas(); i++) {
            System.out.println("Fila " + i + ": " + copia.obtenerFila(i));
        }

        // Agregar una columna desde secuencia
        List<Object> nuevaColumna = new ArrayList<>(Arrays.asList("A", "B", "C"));
        tabla.agregarColumnaDesdeSecuencia(nuevaColumna);
        System.out.println("\nDespués de agregar una columna:");
        for (int i = 0; i < tabla.getCantidadFilas(); i++) {
            System.out.println("Fila " + i + ": " + tabla.obtenerFila(i));
        }

        // Eliminar una columna
        tabla.eliminarColumna(2);
        System.out.println("\nDespués de eliminar la columna 2:");
        for (int i = 0; i < tabla.getCantidadFilas(); i++) {
            System.out.println("Fila " + i + ": " + tabla.obtenerFila(i));
        }

        // Eliminar una fila
        tabla.eliminarFila(1);
        System.out.println("\nDespués de eliminar la fila 1:");
        for (int i = 0; i < tabla.getCantidadFilas(); i++) {
            System.out.println("Fila " + i + ": " + tabla.obtenerFila(i));
        }
    }

    private static void probarSeleccionYFiltrado() {
        // Crear una tabla con 3 filas y 3 columnas
        TablaImpl tabla = new TablaImpl(3, 3);

        // Establecer etiquetas de columna para facilitar la selección y filtrado
        tabla.setEtiquetaColumna(0, "Columna1");
        tabla.setEtiquetaColumna(1, "Columna2");
        tabla.setEtiquetaColumna(2, "Columna3");

        // Añadir datos a la tabla
        tabla.setDato(0, 0, "dato1");
        tabla.setDato(0, 1, "dato2");
        tabla.setDato(0, 2, "dato3");
        tabla.setDato(1, 0, "dato4");
        tabla.setDato(1, 1, "dato5");
        tabla.setDato(1, 2, "dato6");
        tabla.setDato(2, 0, "dato7");
        tabla.setDato(2, 1, "dato8");
        tabla.setDato(2, 2, "dato9");

        // Imprimir tabla original
        System.out.println("Tabla original:");
        for (int i = 0; i < tabla.getCantidadFilas(); i++) {
            System.out.println("Fila " + i + ": " + tabla.obtenerFila(i));
        }

        // Probar selección de filas y columnas
        List<Integer> filasSeleccionadas = Arrays.asList(0, 2);
        List<Integer> columnasSeleccionadas = Arrays.asList(1, 2);
        Tabla seleccion = tabla.seleccionar(filasSeleccionadas, columnasSeleccionadas);
        System.out.println("\nTabla seleccionada:");
        for (int i = 0; i < seleccion.getCantidadFilas(); i++) {
            System.out.println("Fila " + i + ": " + seleccion.obtenerFila(i));
        }

        // Probar filtrado
        Tabla filtrada = tabla.filtrar("Columna2", "dato8");
        System.out.println("\nTabla filtrada:");
        for (int i = 0; i < filtrada.getCantidadFilas(); i++) {
            System.out.println("Fila " + i + ": " + filtrada.obtenerFila(i));
        }
    }

    /*
     * Explicación de los Cambios
     * Inicialización de Datos: Se configura una tabla con datos de prueba, y se
     * utiliza un valor faltante ("NA") para probar la imputación.
     * Ordenamiento: Se ordena la tabla por la primera columna en orden ascendente.
     * Imputación: Se introduce un valor faltante en la tabla para demostrar la
     * imputación de valores con 0.
     * Muestreo: Se crea una muestra aleatoria del 50% de las filas y se imprime.
     * Este método debería ayudarte a verificar que las funcionalidades avanzadas
     * estén funcionando correctamente en tu clase TablaAvanzada
     */
    public static void probarOperacionesAvanzadas() {
        // Crear una instancia de TablaAvanzada con 3 filas y 3 columnas
        TablaAvanzada tabla = new TablaAvanzada(3, 3);

        // Configurar datos de prueba
        tabla.setDato(0, 0, 5);
        tabla.setDato(0, 1, "A");
        tabla.setDato(0, 2, 3.2);
        tabla.setDato(1, 0, 3);
        tabla.setDato(1, 1, "B");
        tabla.setDato(1, 2, 1.4);
        tabla.setDato(2, 0, 7);
        tabla.setDato(2, 1, "C");
        tabla.setDato(2, 2, 2.5);

        // Imprimir la tabla original
        System.out.println("Tabla Original:");
        imprimirTabla(tabla);

        // Ordenar por la columna 0 en orden ascendente
        tabla.ordenarPorColumna(0, true);
        System.out.println("\nTabla Ordenada por Columna 0 (Ascendente):");
        imprimirTabla(tabla);

        // Imputar valores faltantes con un valor específico
        tabla.setDato(0, 0, "NA"); // Introducir un valor faltante para probar la imputación
        tabla.imputar(0); // Imputar valores faltantes con 0
        System.out.println("\nTabla Después de Imputar Valores Faltantes con 0:");
        imprimirTabla(tabla);

        // Crear una muestra aleatoria de 50% de las filas
        TablaImpl muestra = tabla.muestreo(50.0);
        System.out.println("\nMuestra Aleatoria (50% de las filas):");
        imprimirTabla(muestra);
    }

    /**
     * Método para imprimir los datos de una tabla de manera legible.
     *
     * @param tabla La tabla cuyos datos se imprimirán.
     */
    private static void imprimirTabla(Tabla tabla) {
        for (int i = 0; i < tabla.getCantidadFilas(); i++) {
            List<Object> fila = tabla.obtenerFila(i);
            System.out.println("Fila " + i + ": " + fila);
        }
    }

}