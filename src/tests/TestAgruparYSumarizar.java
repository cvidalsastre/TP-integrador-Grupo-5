package tests;
import java.util.Arrays;
import enums.OperacionEstadistica;
import kapibara.Celda;
import kapibara.EtiquetaCadena;
import kapibara.Tabla;

public class TestAgruparYSumarizar {
    public static void main(String[] args) {
        // Crear una instancia de Tabla
        Tabla tabla = new Tabla();

        // Agregar columnas a la tabla
        tabla.agregarColumna(String.class, new EtiquetaCadena("Grupo"));
        tabla.agregarColumna(Double.class, new EtiquetaCadena("Valor1"));
        tabla.agregarColumna(Double.class, new EtiquetaCadena("Valor2"));

        // Agregar filas a la tabla
        tabla.agregarFila(Arrays.asList(new Celda<>("A"), new Celda<>(10.0), new Celda<>(20.0)));
        tabla.agregarFila(Arrays.asList(new Celda<>("A"), new Celda<>(15.0), new Celda<>(25.0)));
        tabla.agregarFila(Arrays.asList(new Celda<>("B"), new Celda<>(30.0), new Celda<>(40.0)));
        tabla.agregarFila(Arrays.asList(new Celda<>("B"), new Celda<>(35.0), new Celda<>(45.0)));
        tabla.agregarFila(Arrays.asList(new Celda<>("C"), new Celda<>(50.0), new Celda<>(60.0)));

        // Agrupar y sumarizar por la columna "Grupo" con la operación "suma"
        Tabla tablaAgrupada = tabla.agruparYSumarizar(Arrays.asList(new EtiquetaCadena("Grupo")), OperacionEstadistica.MODA);

        // Visualizar la tabla agrupada
        tablaAgrupada.visualizar(10,10,10);

        // Agrupar y sumarizar por la columna "Grupo" con la operación "media"
        Tabla tablaAgrupadaMedia = tabla.agruparYSumarizar(Arrays.asList(new EtiquetaCadena("Grupo")),OperacionEstadistica.MEDIA);

        // Visualizar la tabla agrupada con la operación "media"
        tablaAgrupadaMedia.visualizar(10,10,10);

        // Visualizar tabla
        tabla.visualizar(10,10,10);

        
    }
}
