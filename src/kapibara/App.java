package kapibara;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {

    public static void main(String[] args) {
        ejecutarRF1(); // Ejecuta RF 1.1 al RF 1.3
        ejecutarRF14(); // Ejecuta RF 1.4
        ejecutarRF21(); // Ejecuta RF 2.1 y RF 2.2
        ejecutarRF23(); // Ejecuta RF 2.3 y RF 2.4
        ejecutarRF25(); // Ejecuta RF 2.5 y RF 2.6
        ejecutarRF27(); // Ejecuta RF 2.7 y RF 2.8
        ejecutarRF291011(); // Ejecuta RF 2.9, RF 2.10 y RF 2.11
     
        ejecutarRF213(); // Requerimiento 2.13
        ejecutarRF214(); // Requerimiento 2.14
        ejecutarRF215(); // Requerimiento 2.15
        ejecutarRF216(); // Requerimiento 2.16
        ejecutarRF217(); // Requerimiento 2.17
        ejecutarRF31(); // Probar nuevos tipos de datos
        ejecutarRF32(); // Cambiar formato de visualización
    }

    // RF 1.1 al RF 1.3: Crear una tabla básica, implementar TAD para fila y columna
    private static void ejecutarRF1() {
        System.out.println("Requerimientos RF 1.1 al RF 1.3: Crear una tabla básica, TAD para fila y columna");

        Tabla tabla = new Tabla();
        System.out.println("Requerimiento RF 1.1: Crear una tabla básica con filas y columnas");
        tabla.agregarColumna(Integer.class, new EtiquetaCadena("ID"));
        tabla.agregarColumna(String.class, new EtiquetaCadena("Nombre"));
        tabla.agregarColumna(Integer.class, new EtiquetaCadena("Edad"));

        tabla.agregarFila(Arrays.asList(new Celda<>(1), new Celda<>("Juan"), new Celda<>(25)));
        tabla.agregarFila(Arrays.asList(new Celda<>(2), new Celda<>("María"), new Celda<>(30)));
        tabla.agregarFila(Arrays.asList(new Celda<>(3), new Celda<>("Pedro"), new Celda<>(22)));

        tabla.visualizar(10, 10, 20);

        // RF 1.2: TAD para una fila
        System.out.println("\nRequerimiento RF 1.2: Implementar TAD para una fila");
        Etiqueta etiquetaFila = new EtiquetaNumerica(0);
        Fila fila = new Fila(etiquetaFila, tabla.getFila(etiquetaFila), tabla.getEtiquetasColumnas());
        System.out.println("Fila creada: " + fila);

        // RF 1.3: TAD para una columna
        System.out.println("\nRequerimiento RF 1.3: Implementar TAD para una columna");
        Etiqueta etiquetaColumna = new EtiquetaCadena("Nombre");
        Columna<?> columna = tabla.getColumna(etiquetaColumna);
        System.out.println("Columna creada: " + columna.getEtiqueta().toString());
        columna.getCeldas().forEach(celda -> System.out.println(celda.getValor()));
    }

    // RF 1.4: Implementar etiquetas para indexar filas y columnas

    private static void ejecutarRF14() {

        System.out.println("Requerimiento RF 1.4: Implementar etiquetas para indexar filas y columnas");

        Tabla tabla1 = new Tabla();
        tabla1.agregarColumna(Integer.class, new EtiquetaCadena("ID"));
        tabla1.agregarColumna(String.class, new EtiquetaCadena("Nombre"));
        tabla1.agregarColumna(Integer.class, new EtiquetaCadena("Edad"));

        tabla1.agregarFila(Arrays.asList(new Celda<>(1), new Celda<>("Juan"), new Celda<>(25)),
                new EtiquetaNumerica(0));
        tabla1.agregarFila(Arrays.asList(new Celda<>(2), new Celda<>("María"), new Celda<>(30)),
                new EtiquetaNumerica(1));
        tabla1.agregarFila(Arrays.asList(new Celda<>(3), new Celda<>("Pedro"), new Celda<>(22)),
                new EtiquetaNumerica(2));

        System.out.println("Tabla creada con etiquetas:");
        tabla1.visualizar(10, 10, 20);
    }

    // RF 2.1 y RF 2.2: Ingresar y modificar celdas null o NA

    private static void ejecutarRF21() {
        System.out.println("Requerimientos RF 2.1 y RF 2.2: Ingresar y modificar celdas null o NA");

        Tabla tabla2 = new Tabla();
        tabla2.agregarColumna(Integer.class, new EtiquetaCadena("ID"));
        tabla2.agregarColumna(String.class, new EtiquetaCadena("Nombre"));
        tabla2.agregarColumna(Integer.class, new EtiquetaCadena("Edad"));

        tabla2.agregarFila(Arrays.asList(new Celda<>(4), new Celda<>("Ana"), new Celda<>(null)),
                new EtiquetaNumerica(3));
        tabla2.agregarFila(Arrays.asList(new Celda<>(5), new Celda<>(null), new Celda<>(28)), new EtiquetaNumerica(4));

        System.out.println("Tabla después de agregar celdas con null o NA:");
        tabla2.visualizar(10, 10, 20);

        tabla2.editarCelda(new EtiquetaNumerica(3), new EtiquetaCadena("Edad"), 29); // Reemplazar null por 29
        tabla2.editarCelda(new EtiquetaNumerica(4), new EtiquetaCadena("Nombre"), "Carlos"); // Reemplazar null por
                                                                                             // Carlos

        System.out.println("Tabla después de modificar las celdas null:");
        tabla2.visualizar(10, 10, 20);
    }

    // RF 2.3 y RF 2.4: Cambiar datos de una celda, agregar y eliminar
    // filas/columnas
    private static void ejecutarRF23() {
        System.out.println(
                "Requerimientos RF 2.3 y RF 2.4: Cambiar datos de una celda, agregar y eliminar filas/columnas");

        Tabla tabla3 = new Tabla();
        tabla3.agregarColumna(Integer.class, new EtiquetaCadena("ID"));
        tabla3.agregarColumna(String.class, new EtiquetaCadena("Producto"));
        tabla3.agregarColumna(Double.class, new EtiquetaCadena("Precio"));

        tabla3.agregarFila(Arrays.asList(new Celda<>(1), new Celda<>("Manzana"), new Celda<>(10.5)),
                new EtiquetaNumerica(0));
        tabla3.agregarFila(Arrays.asList(new Celda<>(2), new Celda<>("Naranja"), new Celda<>(15.0)),
                new EtiquetaNumerica(1));
        tabla3.agregarFila(Arrays.asList(new Celda<>(3), new Celda<>("Plátano"), new Celda<>(12.0)),
                new EtiquetaNumerica(2));

        System.out.println("Tabla original:");
        tabla3.visualizar(10, 10, 20);

        System.out.println("\nEditando la celda [1, 'Producto'] de 'Naranja' a 'Mandarina':");
        tabla3.editarCelda(new EtiquetaNumerica(1), new EtiquetaCadena("Producto"), "Mandarina");
        tabla3.visualizar(10, 10, 20);

        tabla3.agregarFila(Arrays.asList(new Celda<>(4), new Celda<>("Pera"), new Celda<>(8.0)),
                new EtiquetaNumerica(3));
        tabla3.eliminarFila(new EtiquetaNumerica(0)); // Eliminamos la fila con ID 0

        System.out.println("Tabla después de agregar una fila y eliminar otra:");
        tabla3.visualizar(10, 10, 20);
    }

    // RF 2.5 y RF 2.6: Mostrar la cantidad de filas/columnas y tipos de datos
    private static void ejecutarRF25() {
        System.out.println("Requerimientos RF 2.5 y RF 2.6: Mostrar cantidad de filas/columnas y tipos de datos");

        Tabla tabla6 = new Tabla();
        tabla6.agregarColumna(Integer.class, new EtiquetaCadena("ID"));
        tabla6.agregarColumna(String.class, new EtiquetaCadena("Nombre"));
        tabla6.agregarColumna(Double.class, new EtiquetaCadena("Salario"));

        tabla6.agregarFila(Arrays.asList(new Celda<>(1), new Celda<>("Juan"), new Celda<>(2000.0)),
                new EtiquetaNumerica(0));
        tabla6.agregarFila(Arrays.asList(new Celda<>(2), new Celda<>("María"), new Celda<>(2500.0)),
                new EtiquetaNumerica(1));

        System.out.println("Cantidad de filas: " + tabla6.getCantidadFilas());
        System.out.println("Cantidad de columnas: " + tabla6.getCantidadColumnas());

        System.out.println("Tipos de datos de cada columna:");
        for (Columna<?> columna : tabla6.getColumnas()) {
            System.out.println("Columna: " + columna.getEtiqueta().getValor() + " - Tipo: "
                    + columna.getTipoDeDato().getSimpleName());
        }
    }

    // RF 2.7 y RF 2.8: Mostrar etiquetas y cargar tabla desde archivo CSV
    private static void ejecutarRF27() {
        System.out.println("Requerimientos RF 2.7 y RF 2.8: Mostrar etiquetas y cargar tabla desde archivo CSV");

        // **RF 2.7: Mostrar las etiquetas de las filas y columnas**
        System.out.println("Requerimiento RF 2.7: Mostrar las etiquetas de las filas y columnas");

        Tabla tabla7 = new Tabla();
        tabla7.agregarColumna(Integer.class, new EtiquetaCadena("ID"));
        tabla7.agregarColumna(String.class, new EtiquetaCadena("Nombre"));
        tabla7.agregarColumna(Double.class, new EtiquetaCadena("Salario"));

        tabla7.agregarFila(Arrays.asList(new Celda<>(1), new Celda<>("Juan"), new Celda<>(2000.0)),
                new EtiquetaNumerica(0));
        tabla7.agregarFila(Arrays.asList(new Celda<>(2), new Celda<>("María"), new Celda<>(2500.0)),
                new EtiquetaNumerica(1));
        tabla7.agregarFila(Arrays.asList(new Celda<>(3), new Celda<>("Carlos"), new Celda<>(2200.0)),
                new EtiquetaNumerica(2));

        System.out.println("Etiquetas de filas:");
        for (Etiqueta etiquetaFila : tabla7.getEtiquetasFilas()) {
            System.out.println(" - " + etiquetaFila.getValor());
        }

        System.out.println("Etiquetas de columnas:");
        for (Etiqueta etiquetaColumna : tabla7.getEtiquetasColumnas()) {
            System.out.println(" - " + etiquetaColumna.getValor());
        }

        // **RF 2.8: Cargar desde el disco una tabla en formato CSV y mostrar cuanto tarda en cargar el csv**
        System.out.println("\nRequerimiento RF 2.8: Cargar desde el disco una tabla en formato CSV");

        // Cambia esta ruta al archivo CSV que quieres usar
        String rutaCsv = "data/camion.csv"; // Ajusta según tu estructura de carpetas
        try {
            Tabla tablaCsv = Tabla.leerDesdeCsv(rutaCsv, true, ",");
            System.out.println("Tabla cargada desde el archivo CSV:");
            tablaCsv.visualizar(10, 10, 20);
        } catch (Exception e) {
            System.out.println("Error al cargar el archivo CSV: " + e.getMessage());
        }

    }

    // RF 2.9, RF 2.10 y RF 2.11: Delimitadores personalizados y visualización con
    // límites
    private static void ejecutarRF291011() {
        System.out.println("\nRequerimientos RF 2.9, RF 2.10 y RF 2.11:");

        // **RF 2.9: Definir el carácter delimitador de columnas y si se utilizarán
        // etiquetas**
        System.out.println(
                "Requerimiento RF 2.9: Definir el carácter delimitador de columnas y si se utilizarán etiquetas");

        // Ruta al archivo CSV con delimitador personalizado
        String rutaCsvPersonalizado = "data/camion1.csv"; // Asegúrate de ajustar la ruta al archivo
        try {
            Tabla tablaPersonalizada = Tabla.leerDesdeCsv(rutaCsvPersonalizado, true, ";");
            System.out.println("Tabla cargada con delimitador personalizado ';':");
            tablaPersonalizada.visualizar(10, 10, 20);
        } catch (Exception e) {
            System.out.println("Error al cargar el archivo CSV con delimitador personalizado: " + e.getMessage());
        }

        // **RF 2.10: Visualizar la tabla en una forma comprensible**
        System.out.println("\nRequerimiento RF 2.10: Visualizar la tabla en una forma comprensible");

        Tabla tabla10 = new Tabla();
        tabla10.agregarColumna(Integer.class, new EtiquetaCadena("ID"));
        tabla10.agregarColumna(String.class, new EtiquetaCadena("Nombre"));
        tabla10.agregarColumna(Double.class, new EtiquetaCadena("Salario"));

        tabla10.agregarFila(Arrays.asList(new Celda<>(1), new Celda<>("Juan"), new Celda<>(2000.0)),
                new EtiquetaNumerica(0));
        tabla10.agregarFila(Arrays.asList(new Celda<>(2), new Celda<>("María"), new Celda<>(2500.0)),
                new EtiquetaNumerica(1));
        tabla10.agregarFila(Arrays.asList(new Celda<>(3), new Celda<>("Carlos"), new Celda<>(2200.0)),
                new EtiquetaNumerica(2));

        System.out.println("Visualización de la tabla:");
        tabla10.visualizar(10, 10, 20);

        // **RF 2.11: Definir un máximo de columnas y filas cuando se quieran
        // visualizar**
        System.out
                .println("\nRequerimiento RF 2.11: Definir un máximo de columnas y filas cuando se quieran visualizar");

        // Limitar la visualización a 2 filas y 2 columnas
        System.out.println("Tabla limitada a 2 filas y 2 columnas:");
        tabla10.visualizar(2, 2, 20);
    }

   

    private static void ejecutarRF213() {
        System.out.println("\nRequerimiento RF 2.13: Filtrar por columna y valor de celda");

        // Crear una tabla de prueba
        Tabla tabla = new Tabla();
        tabla.agregarColumna(Integer.class, new EtiquetaCadena("ID"));
        tabla.agregarColumna(String.class, new EtiquetaCadena("Nombre"));
        tabla.agregarColumna(Double.class, new EtiquetaCadena("Salario"));

        tabla.agregarFila(Arrays.asList(new Celda<>(1), new Celda<>("Juan"), new Celda<>(2000.0)));
        tabla.agregarFila(Arrays.asList(new Celda<>(2), new Celda<>("María"), new Celda<>(2500.0)));
        tabla.agregarFila(Arrays.asList(new Celda<>(3), new Celda<>("Carlos"), new Celda<>(2200.0)));

        // Usar el método filtrar para filtrar las filas
        Tabla tablaFiltrada = tabla.filtrar(new EtiquetaCadena("Salario"), 2000.0, ">");
        System.out.println("Tabla filtrada (Salario > 2000):");
        tablaFiltrada.visualizar(10, 10, 20);
    }

    private static void ejecutarRF214() {
        System.out.println("\nRequerimiento RF 2.14: Generar una tabla nueva a partir de una copia profunda");

        // Crear una tabla original
        Tabla tablaOriginal = new Tabla();
        tablaOriginal.agregarColumna(Integer.class, new EtiquetaCadena("ID"));
        tablaOriginal.agregarColumna(String.class, new EtiquetaCadena("Nombre"));

        tablaOriginal.agregarFila(Arrays.asList(new Celda<>(1), new Celda<>("Juan")));
        tablaOriginal.agregarFila(Arrays.asList(new Celda<>(2), new Celda<>("María")));

        // Generar una copia profunda
        Tabla tablaCopia = tablaOriginal.copiarTabla();
        System.out.println("Tabla original:");
        tablaOriginal.visualizar(10, 10, 20);

        System.out.println("Tabla copia:");
        tablaCopia.visualizar(10, 10, 20);
    }

    private static void ejecutarRF215() {
        System.out.println("\nRequerimiento RF 2.15: Crear una tabla desde una estructura 2D nativa de Java");

        // Crear una estructura 2D de datos
        List<List<Object>> estructura2D = Arrays.asList(
                Arrays.asList(1, "Juan", 2000.0),
                Arrays.asList(2, "María", 2500.0),
                Arrays.asList(3, "Carlos", 2200.0));

        // Crear una tabla con etiquetas de columnas
        Tabla tabla = new Tabla();
        tabla.agregarColumna(Integer.class, new EtiquetaCadena("ID"));
        tabla.agregarColumna(String.class, new EtiquetaCadena("Nombre"));
        tabla.agregarColumna(Double.class, new EtiquetaCadena("Salario"));

        // Agregar filas desde la estructura
        int rowIndex = 0;
        for (List<Object> fila : estructura2D) {
            List<Celda<?>> celdas = new ArrayList<>();
            for (Object valor : fila) {
                celdas.add(new Celda<>(valor));
            }
            tabla.agregarFila(celdas, new EtiquetaNumerica(rowIndex++));
        }

        System.out.println("Tabla creada desde estructura 2D:");
        tabla.visualizar(10, 10, 20);
    }

    private static void ejecutarRF216() {
        System.out.println("\nRequerimiento RF 2.16: Mostrar las n primeras o últimas filas");

        // Crear una tabla para probar
        Tabla tabla = new Tabla();
        tabla.agregarColumna(Integer.class, new EtiquetaCadena("ID"));
        tabla.agregarColumna(String.class, new EtiquetaCadena("Nombre"));
        tabla.agregarColumna(Double.class, new EtiquetaCadena("Salario"));

        tabla.agregarFila(Arrays.asList(new Celda<>(1), new Celda<>("Juan"), new Celda<>(2000.0)));
        tabla.agregarFila(Arrays.asList(new Celda<>(2), new Celda<>("María"), new Celda<>(2500.0)));
        tabla.agregarFila(Arrays.asList(new Celda<>(3), new Celda<>("Carlos"), new Celda<>(2200.0)));

        System.out.println("Primeras 2 filas:");
        for (List<Celda<?>> fila : tabla.head(2)) {
            System.out.println(fila);
        }

        System.out.println("\nÚltimas 2 filas:");
        for (List<Celda<?>> fila : tabla.tail(2)) {
            System.out.println(fila);
        }
    }

    private static void ejecutarRF217() {
        System.out.println("\nRequerimiento RF 2.17: Generar una copia profunda de una tabla");

        // Crear una tabla para copiar
        Tabla tablaOriginal = new Tabla();
        tablaOriginal.agregarColumna(Integer.class, new EtiquetaCadena("ID"));
        tablaOriginal.agregarColumna(String.class, new EtiquetaCadena("Nombre"));
        tablaOriginal.agregarColumna(Double.class, new EtiquetaCadena("Salario"));

        tablaOriginal.agregarFila(Arrays.asList(new Celda<>(1), new Celda<>("Juan"), new Celda<>(2000.0)));
        tablaOriginal.agregarFila(Arrays.asList(new Celda<>(2), new Celda<>("María"), new Celda<>(2500.0)));
        tablaOriginal.agregarFila(Arrays.asList(new Celda<>(3), new Celda<>("Carlos"), new Celda<>(2200.0)));

        // Generar una copia profunda
        Tabla tablaCopia = tablaOriginal.copiarTabla();

        System.out.println("Tabla original:");
        tablaOriginal.visualizar(10, 10, 20);

        System.out.println("Tabla copia:");
        tablaCopia.visualizar(10, 10, 20);

        // Verificar que son independientes
        tablaOriginal.editarCelda(new EtiquetaNumerica(0), new EtiquetaCadena("Nombre"), "Modificado");
        System.out.println("Tabla original modificada:");
        tablaOriginal.visualizar(10, 10, 20);

        System.out.println("Tabla copia sin modificar:");
        tablaCopia.visualizar(10, 10, 20);
    }

    private static void ejecutarRF31() {
        System.out.println("\nRequerimiento RF 3.1: Permitir agregar nuevos tipos de datos para las celdas");

        // Crear una tabla
        Tabla tabla = new Tabla();

        // Crear un tipo de dato personalizado
        class CustomData {
            private String key;
            private int value;

            public CustomData(String key, int value) {
                this.key = key;
                this.value = value;
            }

            @Override
            public String toString() {
                return "CustomData{" + "key='" + key + '\'' + ", value=" + value + '}';
            }
        }

        // Agregar una columna de tipo personalizado
        tabla.agregarColumna(CustomData.class, new EtiquetaCadena("DatosPersonalizados"));

        // Agregar filas con datos personalizados
        tabla.agregarFila(Arrays.asList(new Celda<>(new CustomData("A", 100))));
        tabla.agregarFila(Arrays.asList(new Celda<>(new CustomData("B", 200))));
        tabla.agregarFila(Arrays.asList(new Celda<>(new CustomData("C", 300))));

        System.out.println("Tabla con datos personalizados:");
        tabla.visualizar(10, 10, 50);
    }

    private static void ejecutarRF32() {
        System.out.println("\nRequerimiento RF 3.2: Cambiar el formato de la visualización de las tablas");

        // Crear una tabla
        Tabla tabla = new Tabla();
        tabla.agregarColumna(Integer.class, new EtiquetaCadena("ID"));
        tabla.agregarColumna(String.class, new EtiquetaCadena("Nombre"));
        tabla.agregarColumna(Double.class, new EtiquetaCadena("Salario"));

        // Agregar filas con datos
        tabla.agregarFila(Arrays.asList(new Celda<>(1), new Celda<>("Juan"), new Celda<>(2000.0)));
        tabla.agregarFila(Arrays.asList(new Celda<>(2), new Celda<>("María"), new Celda<>(2500.0)));
        tabla.agregarFila(Arrays.asList(new Celda<>(3), new Celda<>("Carlos"), new Celda<>(2200.0)));

        System.out.println("Tabla con formato predeterminado:");
        tabla.visualizar(10, 10, 20);

        System.out.println("\nTabla con formato alternativo:");
        for (Fila fila : tabla.head2(10)) {
            System.out.println(fila.getEtiquetaFila() + ": " + fila.getCeldasFila());
        }
    }

   

}
