package tests;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import enums.OperacionEstadistica;
import kapibara.Etiqueta;
import kapibara.EtiquetaCadena;
import kapibara.Tabla;

public class TestLeerCsv {
    
    public static void main(String ...args) throws Exception{
       
            
        
        
        Tabla tabla = Tabla.leerDesdeCsv("data\\pokemon_combined.csv",true,",");
        List<Etiqueta> etiquetas = new ArrayList<Etiqueta>();
        etiquetas.add(tabla.getEtiquetasColumnas().get(0));

        tabla.ordenarPor(tabla.getEtiquetasColumnas().get(0), true);
        
        Tabla pokemonPesadoAcero = tabla
        .filtrar(new EtiquetaCadena("Weight"),100,">")
        .filtrar(new EtiquetaCadena("Type"), "Steel","=");
        pokemonPesadoAcero.ordenarPor(new EtiquetaCadena("Weight"), false);
        System.out.println("Los pokemons de tipo acero mas pesados con peso mayor a 100 kilos son");
        pokemonPesadoAcero.visualizar(100, 20,10);


        Tabla pokemonPesadoPlanta = tabla
        .filtrar(new EtiquetaCadena("Weight"),100,">")
        .filtrar(new EtiquetaCadena("Type"), "Rock","=")
        .ordenarPor(new EtiquetaCadena("Weight"), false);
        System.out.println("Los pokemons de tipo Roca mas pesados con peso mayor a 100 kilos son");
        pokemonPesadoPlanta.visualizar(100, 20,10);
        
        pokemonPesadoPlanta.guardarTabla("temp/pokemonsPlanta.csv");



        Tabla tablaSuperHeroes = Tabla.leerDesdeCsv("data/superhero_battles 10k.csv",true,",");
        // tablaSuperHeroes.visualizar(10,20,7);
        tablaSuperHeroes
        // .filtrar(new EtiquetaCadena("Sup_1_Character"), "Superman", "=")
        .agruparYSumarizar(Arrays.asList(new EtiquetaCadena("Sup_1_Universe")),OperacionEstadistica.MEDIA)
        .ordenarPor(new EtiquetaCadena("Superhero_1_win"),false)
        .visualizar(100,100 , 10);
        // tablaSuperHeroes.agruparYSumarizar(null, OperacionEstadistica.CUENTA)
    }
}
