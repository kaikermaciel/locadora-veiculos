package model.entidades;

public class Veiculo {
	private String categoria;
	private int maxPassageiros;
	private int tamBagageiro;
	private String tipoCambio;
	private boolean arCondicionado;
	private float mediaConsumo;
	private boolean airbag;
	private boolean freioABS;
	private boolean dvd;
	private float valorPorDia;

	// constructor class veiculo
	public Veiculo(String categoria, int maxPassageiros, int tamBagageiro, String tipoCambio, boolean arCondicionado, float mediaConsumo, boolean airbag, 
			boolean freioABS, boolean dvd, float valorPorDia) {
		this.categoria = categoria;
		this.maxPassageiros = maxPassageiros;
		this.tamBagageiro = tamBagageiro;
		this.tipoCambio = tipoCambio;
		this.arCondicionado = arCondicionado;
		this.mediaConsumo = mediaConsumo;
		this.airbag = airbag;
		this.freioABS = freioABS;
		this.dvd = dvd;
		this.valorPorDia = valorPorDia;
	}

	public Veiculo() {
		this.categoria = "";
    	this.maxPassageiros = 0;
    	this.tamBagageiro = 0;
    	this.tipoCambio = "";
    	this.arCondicionado = false;
    	this.mediaConsumo = 0.0f;
    	this.airbag = false;
    	this.freioABS = false;
    	this.dvd = false;
    	this.valorPorDia = 0.0f;
	}

	// get setter categoria
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	// get e setter maxPassageiro
	public int getmaxPassageiros() {
		return maxPassageiros;
	}
	public void setmaxPassageiros(int maxPassageiros) {
		this.maxPassageiros = maxPassageiros;
	}

	// get e setter tamBagageiro
	public int gettamBagageiro() {
		return tamBagageiro;
	}
	public void settamBagageiro(int tamBagageiro) {
		this.tamBagageiro = tamBagageiro;
	}

	// get setter tipoCambio
	public String gettipoCambio() {
		return tipoCambio;
	}
	public void settipoCambio(String tipoCambio) {
		this.tipoCambio = tipoCambio;
	}

	// get setter arCondicionado
	public boolean getarCondicionado() {
		return arCondicionado;
	}
	public void setarCondicionado(boolean arCondicionado) {
		this.arCondicionado = arCondicionado;
	}

	//get setter mediaConsumo
	public float getmediaConsumo() {
		return mediaConsumo;
	}
	public void setmediaConsumo(float mediaConsumo) {
		this.mediaConsumo = mediaConsumo;
	}

	//get setter acessorios:
		// airbag
	public boolean getairbag() {
		return airbag;
	}
	public void setairbag(boolean airbag) {
		this.airbag = airbag;
	}
	
		// freioABS
	public boolean getfreioABS() {
		return freioABS;
	}
	public void setfreioABS(boolean freioABS) {
		this.freioABS = freioABS;
	}
		// dvd
	public boolean getdvd() {
		return dvd;
	}
	public void setdvd(boolean dvd) {
		this.dvd = dvd;
	}

	//get setter valorPorDia
	public float getvalorPorDia() {
		return valorPorDia;
	}
	public void setvalorPorDia(float valorPorDia) {
		this.valorPorDia = valorPorDia;
	}


}
