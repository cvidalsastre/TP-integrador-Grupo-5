import java.util.List;
import java.util.Map;

public interface Agrupable {
    Map<List<Object>, List<Integer>> agruparPor(List<Etiqueta> etiquetasColumnas);

    Tabla aplicarOperaciones(Map<List<Object>, List<Integer>> grupos, String operacion);
}