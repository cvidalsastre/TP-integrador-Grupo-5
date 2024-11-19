import java.io.File;
import java.util.ArrayList;
import java.util.List;
import enums.OperacionEstadistica;
import kapibara.*;
public class Demo1 {

 public static void main(String ...args) {
         
    
    
        File datasetTitanic = new File("datasetDemo/titanic.csv");
        // realizamos una exploración de este famoso dataset
        Tabla tabla_titanic = Tabla.leerDesdeCsv(datasetTitanic.getAbsolutePath(),true,"#");
        
        // aca realizamos mutaciones al objeto tabla
        // tabla_titanic.eliminarColumna(new EtiquetaCadena("SibSp"));
       List<Etiqueta> etiquetasColumnas = new ArrayList<Etiqueta>(); 
       etiquetasColumnas.add(new EtiquetaCadena("Fare"));
       etiquetasColumnas.add(new EtiquetaCadena("SibSp"));
       etiquetasColumnas.add(new EtiquetaCadena("Parch"));
//        etiquetasColumnas.add(new EtiquetaCadena("PassengerId"));



        for(Etiqueta etiqueta : etiquetasColumnas){
                tabla_titanic.eliminarColumna(etiqueta);
        }

        
   

        
        List<Etiqueta> etiquetaAgrupar = new ArrayList<Etiqueta>();
 

        System.out.println("");
        System.out.println("Mostramos la cantidad de pasajeros para cada clase y separados sexo");
        etiquetaAgrupar.add(new EtiquetaCadena("Pclass"));
        etiquetaAgrupar.add(new EtiquetaCadena("Sex"));    
        Tabla cantidadPasajerosPorClase = tabla_titanic
        .agruparYSumarizar(etiquetaAgrupar,OperacionEstadistica.CUENTA)
        .ordenarPor(new EtiquetaCadena("Pclass"), false);
        System.out.println("");
        cantidadPasajerosPorClase.visualizar(5,1,4);


        System.out.println("");
        System.out.println("Mostramos la cantidad de supervivientes para cada clase separados por sexo");
        Tabla cantidadSupervivientesPorClase = tabla_titanic
        .filtrar(new EtiquetaCadena("Survived"),1 ,"=")
        .agruparYSumarizar(etiquetaAgrupar,OperacionEstadistica.CUENTA);
        System.out.println("");
        cantidadSupervivientesPorClase.visualizar(5,5,4);

        System.out.println("Mostramos la media de tasa de supervivencia y edad para cada clase");
        Tabla mediaSupervivenciaPorClase = tabla_titanic
        .agruparYSumarizar(etiquetaAgrupar,OperacionEstadistica.MEDIA);
        mediaSupervivenciaPorClase.eliminarColumna(new EtiquetaCadena("PassengerId"));
        System.out.println("");
        mediaSupervivenciaPorClase.visualizar(5,100,4);
 


        




}}
