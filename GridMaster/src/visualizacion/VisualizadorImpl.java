package visualizacion;

import core.Tabla;

public class VisualizadorImpl implements Visualizador {
    private Tabla tabla;

    public VisualizadorImpl(Tabla tabla) {
        this.tabla = tabla;
    }

    @Override
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
