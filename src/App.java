import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {

    public static void main(String[] args) {
        // Prueba de instanciar una tabla vacia
        Tabla tabla1 = new Tabla();

        System.out.println("deberia imprimir 0 = " + tabla1.getCantidadColumnas());
        System.out.println("deberia imprimir 0 = " + tabla1.getCantidadFilas());

        // Prueba agregar columna tipo String a la tabla vacia
        tabla1.agregarColumna(String.class);
        tabla1.agregarColumna(int.class);
        tabla1.agregarColumna(boolean.class);

        System.out.println("deberia cumplir 3 = " + tabla1.getCantidadColumnas());

        int contadorPrintEtiqueta = 0;

        // ejemplo de print de etiquetas numericas

        System.out.println("Chequeando etiquetas de columnas de la tabla sin sobrecarga de parametros");

        for (Etiqueta etiqueta : tabla1.getEtiquetasColumnas()) {
            System.out
                    .println("valor etiqueta, deberia cumplir " + contadorPrintEtiqueta + " = " + etiqueta.getValor());
            contadorPrintEtiqueta++;
        }

        // como aca no agregué filas todavia deberia estar vacia
        System.out.println("deberia cumplir [] = " + tabla1.getEtiquetasFilas());

        // pruebo agregando algunas filas sin sobrecarga de parametros

        tabla1.agregarFila();
        tabla1.agregarFila();
        tabla1.agregarFila();

        System.out.println("Deberia cumplir 3 = " + tabla1.getCantidadFilas());

        contadorPrintEtiqueta = 0;

        System.out.println("Chequeando etiquetas de filas de la tabla sin sobrecarga de parametros");

        for (Etiqueta etiqueta : tabla1.getEtiquetasFilas()) {
            System.out
                    .println("valor etiqueta, deberia cumplir " + contadorPrintEtiqueta + " = " + etiqueta.getValor());
            contadorPrintEtiqueta++;
        }

        // Chequeo los valores de todas las celdas de las columnas
        System.out.println("Chequeando valores de las celdas en las columnas:");

        for (Columna<?> columna : tabla1.getColumnas()) {
            System.out.println("Columna: " + columna.getEtiqueta().getValor() + " con " + columna.getCeldas().size() + " celdas");
            for (Celda<?> celda : columna.getCeldas()) {
                System.out.print("Valor celda: " + celda.getValor() + " "); // Debe imprimir null
                System.out.println();
            }
            System.out.println(); // Nueva línea para la siguiente columna
        }

        // TODO habria que chequear ahora agregando filas con valores de cierto tipo de dato

    }

}