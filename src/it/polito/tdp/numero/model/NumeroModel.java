package it.polito.tdp.numero.model;

import java.security.InvalidParameterException;

public class NumeroModel {
	
	private final int NMAX=100; 
	private final int TMAX=8; 
	
	private int segreto; 
	private int tentativiFatti; 
	private boolean inGioco=false; 
	
	public NumeroModel() {
		this.inGioco=false; 
	}
	
	/**
	 * Avvia nuova partita 
	 */
	public void newGame() {
    	this.segreto=(int)(Math.random()*NMAX)+1; 	
    	this.tentativiFatti=0; 
    	this.inGioco=true; 
	}
	
	/**
	 * Metodo per effettuare un tentativo
	 * @param t il tentativo
	 * @return 1 se il tentativo è troppo alto, 0 se ha indovinato, -1 se è troppo basso 
	 */
	public int tentativo(int t) {
		
		//controllare se la partita è in corso
		if(!inGioco) {
			throw new IllegalStateException("La partita è terminata"); 
		}
		
		//controllare se l'input è nel range corretto
		if(!tentativoValido(t)) {
			throw new InvalidParameterException(String.format("Devi inserire un numero tra %d e %d", 1, NMAX)); 
		}
		
		//gestisci tentativo
		this.tentativiFatti++; 
		if(this.tentativiFatti==TMAX) {
			//la partita è finita perchè ho esaurito i tentativi
			this.inGioco=false; }
		
		if(this.segreto==t) { 	//ho indovinato
			this.inGioco=false; 
			return 0; }
		if(t>this.segreto) {	//troppo alto
			return 1; }
		else
			return -1; 			//troppo basso
	}
	
	public boolean tentativoValido(int t) {
		if(t<1 || t>NMAX) {
			return false; }
		else {
			return true; }
		}

	public boolean isInGioco() {
		return inGioco;
	}

	public int getSegreto() {
		return segreto;
	}

	public int getTentativiFatti() {
		return tentativiFatti;
	}

	public int getTMAX() {
		return TMAX;
	}
	
	
	}
	
