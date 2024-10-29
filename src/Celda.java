public class Celda<T> {
    private T valor;

    //Constructor 
    public Celda(T valor){
        this.valor = valor;
    }

    //Metodos
    public T getValor(){
        return valor;
    }

    public String getTipo() {
        return valor.getClass().getSimpleName();
    }

    public boolean esNA(){
        return valor==null;
    }

    public void cambiarValor(T valorNuevo){
        this.valor=valorNuevo;
    }



    //PROBAMOS
    public static void main(String[] args) {
        Celda<Integer> celda = new Celda(0);

        System.out.println("Valor: " + celda.getValor());
        System.out.println("Tipo de dato: " + celda.getTipo());
        System.out.println("Es NA?" + celda.esNA());
        celda.cambiarValor(4);
        System.out.println("Valor: " + celda.getValor());
        
        //String cadena;
        //Celda<String> celda2 = new Celda<String>(cadena);
        //System.out.println("V");


    }
}


