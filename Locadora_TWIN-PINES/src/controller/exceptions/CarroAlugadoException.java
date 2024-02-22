package controller.exceptions;

public class CarroAlugadoException extends CarroException{
    
    private static final long serialVersionUID = 1L;

    public CarroAlugadoException(){
        this("Carro já se encontra alugado");
    }

    public CarroAlugadoException(String s){
        super(s);
    }
}
