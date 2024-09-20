import java.io.*; // Se importa toda la biblioteca de entrada/salida (IO) para leer y escribir archivos
import java.nio.charset.StandardCharsets; // Se importa para manejar la codificación de caracteres (en este caso, UTF-8)
import java.util.List; // Importa la clase List, que es una colección que se usa para manejar filas de la tabla

public class GestorCSV {

    // Método que lee un archivo CSV línea por línea y usa el delimitador
    // especificado para dividir cada línea en columnas, almacenando estos valores
    // en la tabla.
    public void cargarDesdeCSV(Tabla tabla, String archivo, String delimitador, boolean tieneEncabezado)
            throws IOException {
        // BufferedReader lee el archivo línea por línea, con codificación UTF-8
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(archivo), StandardCharsets.UTF_8))) {
            String linea; // Variable para almacenar cada línea del archivo CSV
            // Si tiene encabezado, lee la primera línea y asigna las etiquetas a las
            // columnas
            if (tieneEncabezado && (linea = br.readLine()) != null) {
                String[] encabezados = linea.split(delimitador); // Separa los encabezados por el delimitador
                for (int i = 0; i < encabezados.length; i++) {
                    tabla.setEtiquetaColumna(i, encabezados[i]); // Asigna las etiquetas a las columnas en la tabla
                }
            }
            int filaIndex = 0; // Índice de fila actual
            // Bucle para leer las siguientes líneas del archivo
            while ((linea = br.readLine()) != null) {
                String[] valores = linea.split(delimitador); // Separa los valores de cada fila
                // Recorre cada columna y asigna el valor a la tabla
                for (int columna = 0; columna < valores.length; columna++) {
                    // Si el valor es "NA", se asigna null, de lo contrario se asigna el valor
                    tabla.setDato(filaIndex, columna,
                            valores[columna].equals("NA") ? null : parseDato(valores[columna]));
                }
                filaIndex++; // Pasa a la siguiente fila
            }
        }
    }

    // Método que toma los datos de la tabla y los escribe en un archivo CSV, con la
    // opción de incluir un encabezado con los nombres de las columnas.
    public void guardarComoCSV(Tabla tabla, String archivo, String delimitador, boolean incluirEncabezado)
            throws IOException {
        // BufferedWriter escribe en un archivo con codificación UTF-8
        try (BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(archivo), StandardCharsets.UTF_8))) {
            // Si se incluye el encabezado, escribe las etiquetas de las columnas
            if (incluirEncabezado) {
                bw.write(String.join(delimitador, tabla.getEtiquetasColumnas())); // Une las etiquetas con el
                                                                                  // delimitador
                bw.newLine(); // Inserta una nueva línea después de los encabezados
            }
            // Recorre cada fila de la tabla
            for (int i = 0; i < tabla.getCantidadFilas(); i++) {
                // Obtiene la fila actual
                List<Object> fila = tabla.obtenerFila(i);
                // Convierte la fila a una cadena, reemplazando null con "NA"
                String filaStr = String.join(delimitador, fila.stream()
                        .map(celda -> formatearDato(celda)) // Convierte cada celda a cadena
                        .toArray(String[]::new)); // Convierte la lista a un arreglo de Strings
                bw.write(filaStr); // Escribe la fila en el archivo
                bw.newLine(); // Inserta una nueva línea después de cada fila
            }
        }
    }

    // Convertir una cadena en el tipo adecuado (según el formato de los datos)
    private Object parseDato(String dato) {
        if (dato.matches("-?\\d+")) { // Verifica si el dato es un entero
            return Integer.parseInt(dato);
        } else if (dato.matches("-?\\d+\\.\\d+")) { // Verifica si el dato es un número decimal
            return Double.parseDouble(dato);
        } else {
            return dato; // Si no es un número, devuelve el dato como una cadena
        }
    }

    // Convertir datos a formato de cadena (para CSV)
    private String formatearDato(Object dato) {
        return dato == null ? "NA" : dato.toString(); // Si el dato es null, devuelve "NA"; de lo contrario, convierte
                                                      // el dato a una cadena
    }
}
