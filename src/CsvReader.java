import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.File;

public class CsvReader {

    public List<List<String>> leerCSV(String rutaArchivo, boolean includeHeaders,String dataSeparator) {
        List<List<String>> datosPorColumna = new ArrayList<>();
        File file = new File(rutaArchivo);

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            
            String linea;
            List<List<String>> todasLasFilas = new ArrayList<>();

            // Leer todas las líneas y dividirlas en columnas
            while ((linea = bufferedReader.readLine()) != null) {
                List<String> elementosLineaColumnas = Arrays.asList(linea.split(dataSeparator));
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

                for (int columna = 0; columna < numeroDeColumnas; columna++) {
                    String elemento = elementosFila.get(columna).trim();
                    datosPorColumna.get(columna).add(elemento);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return datosPorColumna;
    }

    
    public List<Class<?>> identificarTipos(List<List<String>> datosPorColumna) {
        List<Class<?>> tipos = new ArrayList<>();
    
        for (List<String> columna : datosPorColumna) {
            Class<?> tipo = String.class; // Default type
    
            for (String elemento : columna) {
                if (elemento.equalsIgnoreCase("true") || elemento.equalsIgnoreCase("false")) {
                    tipo = Boolean.class;
                    break;
                } else {
                    try {
                        Double.parseDouble(elemento);
                        tipo = Double.class;
                        break;
                    } catch (NumberFormatException e) {
                        tipo = String.class;
                        tipos.add(tipo);
                    }
                }
            }
            tipos.add(tipo);
        }
        return tipos;
    }




}
