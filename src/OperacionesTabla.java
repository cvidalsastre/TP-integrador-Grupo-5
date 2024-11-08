import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OperacionesTabla {

    /*
     * public static Tabla agruparPor(Tabla tabla, List<Etiqueta> etiquetasColumnas)
     * {
     * Tabla otraTabla = new Tabla();
     * 
     * for (int i = 0; i < tabla.getCantidadFilas(); i++) {
     * 
     * 
     * for (Etiqueta etiqueta : etiquetasColumnas) {
     * Columna<?> columna = tabla.getColumna(etiqueta);
     * 
     * //Metodo para agregar columnas ya creadas a tablas
     * //claveGrupo.add(columna.getCeldas().get(i).getValor());
     * }
     * 
     * //grupos.computeIfAbsent(claveGrupo, k -> new ArrayList<>()).add(i);
     * }
     * 
     * // return grupos;
     * }
     */

    public static Tabla aplicarOperaciones(Tabla tabla, Map<List<Object>, List<Integer>> grupos, String operacion) {
        Tabla resultado = new Tabla();

        // Agregar columnas numéricas al resultado
        for (Columna<?> columna : tabla.getColumnas()) {
            if (Number.class.isAssignableFrom(columna.getTipoDeDato())) {
                resultado.agregarColumna(columna.getTipoDeDato(), columna.getEtiqueta());
            }
        }

        for (Map.Entry<List<Object>, List<Integer>> entrada : grupos.entrySet()) {
            List<Object> valoresResultado = new ArrayList<>();

            for (Columna<?> columna : tabla.getColumnas()) {
                if (Number.class.isAssignableFrom(columna.getTipoDeDato())) {
                    List<Number> valoresGrupo = new ArrayList<>();
                    for (int indice : entrada.getValue()) {
                        valoresGrupo.add((Number) columna.getCeldas().get(indice).getValor());
                    }

                    // Depuración
                    System.out.println("Valores del grupo: " + valoresGrupo);
                    System.out.println("Operación: " + operacion);

                    Number resultadoOperacion = calcularOperacion(valoresGrupo, operacion);
                    valoresResultado.add(resultadoOperacion);
                }
            }

            // Depuración antes de agregar la fila
            System.out.println("Valores resultado: " + valoresResultado);

            List<Celda<?>> filaResultado = new ArrayList<>();
            for (Object valor : valoresResultado) {
                filaResultado.add(new Celda<>(valor));
            }

            resultado.agregarFila(filaResultado, new EtiquetaCadena(entrada.getKey().toString()));
        }

        return resultado;
    }

    public static Number calcularOperacion(List<Number> valores, String operacion) {
        if (valores.isEmpty())
            return 0; // O manejar según la lógica deseada

        switch (operacion) {
            case "suma":
                return valores.stream().mapToDouble(Number::doubleValue).sum();
            case "máximo":
                return valores.stream().mapToDouble(Number::doubleValue).max().orElse(0);
            case "mínimo":
                return valores.stream().mapToDouble(Number::doubleValue).min().orElse(0);
            case "cuenta":
                return valores.size();
            case "media":
                return valores.stream().mapToDouble(Number::doubleValue).average().orElse(0);
            case "varianza":
                double media = valores.stream().mapToDouble(Number::doubleValue).average().orElse(0);
                return valores.stream().mapToDouble(Number::doubleValue)
                        .map(v -> Math.pow(v - media, 2))
                        .average().orElse(0);
            case "desvío estándar":
                double varianza = calcularOperacion(valores, "varianza").doubleValue();
                return Math.sqrt(varianza);
            default:
                throw new IllegalArgumentException("Operación no soportada: " + operacion);
        }
    }

    /**
     * Método para concatenar dos tablas.
     *
     * @param tabla1 La primera tabla.
     * @param tabla2 La segunda tabla.
     * @return Una nueva tabla que contiene todas las filas de ambas tablas.
     * @throws IllegalArgumentException si las tablas no son compatibles para la
     *                                  concatenación.
     */
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
            List<Celda<?>> fila = tabla1.getFila(tabla1.getEtiquetasFilas().get(i));
            resultado.agregarFila(fila);
        }

        // Copiar las filas de la segunda tabla
        for (int i = 0; i < tabla2.getCantidadFilas(); i++) {
            List<Celda<?>> fila = tabla2.getFila(tabla2.getEtiquetasFilas().get(i));
            resultado.agregarFila(fila);
        }

        return resultado;
    }

}
