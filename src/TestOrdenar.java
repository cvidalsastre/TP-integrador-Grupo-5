import java.util.ArrayList;
import java.util.List;

public class TestOrdenar {
    public static void main(String[] args) {
        Tabla tabla = new Tabla();

        // Agregar columnas
        tabla.agregarColumna(String.class, new EtiquetaCadena("Nombre"));
        tabla.agregarColumna(Integer.class, new EtiquetaCadena("Edad"));

        // Agregar filas
        List<Celda<?>> fila1 = new ArrayList<>();
        fila1.add(new Celda<>("Alice"));
        fila1.add(new Celda<>(30));
        tabla.agregarFila(fila1);

        List<Celda<?>> fila2 = new ArrayList<>();
        fila2.add(new Celda<>("Bob"));
        fila2.add(new Celda<>(25));
        tabla.agregarFila(fila2);

        List<Celda<?>> fila3 = new ArrayList<>();
        fila3.add(new Celda<>("Charlie"));
        fila3.add(new Celda<>(35));
        tabla.agregarFila(fila3);

        // Imprimir tabla antes de ordenar
        System.out.println("Tabla antes de ordenar:");
        try {
            tabla.visualizar(3, 2, 15); // Asegúrate de que maxFilas y maxColumnas son correctos
        } catch (IllegalArgumentException e) {
            System.out.println("Error al visualizar: " + e.getMessage());
        }

        // Ordenar por Edad en forma ascendente
        tabla.ordenarPor(List.of(new EtiquetaCadena("Edad")), true);

        // Imprimir tabla después de ordenar
        System.out.println("\nTabla después de ordenar por Edad:");
        try {
            tabla.visualizar(3, 2, 15); // Asegúrate de que maxFilas y maxColumnas son correctos
        } catch (IllegalArgumentException e) {
            System.out.println("Error al visualizar: " + e.getMessage());
        }
    }
}
