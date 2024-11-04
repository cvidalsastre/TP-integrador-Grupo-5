import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OperacionesTabla {

    public static Map<List<Object>, List<Integer>> agruparPor(Tabla tabla, List<Etiqueta> etiquetasColumnas) {
        Map<List<Object>, List<Integer>> grupos = new HashMap<>();

        for (int i = 0; i < tabla.getCantidadFilas(); i++) {
            List<Object> claveGrupo = new ArrayList<>();

            for (Etiqueta etiqueta : etiquetasColumnas) {
                Columna<?> columna = tabla.getColumna(etiqueta);
                claveGrupo.add(columna.getCeldas().get(i).getValor());
            }

            grupos.computeIfAbsent(claveGrupo, k -> new ArrayList<>()).add(i);
        }

        return grupos;
    }

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

}
