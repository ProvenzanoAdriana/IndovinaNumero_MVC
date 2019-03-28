package it.polito.tdp.IndovinaNumero;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class NumeroController {
	
	private final int NMAX=100; 
	private final int TMAX=8; 
	
	private int segreto; 
	private int tentativiFatti; 
	private boolean inGioco=false; 

    @FXML
    private HBox boxControlloPartita;

    @FXML
    private TextField txtRimasti;
    //numero tentativi rimasti ancora da provare
    @FXML
    private HBox boxControlloTentativi;

    @FXML
    private TextField txtTentativo;
    //tentativo inserito dall'utente

    @FXML
    private TextArea txtMessaggi;

    @FXML
    
    void handleNuovaPartita(ActionEvent event) {
    	//gestisce l'inizio di una nuova partita
    	
    	//LOGICA DEL GIOCO
    	this.segreto=(int)(Math.random()*NMAX)+1; 	//Math.random() genera un numero reale tra 0 e 1, otteniamo un numero
    												//intero tra 1 e NMAX
    	this.tentativiFatti=0; 
    	this.inGioco=true; 
    	
    	//GESTIONE INTERFACCIA
    	boxControlloPartita.setDisable(true); 		//disabilitata
    	boxControlloTentativi.setDisable(false);	//abilitata
    	txtMessaggi.clear();
    	txtRimasti.setText(Integer.toString(this.TMAX));
    }

    @FXML
    void handleProvaTentativo(ActionEvent event) {
    	
    	//LEGGI IL VALORE DEL TENTATIVO
    	String ts=txtTentativo.getText();
    
    	//CONTROLLA SE E' VALIDO
    	int tentativo; 
    	try{
    		tentativo=Integer.parseInt(ts); 
    	} catch (NumberFormatException e) {
    		//la stringa inserita non è un numero valido
    		txtMessaggi.appendText("Non è un numero valido\n");
    		return; 
    	}
    	tentativiFatti++; 
    	
    	//CONTROLLA SE HA INDOVINATO -> fine partita
    	if(tentativo==segreto) {
    		txtMessaggi.appendText("Complimenti, hai indovinato in "+tentativiFatti+" tentativi\n");
    		
    		boxControlloPartita.setDisable(false); 		//abilitata
        	boxControlloTentativi.setDisable(true);		//disabilitata
        	this.inGioco=false; 
        	return; 
    	}
    	
    	//VERIFICA SE HAI ESAURITO I TENTATIVI -> fine partita
    	if(tentativiFatti==TMAX) {
    		txtMessaggi.appendText("Hai perso, il numero segreto era "+segreto+" \n");
    		
    		boxControlloPartita.setDisable(false); 		//abilitata
        	boxControlloTentativi.setDisable(true);		//disabilitata
        	this.inGioco=false; 
        	return; 
    		
    	}
    	
    	//INFORMA SE ERA TROPPO ALTO/BASSO -> stampa messaggio
    	if(tentativo<segreto) {
    		txtMessaggi.appendText("Tentativo troppo basso\n");
    	} else {
    		txtMessaggi.appendText("Tentativo troppo alto\n");
    	}
    	
    	//AGGIORNA INTERFACCIO CON N. TENTATIVI RIMASTI
    	txtRimasti.setText(Integer.toString(TMAX-tentativiFatti));
    	
    }

}
