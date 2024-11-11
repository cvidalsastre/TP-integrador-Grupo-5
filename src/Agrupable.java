import java.util.List;

import enums.OperacionEstadistica;

public interface Agrupable {
Tabla agruparYSumarizar(List<Etiqueta> etiquetasColumnasAgrupamiento, OperacionEstadistica operacion);

}