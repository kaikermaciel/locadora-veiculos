package controller.exceptions;

public class CarroException extends Exception{
    private static final long serialVersionUID = 1L;

    public CarroException(){
        this("Exceção geral de veiculo");
    }
    public CarroException(String s){
        super(s);
    }
}
