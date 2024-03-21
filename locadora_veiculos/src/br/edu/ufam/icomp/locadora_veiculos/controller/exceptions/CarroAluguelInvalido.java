package br.edu.ufam.icomp.locadora_veiculos.controller.exceptions;

public class CarroAluguelInvalido extends CarroException{
    
    private static final long serialVersionUID = 1L;

    public CarroAluguelInvalido(){
        this("Carro com data de aluguel invalido");
    }

    public CarroAluguelInvalido(String s){
        super(s);
    }
}
