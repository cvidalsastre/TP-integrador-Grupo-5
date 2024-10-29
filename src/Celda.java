public class Celda<T> {
    private T valor;



    //Constructor 
    public Celda(T valor){
        this.valor = valor;
    }

    

    public Celda() {
        this.valor = null;
    }



    //Metodos
    public T getValor(){
        return valor;
    }

    public String getTipo() {
        if (valor == null){
            return "NA";
        }
        return valor.getClass().getSimpleName();
    }

    public boolean esNA(){
        return valor == null;
    }

    public void cambiarValor(T valorNuevo){
        this.valor = valorNuevo;
    }

    public void volverNA(){
        this.valor = null;
    }



    //PROBAMOS
    public static void main(String[] args) {
        Celda<Integer> celda = new Celda<>();

        System.out.println("Valor: " + celda.getValor());
        System.out.println("Tipo de dato: " + celda.getTipo());
        System.out.println("Es NA?" + celda.esNA());
        celda.cambiarValor(4);
        System.out.println("Valor: " + celda.getValor());
        
        //Celda<String> celda2 = new Celda<String>();

        //System.out.println("es Na?" + celda2.esNA());


    }
}


