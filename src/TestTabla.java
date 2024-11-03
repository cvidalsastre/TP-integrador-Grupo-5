import java.util.ArrayList;
import java.util.List;



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

        t.agregarColumna(String.class, new EtiquetaCadena("Nombre")); // Columna 0: Nombre
        t.agregarColumna(Integer.class); // Columna 1: 0
        t.agregarColumna(Boolean.class, new EtiquetaCadena("Horas Extras?")); // Columna 2: Horas Extras?
        t.agregarColumna(Float.class, new EtiquetaCadena("Sueldo")); // Columna 3: Sueldo 
        
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
        t.agregarFila(fila2,new EtiquetaCadena("NSA"));

        System.out.println("Cantidad de filas: " + t.getCantidadFilas());
        System.out.println("Cantidad de columnas: " + t.getCantidadColumnas());
        System.out.println("Etiquetas de las filas: " + t.getEtiquetasFilas());
        System.out.println("Etiquetas de las columnas: " + t.getEtiquetasColumnas());

        List<Celda<?>> fila3 = new ArrayList<>();
        fila3.add(c3); 
        fila3.add(c7);
        fila3.add(c11); 
        fila3.add(c15);
        t.agregarFila(fila3,new EtiquetaCadena("Empleadx del mes"));

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
        System.out.println(t.muestreo(porcentaje));

        System.out.println("Te muestro las columnas ");
        System.out.println(t.getColumnas());





            
      
        
    }
}