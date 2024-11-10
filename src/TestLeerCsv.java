import java.util.ArrayList;
import java.util.List;

public class TestLeerCsv {
    
    public static void main(String ...args) throws Exception{
        Tabla tabla = Tabla.leerDesdeCsv("data/pokemon_combined.csv",true,",");
        List<Etiqueta> etiquetas = new ArrayList<Etiqueta>();
        etiquetas.add(tabla.getEtiquetasColumnas().get(0));
        etiquetas.add(tabla.getEtiquetasColumnas().get(1));

        tabla.ordenarPor(etiquetas, true);
        
        Tabla pokemonPesadoAcero = tabla
        .filtrar(new EtiquetaCadena("Weight"),100,">")
        .filtrar(new EtiquetaCadena("Type"), "Steel","=");
        List<Etiqueta> ordenarEtiqueta =  new ArrayList<Etiqueta>();
        ordenarEtiqueta.add(new EtiquetaCadena("Weight"));
        pokemonPesadoAcero.ordenarPor(ordenarEtiqueta, false);
        System.out.println("Los 5 pokemons de tipo acero mas pesados con peso mayor a 100 kilos");
        pokemonPesadoAcero.visualizar(100, 20,10);


        Tabla pokemonPesadoPlanta = tabla
        .filtrar(new EtiquetaCadena("Weight"),100,">")
        .filtrar(new EtiquetaCadena("Type"), "Grass","=");
        List<Etiqueta> ordenarEtiquetaPlanta =  new ArrayList<Etiqueta>();
        ordenarEtiquetaPlanta.add(new EtiquetaCadena("Weight"));
        pokemonPesadoPlanta.ordenarPor(ordenarEtiqueta, false);
        System.out.println("Los 5 pokemons de tipo Planta mas pesados con peso mayor a 100 kilos");
        pokemonPesadoPlanta.visualizar(100, 20,10);
        
        pokemonPesadoPlanta.guardarTabla("temp/pokemonsPlanta.csv");
   
    }
}
