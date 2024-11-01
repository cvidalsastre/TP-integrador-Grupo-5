import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {

    public static void main(String[] args) {
        // Prueba de instanciar una tabla vacia
        Tabla tabla1 = new Tabla();

        System.out.println();
        System.out.println("Chequeando cantidad de filas y columnas de una tabla vacia");
        System.out.println();

        System.out.println("deberia imprimir 0 = " + tabla1.getCantidadColumnas());
        System.out.println("deberia imprimir 0 = " + tabla1.getCantidadFilas());

        // Prueba agregar columna tipo String a la tabla vacia
        tabla1.agregarColumna(String.class);
        tabla1.agregarColumna(int.class);
        tabla1.agregarColumna(boolean.class);

        System.out.println();
        System.out.println("Chequeando cantidad de columnas de una tabla despues de agregar 3 columnas");
        System.out.println();

        System.out.println("deberia cumplir 3 = " + tabla1.getCantidadColumnas());

        int contadorPrintEtiqueta = 0;

        // ejemplo de print de etiquetas numericas
        System.out.println();
        System.out.println("Chequeando etiquetas de columnas de la tabla sin sobrecarga de parametros");
        System.out.println();

        for (Etiqueta etiqueta : tabla1.getEtiquetasColumnas()) {
            System.out
                    .println("valor etiqueta, deberia cumplir " + contadorPrintEtiqueta + " = " + etiqueta.getValor());
            contadorPrintEtiqueta++;
        }


        System.out.println();
        System.out.println("Chequeando etiquetas de filas de la tabla sin filas");
        System.out.println(); 
        // como aca no agregué filas todavia deberia estar vacia
        System.out.println("deberia cumplir [] = " + tabla1.getEtiquetasFilas());

        // pruebo agregando algunas filas sin sobrecarga de parametros
        System.out.println();
        System.out.println("Chequeando cantidad etiquetas de filas de la tabla generadas  sin sobrecarga de parametros");
        System.out.println();
        tabla1.agregarFila();
        tabla1.agregarFila();
        tabla1.agregarFila();

        System.out.println("Deberia cumplir 3 = " + tabla1.getCantidadFilas());


        contadorPrintEtiqueta = 0;
      System.out.println();
        System.out.println("Chequeando etiquetas de filas de la tabla sin sobrecarga de parametros");
        System.out.println();

        for (Etiqueta etiqueta : tabla1.getEtiquetasFilas()) {
            System.out
                    .println("valor etiqueta, deberia cumplir " + contadorPrintEtiqueta + " = " + etiqueta.getValor());
            contadorPrintEtiqueta++;
        }

        // Chequeo los valores de todas las celdas de las columnas
        System.out.println();
        System.out.println("Chequeando valores de las celdas en las columnas:");
        System.out.println();

        for (Columna<?> columna : tabla1.getColumnas()) {
            System.out.println();
            System.out.println(
                    "Columna: " + columna.getEtiqueta().getValor() + " con " + columna.getCeldas().size() + " celdas");
            for (Celda<?> celda : columna.getCeldas()) {
                System.out.print("Valor celda: " + celda.getValor() + " "); // Debe imprimir null
                System.out.println();
            }
            System.out.println(); // Nueva línea para la siguiente columna
        }

        // TODO habria que chequear ahora agregando filas con valores de cierto tipo de
        // dato

        // Creación de la tabla
        Tabla tabla = new Tabla();

        tabla.agregarColumna(String.class, new EtiquetaCadena("Nombre")); // Columna 0: Nombre
        tabla.agregarColumna(Integer.class, new EtiquetaCadena("edad")); // Columna 1: Edad

        List<Celda<?>> fila1 = new ArrayList<>();
        fila1.add(new Celda<>("Alice")); // Nombre - debe ser String
        fila1.add(new Celda<>(30));
        tabla.agregarFila(fila1);
        List<Celda<?>> fila2 = new ArrayList<>();
        fila2.add(new Celda<>("bob")); // Nombre - debe ser String
        fila2.add(new Celda<>(300));
        tabla.agregarFila(fila2);


        
        System.out.println();
        System.out.println("Chequeando valores de y tipo de la primer fila");
        System.out.println();
        for (Celda<?> celda : fila1) {
            System.out.println("Valor: " + celda.getValor() + ", Tipo: "
                    + (celda.getValor() != null ? celda.getValor().getClass().getSimpleName() : "null"));
        }

        // Chequeo los valores de todas las celdas de las columnas
        System.out.println();
        System.out.println("Chequeando valores de las celdas en las columnas:");
        System.out.println();

        for (Columna<?> columna : tabla.getColumnas()) {
            System.out.println(
                    "Columna: " + columna.getEtiqueta().getValor() + " con " + columna.getCeldas().size() + " celdas");
            for (Celda<?> celda : columna.getCeldas()) {
                System.out.print("Valor celda: " + celda.getValor() + " "); // Debe imprimir null
                System.out.println();
            }
            System.out.println(); // Nueva línea para la siguiente columna
        }

        Etiqueta labelFila = new EtiquetaNumerica(0);
        Etiqueta labelColumna = new EtiquetaCadena("Nombre");

        System.out.println("MODIFICANDO CELDA");
        tabla.volverNACelda(labelFila, labelColumna);
        System.out.println(tabla.getCelda(labelFila, labelColumna).getValor());

        tabla.volverNACelda(labelFila, labelColumna);
        System.out.println(tabla.getCelda(labelFila, labelColumna).getValor());

        tabla.editarCelda(labelFila, labelColumna, "32432");


        System.out.println("Prueba de get fila " + labelFila );
        System.out.println(tabla.getFila(labelFila));
        System.out.println();
        // probando EliminarFila 

        
        tabla.eliminarFila(labelFila);

        for (Columna<?> columna : tabla.getColumnas()) {
            System.out.println(
                    "Columna: " + columna.getEtiqueta().getValor() + " con " + columna.getCeldas().size() + " celdas");
            for (Celda<?> celda : columna.getCeldas()) {
                System.out.print("Valor celda: " + celda.getValor() + " "); 
                System.out.println();
            }
            System.out.println(); 
        }

    }
}