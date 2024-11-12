package kapibara;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.File;

/**
 * Clase para leer y procesar datos desde un archivo CSV.
 */
public class CsvReader {

    /**
     * Lee un archivo CSV y retorna una lista de listas donde cada lista interna
     * representa los datos de una columna.
     *
     * @param rutaArchivo    La ruta del archivo CSV a leer.
     * @param includeHeaders Indica si se deben incluir los encabezados como la
     *                       primera fila.
     * @param dataSeparator  El carácter separador de columnas en el archivo (por
     *                       ejemplo, "," o ";").
     * @return Una lista de listas, donde cada sublista contiene los datos de una
     *         columna.
     */
    public List<List<String>> leerCSV(String rutaArchivo, boolean includeHeaders, String dataSeparator) {
        List<List<String>> datosPorColumna = new ArrayList<>();
        File file = new File(rutaArchivo);

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String linea;
            List<List<String>> todasLasFilas = new ArrayList<>();

            // Leer todas las líneas y dividirlas en columnas
            while ((linea = bufferedReader.readLine()) != null) {
                List<String> elementosLineaColumnas = Arrays.asList(linea.split(dataSeparator, -1)); // Usar -1 para
                                                                                                     // incluir valores
                                                                                                     // vacíos
                todasLasFilas.add(elementosLineaColumnas);
            }

            if (todasLasFilas.isEmpty()) {
                System.out.println("El archivo está vacío.");
                return datosPorColumna;
            }

            // Inicializar listas por cada columna
            int numeroDeColumnas = todasLasFilas.get(0).size();
            for (int i = 0; i < numeroDeColumnas; i++) {
                datosPorColumna.add(new ArrayList<>());
            }

            // Si se incluyen encabezados, agregarlos como la primera fila
            int startRow = 0;
            if (includeHeaders) {
                List<String> headers = todasLasFilas.get(0);
                for (int i = 0; i < numeroDeColumnas; i++) {
                    datosPorColumna.get(i).add(headers.get(i));
                }
                startRow = 1;
            }

            // Analizar cada columna y clasificar los elementos
            for (int fila = startRow; fila < todasLasFilas.size(); fila++) {
                List<String> elementosFila = todasLasFilas.get(fila);

                // Verificar si la fila tiene el número correcto de columnas
                if (elementosFila.size() != numeroDeColumnas) {
                    System.out.println("Saltando línea con número incorrecto de columnas: " + elementosFila);
                    continue; // Saltar esta línea
                }

                try {
                    for (int columna = 0; columna < numeroDeColumnas; columna++) {
                        String elemento = elementosFila.get(columna).trim();
                        datosPorColumna.get(columna).add(elemento.isEmpty() ? "NA" : elemento); // Tratar valores vacíos
                                                                                                // como "NA"
                    }
                } catch (Exception e) {
                    System.out.println("Error al procesar la fila: " + elementosFila + ". Saltando esta fila.");
                    continue; // Saltar esta línea en caso de error
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return datosPorColumna;
    }

    /**
     * Identifica los tipos de datos de cada columna en el archivo CSV.
     *
     * @param datosPorColumna La lista de datos clasificados por columna.
     * @param includeHeaders  Indica si se debe excluir la primera fila
     *                        (encabezados) al analizar los tipos.
     * @return Una lista de tipos de datos (Integer, Double, String, Boolean) por
     *         columna.
     */
    public List<Class<?>> identificarTipos(List<List<String>> datosPorColumna, boolean includeHeaders) {
        List<Class<?>> tiposDeColumnas = new ArrayList<>();
        int startRow = includeHeaders ? 1 : 0;

        for (int columna = 0; columna < datosPorColumna.size(); columna++) {
            Class<?> tipo = null;

            for (int fila = startRow; fila < datosPorColumna.get(columna).size(); fila++) {
                String valor = datosPorColumna.get(columna).get(fila).trim();

                if (!valor.isEmpty() && !valor.equalsIgnoreCase("NA")) {
                    tipo = determinarTipo(valor);
                    if (tipo != null) {
                        break;
                    }
                }
            }

            if (tipo == null) {
                tipo = String.class; // Asignar String como tipo por defecto si no se encuentra otro tipo
            }

            tiposDeColumnas.add(tipo);
        }

        return tiposDeColumnas;
    }

    /**
     * Determina el tipo de dato de un valor dado.
     *
     * @param valor El valor a analizar.
     * @return La clase que representa el tipo de dato (Integer, Double, Boolean o
     *         String).
     */
    private Class<?> determinarTipo(String valor) {
        try {
            Integer.parseInt(valor);
            return Integer.class;
        } catch (NumberFormatException e) {
            // No es un Integer
        }

        try {
            Double.parseDouble(valor);
            return Double.class;
        } catch (NumberFormatException e) {
            // No es un Double
        }

        if (valor.equalsIgnoreCase("true") || valor.equalsIgnoreCase("false")) {
            return Boolean.class;
        }

        return String.class; // Si no es Integer, Double o Boolean, asumir que es String
    }
}
