package controller.exceptions;

public class ClienteIdadeInvalidaException extends ClienteException{
    private static final long serialVersionUID = 1L;

    public ClienteIdadeInvalidaException(){
        this("Idade Inválida para alugar um veiculo");
    }
    public ClienteIdadeInvalidaException(String s){
        super(s);
    }
}
