package br.edu.ufam.icomp.locadora_veiculos.model.entidades;
import java.sql.Date;

import br.edu.ufam.icomp.locadora_veiculos.controller.exceptions.ClienteIdadeInvalidaException;

public class Cliente {
    private Date dataRetirada;
    private String localRetirada;
    private Date dataDevolucao;
    private String localDevolucao;
    private int idadeCondutor;
    private int veiculo_id;
    private boolean GPS;
    private boolean assentoCrianca;
    private boolean seguroCompleto;

    public Cliente(java.sql.Date dataRetirada2, String localRetirada, java.sql.Date dataDevolucao2, String localDevolucao, int idadeCondutor, int veiculo_id, boolean GPS, boolean assentoCrianca, boolean seguroCompleto) throws ClienteIdadeInvalidaException{
        this.dataRetirada = (Date) dataRetirada2;
        this.localRetirada = localRetirada;
        this.dataDevolucao = (Date) dataDevolucao2;
        this.localDevolucao = localDevolucao;
        this.idadeCondutor = idadeCondutor;
        this.veiculo_id = veiculo_id;
        this.GPS = GPS;
        this.assentoCrianca = assentoCrianca;
        this.seguroCompleto = seguroCompleto;

        if (this.idadeCondutor < 18){
            throw new ClienteIdadeInvalidaException();
        }
        
    }

    public Cliente(){
        this.dataRetirada = null;
        this.localRetirada = "";
        this.dataDevolucao = null;
        this.localDevolucao = "";
        this.idadeCondutor = 0;
        this.veiculo_id = 0;
        this.GPS = false;
        this.assentoCrianca = false;
        this.seguroCompleto = false;
    }

    public Date getDataRetirada(){
        return dataRetirada;
    }
    public void setDataRetirada(Date dataRetirada){
        this.dataRetirada = dataRetirada;
    }

    public String getLocalRetirada(){
        return localRetirada;
    }
    public void setLocalRetirada(String localRetirada){
        this.localRetirada = localRetirada;
    }

    public Date getDataDevolucao(){
        return dataDevolucao;
    }
    public void setDataDevolucao(Date dataDevolucao){
        this.dataDevolucao = dataDevolucao;
    }

    public String getLocalDevolucao(){
        return localDevolucao;
    }
    public void setLocalDevolucao(String localDevolucao){
        this.localDevolucao = localDevolucao;
    }

    public int getIdadeCondutor(){
        return idadeCondutor;
    }
    public void setIdadeCondutor(int idadeCondutor){
        this.idadeCondutor = idadeCondutor;
    }

    public int getVeiculoId(){
        return veiculo_id;
    }
    public void setVeiculoId(int veiculo_id){
        this.veiculo_id = veiculo_id;
    }

    public boolean getGPS(){
        return GPS;
    }
    public void setGPS(boolean GPS){
        this.GPS = GPS;
    }

    public boolean getAssentoCrianca(){
        return assentoCrianca;
    }
    public void setAssentoCrianca(boolean assentoCrianca){
        this.assentoCrianca = assentoCrianca;
    }
    
    public boolean getSeguroCompleto(){
        return seguroCompleto;
    }
    public void setSeguroCompleto(boolean seguroCompleto){
        this.seguroCompleto = seguroCompleto;
    }
}
