package controller.exceptions;

public class ClienteException extends Exception{
    private static final long serialVersionUID = 1L;

    public ClienteException(){
        this("Excecão geral do cliente");
    }
    public ClienteException(String s){
        super(s);
    }
}
