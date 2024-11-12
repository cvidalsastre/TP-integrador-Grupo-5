package tests;
import kapibara.Celda;
import kapibara.Columna;
import kapibara.Etiqueta;
import kapibara.EtiquetaCadena;
import kapibara.EtiquetaNumerica;

public class TestColumna<T>{
    public static void main(String[] args) {

        System.out.println("Columna de String");
        Etiqueta e1 = new EtiquetaCadena("Nombre");
        Columna<String> col1 = new Columna<>(e1, String.class);

        System.out.println("Tipo de la columna: " + col1.getTipoDeDato());
        System.out.println("Elemenentos de la columna: " + col1.getCeldas());
        System.out.println("Etiqueta de la columna: " + col1.getEtiqueta());
        

        Celda<String> c1 = new Celda<>("Pepe");
        Celda<String> c2 = new Celda<>();



        col1.agregarCelda(c1);
        col1.agregarCelda(c2);

        System.out.println("Agrego 2 celdas");
        System.out.println("Tipo de la columna: " + col1.getTipoDeDato());
        System.out.println("Elemenentos de la columna: " + col1.getCeldas());
        System.out.println("Etiqueta de la columna: " + col1.getEtiqueta());

        System.out.println("Es NA la celda c2? " + c2.esNA());
        System.out.println("La celda c2 pasa de NA a \"Ted\"");
        c2.cambiarValor("Ted");
        System.out.println("Elemenentos de la columna: " + col1.getCeldas());
        System.out.println("La celda c1 pasa de NA a \"Ted\"");
        c1.volverNA();
        System.out.println("Elemenentos de la columna: " + col1.getCeldas());
        

        System.out.println("Columna de boolean");
        Etiqueta e2 = new EtiquetaNumerica(10);
        Columna<Boolean> col2 = new Columna<>(e2, Boolean.class);
        System.out.println("Tipo de la columna: " + col2.getTipoDeDato());
        System.out.println("Elemenentos de la columna: " + col2.getCeldas());
        System.out.println("Etiqueta de la columna: " + col2.getEtiqueta());

        // tira excepción c2 es tipo String
        //col2.agregarCelda(c2);

        System.out.println("Elemenentos de la columna: " + col2.getCeldas());

        System.out.println("Se agregan 2 celdas");
        Celda<Boolean> c3 = new Celda<>(false);
        Celda<Boolean> c4 = new Celda<>(true);
        col2.agregarCelda(c4);
        col2.agregarCelda(c3);
        System.out.println("Elemenentos de la columna: " + col2.getCeldas());

        System.out.println("La celda c3 se vuelve NA");
        c3.volverNA();
        System.out.println("Elemenentos de la columna: " + col2.getCeldas());


        System.out.println("Columna númerica");
        Etiqueta e3 = new EtiquetaCadena("Sueldo");
        Columna <Float> col3 = new Columna<>(e3, Float.class);

        System.out.println("Tipo de la columna: " + col3.getTipoDeDato());
        System.out.println("Elemenentos de la columna: " + col3.getCeldas());
        System.out.println("Etiqueta de la columna: " + col3.getEtiqueta());

        Celda<Float> c5 = new Celda<>(20000f);
        Celda<Float> c6 = new Celda<>();
        Celda<Float> c7 = new Celda<>(6666.558f);

        System.out.println("Se agregan 3 celdas a la columna");
        col3.agregarCelda(c7);
        col3.agregarCelda(c5);
        col3.agregarCelda(c6);
        
        System.out.println("Elemenentos de la columna: " + col3.getCeldas());
        
        





        
    }
}
