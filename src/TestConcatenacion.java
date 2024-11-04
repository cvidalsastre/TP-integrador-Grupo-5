import java.util.ArrayList;
import java.util.List;

public class TestConcatenacion {
    public static void main(String[] args) {
        // Crear dos tablas
        Tabla tabla1 = new Tabla();
        tabla1.agregarColumna(String.class, new EtiquetaCadena("Nombre"));
        tabla1.agregarColumna(Integer.class, new EtiquetaCadena("Edad"));

        List<Celda<?>> fila1 = new ArrayList<>();
        fila1.add(new Celda<>("Alice"));
        fila1.add(new Celda<>(25));
        tabla1.agregarFila(fila1);

        Tabla tabla2 = new Tabla();
        tabla2.agregarColumna(String.class, new EtiquetaCadena("Nombre"));
        tabla2.agregarColumna(Integer.class, new EtiquetaCadena("Edad"));

        List<Celda<?>> fila2 = new ArrayList<>();
        fila2.add(new Celda<>("Bob"));
        fila2.add(new Celda<>(30));
        tabla2.agregarFila(fila2);

        // Concatenar las tablas
        Tabla tablaConcatenada = OperacionesTabla.concatenar(tabla1, tabla2);

        // Mostrar la tabla concatenada
        System.out.println("Tabla concatenada:");
        tablaConcatenada.visualizar(10, 2, 15);
    }
}
