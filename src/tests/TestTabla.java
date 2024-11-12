package tests;
import java.util.ArrayList;
import java.util.List;

import kapibara.Celda;
import kapibara.Etiqueta;
import kapibara.EtiquetaCadena;
import kapibara.EtiquetaNumerica;
import kapibara.Fila;
import kapibara.Tabla;



public class TestTabla {
    public static void main(String[] args) {
        Celda<String> c1 = new Celda<>("Pepa");
        Celda<String> c2 = new Celda<>("Joe");
        Celda<String> c3 = new Celda<>("Rod");
        Celda<String> c4 = new Celda<>("Quinn");

        
        Celda<Integer> c5 = new Celda<>(20);
        Celda<Integer> c6 = new Celda<>(45);
        Celda<Integer> c7 = new Celda<>(18);
        Celda<Integer> c8 = new Celda<>();

        Celda<Boolean> c9 = new Celda<>(true);
        Celda<Boolean> c10 = new Celda<>(false);
        Celda<Boolean> c11 = new Celda<>(false);
        Celda<Boolean> c12 = new Celda<>();

        Celda<Float> c13 = new Celda<>();
        Celda<Float> c14 = new Celda<>(180000.5f);
        Celda<Float> c15 = new Celda<>(455555.78f);
        Celda<Float> c16 = new Celda<>(1234567.99f);
        
        
        
        Tabla t = new Tabla();
        System.out.println("Tabla pelada");

        System.out.println("Cantidad de filas: " + t.getCantidadFilas());
        System.out.println("Cantidad de columnas: " + t.getCantidadColumnas());
        System.out.println("Etiquetas de las filas: " + t.getEtiquetasFilas());
        System.out.println("Etiquetas de las columnas: " + t.getEtiquetasColumnas());

        
        //Etiquetas columnas
        Etiqueta e1 = new EtiquetaCadena("Nombre");
        Etiqueta e2 = new EtiquetaNumerica(1);
        Etiqueta e3 = new EtiquetaCadena("Horas Extras?");
        Etiqueta e4 = new EtiquetaCadena("Sueldo");
        Etiqueta e9 = new EtiquetaCadena("Sueldo");

        //Etiquetas filas
        Etiqueta e5 = new EtiquetaNumerica(0);
        Etiqueta e6 = new EtiquetaCadena("NSA");
        Etiqueta e7 = new EtiquetaCadena("Empleadx del mes");
        Etiqueta e8 = new EtiquetaNumerica(3);


        t.agregarColumna(String.class, e1); // Columna 0: Nombre
        t.agregarColumna(Integer.class); // Columna 1: 0
        t.agregarColumna(Boolean.class, e3); // Columna 2: Horas Extras?
        t.agregarColumna(Float.class, e4); // Columna 3: Sueldo
        // tira excepción (ya existe la columna "Sueldo")
        //t.agregarColumna(Boolean.class,e9);
        
        System.out.println("Cantidad de filas: " + t.getCantidadFilas());
        System.out.println("Cantidad de columnas: " + t.getCantidadColumnas());
        System.out.println("Etiquetas de las filas: " + t.getEtiquetasFilas());
        System.out.println("Etiquetas de las columnas: " + t.getEtiquetasColumnas());

    
        List<Celda<?>> fila1 = new ArrayList<>();
        fila1.add(c1); 
        fila1.add(c5);
        fila1.add(c9); 
        fila1.add(c13);
        t.agregarFila(fila1);
        
        System.out.println("Cantidad de filas: " + t.getCantidadFilas());
        System.out.println("Cantidad de columnas: " + t.getCantidadColumnas());
        System.out.println("Etiquetas de las filas: " + t.getEtiquetasFilas());
        System.out.println("Etiquetas de las columnas: " + t.getEtiquetasColumnas());



        List<Celda<?>> fila2 = new ArrayList<>();
        fila2.add(c2); 
        fila2.add(c6);
        fila2.add(c10); 
        fila2.add(c14);
        t.agregarFila(fila2,e6);

        System.out.println("Cantidad de filas: " + t.getCantidadFilas());
        System.out.println("Cantidad de columnas: " + t.getCantidadColumnas());
        System.out.println("Etiquetas de las filas: " + t.getEtiquetasFilas());
        System.out.println("Etiquetas de las columnas: " + t.getEtiquetasColumnas());

        List<Celda<?>> fila3 = new ArrayList<>();
        fila3.add(c3); 
        fila3.add(c7);
        fila3.add(c11); 
        fila3.add(c15);
        t.agregarFila(fila3,e7);

        System.out.println("Cantidad de filas: " + t.getCantidadFilas());
        System.out.println("Cantidad de columnas: " + t.getCantidadColumnas());
        System.out.println("Etiquetas de las filas: " + t.getEtiquetasFilas());
        System.out.println("Etiquetas de las columnas: " + t.getEtiquetasColumnas());

        
        List<Celda<?>> fila4 = new ArrayList<>();
        fila4.add(c4); 
        fila4.add(c8);
        fila4.add(c12); 
        fila4.add(c16);
        t.agregarFila(fila4);


        
        System.out.println("Cantidad de filas: " + t.getCantidadFilas());
        System.out.println("Cantidad de columnas: " + t.getCantidadColumnas());
        System.out.println("Etiquetas de las filas: " + t.getEtiquetasFilas());
        System.out.println("Etiquetas de las columnas: " + t.getEtiquetasColumnas());

        System.out.println("Visualizar las 3 primeras filas, con las 2 primeras columnas y largo máx de cadena 1");
        t.visualizar(3, 2, 1);
        
        
        float porcentaje = 50f;
        System.out.println("Muestro aleatorio con porcentaje " + porcentaje + " % ");

        System.out.println("Te muestro las columnas ");
        System.out.println(t.getColumnas());

        System.out.println("headLista(3)");
        t.imprimirFilas(t.getEtiquetasColumnas(),t.headLista(3));

        System.out.println("DDDDDDDDDDDDDDDDDDDD");
        // Hecho con la clase FILA

        System.out.println("headLista(3)");
        t.imprimirFilas(t.head(3));
        List<Fila> filas1 = t.head(2);

        t.editarCelda(e5,e1 , "aaaaaa");
        System.out.println(filas1);
        
        System.out.println("headLista(3)");
        t.imprimirFilas(t.tail(3));


        System.out.println("VISUALIZAR");
        t.visualizar(3, 2, 1);


        t.imprimirFilas(t.muestreo(50f));

        System.out.println("Selección Parcial");

        List<Etiqueta> selCol = new ArrayList<>();
        List<Etiqueta> selFilas = new ArrayList<>();

        selCol.add(e4); // "Sueldo"
        selCol.add(e1); // "Nombre"
        selCol.add(e2); // 1
        
        
        selFilas.add(e6); // "NSA"
        selFilas.add(e7); // "Empleadx del mes"

        t.imprimirFilas(t.seleccionParcial(selFilas, selCol));


        System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");

        System.out.println("tailList(2)");
        t.imprimirFilas(t.getEtiquetasColumnas(),t.tailList(2));

        System.out.println("Selección Parcial");

        
    }
}