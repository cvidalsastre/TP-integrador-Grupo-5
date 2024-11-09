import java.util.ArrayList;
import java.util.List;

public class TestLeerCsv {
    
    public static void main(String ...args) throws Exception{
        Tabla tabla = Tabla.leerDesdeCsv("data/pokemon_combined.csv",true,",");
        List<Etiqueta> etiquetas = new ArrayList<Etiqueta>();
        etiquetas.add(tabla.getEtiquetasColumnas().get(0));

        tabla.ordenarPor(etiquetas, false);
        tabla.visualizar(10,10 ,10);
   
    }
}
