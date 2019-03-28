package it.polito.tdp.IndovinaNumero;

import it.polito.tdp.numero.model.NumeroModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class NumeroController {
	
	private NumeroModel model; 

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
    	 	
    	//GESTIONE INTERFACCIA
    	boxControlloPartita.setDisable(true); 		//disabilitata
    	boxControlloTentativi.setDisable(false);	//abilitata
    	txtMessaggi.clear();
    	txtTentativo.clear();
    	txtRimasti.setText(Integer.toString(model.getTMAX()));
    	
    	//Comunico al modello di iniziare una nuova partita
    	model.newGame();
    }

    @FXML
    void handleProvaTentativo(ActionEvent event) {
    	
    	//LEGGI IL VALORE DEL TENTATIVO
    	String ts=txtTentativo.getText();
    
    	//CONTROLLA SE E' VALIDO IL TIPO DI DATO (il modello richiede il tipo di parametri giusto)
    	int tentativo; 
    	try{
    		tentativo=Integer.parseInt(ts); 
    	} catch (NumberFormatException e) {
    		//la stringa inserita non è un numero valido
    		txtMessaggi.appendText("Non è un numero valido\n");
    		return; 
    	}
    	
    	if(!model.tentativoValido(tentativo)) {
    		txtMessaggi.appendText("Range non valido\n");
    		return; 
    	}
    	
    	int risultato=model.tentativo(tentativo); 
    	if(risultato==0) {
    		txtMessaggi.appendText("Complimenti, hai indovinato in "+model.getTentativiFatti()+" tentativi\n");
    		
    		boxControlloPartita.setDisable(false); 		//abilitata
        	boxControlloTentativi.setDisable(true);		//disabilitata
    	}else if(risultato<0) {
    		txtMessaggi.appendText("Tentativo troppo basso\n");
    	} else {
    		txtMessaggi.appendText("Tentativo troppo alto\n");
    	}
    	
    	
    	//AGGIORNA INTERFACCIO CON N. TENTATIVI RIMASTI
    	txtRimasti.setText(Integer.toString(model.getTMAX()-model.getTentativiFatti()));
    	
    	if(!model.isInGioco()) {
    		//la parita è finita!
    		if(risultato!=0) {
    			txtMessaggi.appendText("Hai perso!");
    			txtMessaggi.appendText(String.format("\nIl numero segreto era %d", model.getSegreto()));
    			boxControlloPartita.setDisable(false); 		//abilitata
            	boxControlloTentativi.setDisable(true);		//disabilitata
    		}
    	}
    }
    
    @FXML
    void initialize() {
        assert boxControlloPartita != null : "fx:id=\"boxControlloPartita\" was not injected: check your FXML file 'Numero.fxml'.";
        assert txtRimasti != null : "fx:id=\"txtRimasti\" was not injected: check your FXML file 'Numero.fxml'.";
        assert boxControlloTentativi != null : "fx:id=\"boxControlloTentativi\" was not injected: check your FXML file 'Numero.fxml'.";
        assert txtTentativo != null : "fx:id=\"txtTentativo\" was not injected: check your FXML file 'Numero.fxml'.";
        assert txtMessaggi != null : "fx:id=\"txtMessaggi\" was not injected: check your FXML file 'Numero.fxml'.";

    }

	public void setModel(NumeroModel model) {
		this.model = model;
	}
    
    

}
