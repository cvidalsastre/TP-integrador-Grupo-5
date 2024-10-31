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
        if (esNA()){
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

}