public class Visualizador {

    // Esta clase se encarga de la visualización de los datos. Se inyecta una
    // instancia de Tabla en el constructor para acceder a los datos que se deben
    // mostrar.

    private Tabla tabla; // Instancia de la interfaz Tabla para acceder a los datos

    public Visualizador(Tabla tabla) {
        this.tabla = tabla; // Constructor que recibe una instancia de Tabla
    }

    /**
     * Presenta los datos en formato texto.
     * 
     * @param maxFilas              El número máximo de filas a mostrar
     * @param maxColumnas           El número máximo de columnas a mostrar
     * @param maxCaracteresPorCelda El número máximo de caracteres por celda
     */
    public void visualizar(int maxFilas, int maxColumnas, int maxCaracteresPorCelda) {
        // Recorre las filas hasta el número máximo especificado o hasta el total de
        // filas en la tabla
        for (int i = 0; i < Math.min(tabla.getCantidadFilas(), maxFilas); i++) {
            // Recorre las columnas hasta el número máximo especificado o hasta el total de
            // columnas en la tabla
            for (int j = 0; j < Math.min(tabla.getCantidadColumnas(), maxColumnas); j++) {
                // Obtiene el dato de la celda y convierte a cadena
                String dato = tabla.getDato(i, j).toString();
                // Si el dato excede el número máximo de caracteres por celda, lo recorta y
                // añade "..."
                if (dato.length() > maxCaracteresPorCelda) {
                    dato = dato.substring(0, maxCaracteresPorCelda) + "...";
                }
                // Imprime el dato con el formato especificado
                System.out.print(String.format("%-" + maxCaracteresPorCelda + "s", dato));
            }
            // Salta a la siguiente línea después de imprimir todos los datos de una fila
            System.out.println();
        }
    }
}
