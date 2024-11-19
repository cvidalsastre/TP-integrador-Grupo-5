import java.io.File;
import java.util.Arrays;

import enums.OperacionEstadistica;
import kapibara.*;
public class Demo2 {

 public static void main(String ...args) {
         
       // mostramos la tabla con algunas filas para saber que forma tiene
       Tabla tablaSuperHeroes = Tabla.leerDesdeCsv(new File("datasetDemo/marvelVsDc.csv").getAbsolutePath(),true,",");
        
        System.out.println("Mostramos la tabla");
        System.out.println("");
        tablaSuperHeroes.visualizar(5,100,10);

        System.out.println("");
        System.out.println("Los 10 Superheroes con mejor ratio de aprobación del publico");
        System.out.println("");

        tablaSuperHeroes
        .filtrar(new EtiquetaCadena("Superhero_1_win"), 51.0, ">") 
        // .filtramos a los superheroes que tengan mas del 50% de aprobación frente a otro superheroe, osea que ganaron el duelo
        .agruparYSumarizar(Arrays.asList(new EtiquetaCadena("Sup_1_Character")),OperacionEstadistica.CUENTA)
        // agrupamos por superheroe y contamos cuantas filas quedaron por cada superheroe que cumplan con la condición de filtrado 
        .ordenarPor(new EtiquetaCadena("Superhero_1_win"),false)
        // ordenamos la columna por la cantidad de enfrentamientos con ratio de victoria mayor al 50%
        .visualizar(10,1,10);
        // Con esto conseguimos una lista con los 10 superheroes o personajes mas votados por el publico

        //  aca filtramos todos los duelos en donde batman pierde y lo guardamos en una tabla
        tablaSuperHeroes
        .filtrar(new EtiquetaCadena("Sup_1_Character"), "Batman", "=")
        .filtrar(new EtiquetaCadena("Superhero_2_win"), 51.0, ">")
        .guardarTabla("temp/pierdeBatman.csv");





} 
}