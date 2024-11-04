import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestOperacionesTabla {
    public static void main(String[] args) {
        // Crear y llenar la tabla con datos
        Tabla tabla = new Tabla();
        tabla.agregarColumna(String.class, new EtiquetaCadena("Categoria"));
        tabla.agregarColumna(Integer.class, new EtiquetaCadena("Valores"));

        List<Celda<?>> fila1 = new ArrayList<>();
        fila1.add(new Celda<>("A"));
        fila1.add(new Celda<>(10));
        tabla.agregarFila(fila1);

        List<Celda<?>> fila2 = new ArrayList<>();
        fila2.add(new Celda<>("A"));
        fila2.add(new Celda<>(20));
        tabla.agregarFila(fila2);

        List<Celda<?>> fila3 = new ArrayList<>();
        fila3.add(new Celda<>("B"));
        fila3.add(new Celda<>(30));
        tabla.agregarFila(fila3);

        // Visualización inicial
        if (tabla.getCantidadFilas() > 0 && tabla.getCantidadColumnas() > 0) {
            System.out.println("Tabla inicial:");
            tabla.visualizar(3, 2, 15); // Ajustado para coincidir con la cantidad de columnas
        } else {
            System.out.println("La tabla está vacía, no se puede visualizar.");
        }

        // Definir las columnas clave para agrupar
        List<Etiqueta> columnasClave = List.of(new EtiquetaCadena("Categoria"));

        // Agrupar y aplicar operaciones
        Map<List<Object>, List<Integer>> grupos = tabla.agruparPor(columnasClave);
        System.out.println("Grupos creados: " + grupos);

        Tabla resultado = tabla.aplicarOperaciones(grupos, "suma");

        // Visualizar los resultados
        System.out.println("Tabla resultado:");
        resultado.visualizar(10, resultado.getCantidadColumnas(), 15);
    }
}
