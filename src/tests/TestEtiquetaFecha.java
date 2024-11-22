package kapibara;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TestEtiquetaFecha {
    public static void main(String[] args) {
        // Crear etiquetas usando diferentes constructores
        EtiquetaFecha etiqueta1 = new EtiquetaFecha(LocalDate.of(2024, 11, 22)); // Usando LocalDate
        EtiquetaFecha etiqueta2 = new EtiquetaFecha("2024-12-31"); // Usando String en formato ISO

        // Imprimir valores y representaciones
        System.out.println("Etiqueta 1 (LocalDate): " + etiqueta1.getValor()); // Muestra: 2024-11-22
        System.out.println("Etiqueta 1 (toString): " + etiqueta1.toString()); // Muestra: "2024-11-22"

        System.out.println("Etiqueta 2 (String): " + etiqueta2.getValor()); // Muestra: 2024-12-31
        System.out.println("Etiqueta 2 (toString): " + etiqueta2.toString()); // Muestra: "2024-12-31"

        // Cambiar el formato de salida
        DateTimeFormatter formatoPersonalizado = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Etiqueta 1 (formato personalizado): " + etiqueta1.toString(formatoPersonalizado)); // Muestra: 22/11/2024

        // Comparar etiquetas
        EtiquetaFecha etiqueta3 = new EtiquetaFecha(LocalDate.of(2024, 11, 22));
        System.out.println("¿Etiqueta 1 y 3 son iguales? " + etiqueta1.equals(etiqueta3)); // Dependerá de cómo definas equals en Etiqueta
    }
}
