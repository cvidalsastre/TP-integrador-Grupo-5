package tests;
import java.util.ArrayList;
import java.util.List;

import kapibara.Celda;
import kapibara.EtiquetaCadena;
import kapibara.Tabla;

public class TestFiltrado {
    public static void main(String[] args) {
        // Crear la tabla
        Tabla tabla = new Tabla();
        tabla.agregarColumna(Integer.class, new EtiquetaCadena("Edad"));
        tabla.agregarColumna(String.class, new EtiquetaCadena("Nombre"));

        List<Celda<?>> fila1 = new ArrayList<>();
        fila1.add(new Celda<>(25));
        fila1.add(new Celda<>("Juan"));
        tabla.agregarFila(fila1);

        List<Celda<?>> fila2 = new ArrayList<>();
        fila2.add(new Celda<>(30));
        fila2.add(new Celda<>("Ana"));
        tabla.agregarFila(fila2);

        List<Celda<?>> fila3 = new ArrayList<>();
        fila3.add(new Celda<>(20));
        fila3.add(new Celda<>("Luis"));
        tabla.agregarFila(fila3);

        // Aplicar filtros directamente a la columna "Edad"
        Tabla resultado = tabla.filtrar(new EtiquetaCadena("Edad"), 30, "=");

        // Visualizar resultado
        resultado.visualizar(10, 2, 15);
    }
}