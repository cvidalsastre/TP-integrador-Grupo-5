import java.util.ArrayList;
import java.util.List;

public class TestCopia {
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
        
        List<Celda<?>> fila1 = new ArrayList<>();
        fila1.add(c1); 
        fila1.add(c5);
        fila1.add(c9); 
        fila1.add(c13);
        t.agregarFila(fila1);
        

        List<Celda<?>> fila2 = new ArrayList<>();
        fila2.add(c2); 
        fila2.add(c6);
        fila2.add(c10); 
        fila2.add(c14);
        t.agregarFila(fila2,e6);

        List<Celda<?>> fila3 = new ArrayList<>();
        fila3.add(c3); 
        fila3.add(c7);
        fila3.add(c11); 
        fila3.add(c15);
        t.agregarFila(fila3,e7);
        
        List<Celda<?>> fila4 = new ArrayList<>();
        fila4.add(c4); 
        fila4.add(c8);
        fila4.add(c12); 
        fila4.add(c16);
        t.agregarFila(fila4);

        System.out.println("Te muestro las columnas ");
        System.out.println(t.getColumnas());

        System.out.println("Cantidad de filas: " + t.getCantidadFilas());
        System.out.println("Cantidad de columnas: " + t.getCantidadColumnas());
        System.out.println("Etiquetas de las filas: " + t.getEtiquetasFilas());
        System.out.println("Etiquetas de las columnas: " + t.getEtiquetasColumnas());


        System.out.println("Agreguemos una columna ya creada a la tabla.");

        Celda<String> c17 = new Celda<>("Argentina");
        Celda<String> c18 = new Celda<>("Colombia");
        Celda<String> c19 = new Celda<>("Brasil");
        Celda<String> c20 = new Celda<>("Chile");
        Celda<String> c22 = new Celda<>("Paraguay");

        

        Etiqueta e10 = new EtiquetaCadena("Pais");

        Columna<String> col = new Columna<String>(e10, String.class);

        col.agregarCelda(c17);
        col.agregarCelda(c18);
        col.agregarCelda(c19);
        col.agregarCelda(c20);
        col.agregarCelda(c22);


        t.agregarColumnaYC(String.class, e10, col);

        System.out.println("Cantidad de filas: " + t.getCantidadFilas());
        System.out.println("Cantidad de columnas: " + t.getCantidadColumnas());
        System.out.println("Etiquetas de las filas: " + t.getEtiquetasFilas());
        System.out.println("Etiquetas de las columnas: " + t.getEtiquetasColumnas());

        System.out.println("Te muestro las columnas ");
        System.out.println(t.getColumnas());




    }
}
