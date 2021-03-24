/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FXMLController {
	Dictionary dictionary;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="SceltaLingua"
    private ComboBox<String> SceltaLingua; // Value injected by FXMLLoader

    @FXML // fx:id="txtParole"
    private TextArea txtParole; // Value injected by FXMLLoader

    @FXML // fx:id="txtParoleErrate"
    private TextArea txtParoleErrate; // Value injected by FXMLLoader

    @FXML // fx:id="bottoneClear"
    private Button bottoneClear; // Value injected by FXMLLoader
    
    @FXML // fx:id="txtErrori"
    private Label txtErrori; // Value injected by FXMLLoader

    @FXML // fx:id="txtTempo"
    private Label txtTempo; // Value injected by FXMLLoader

    @FXML
    void btnClear(ActionEvent event) {
    	txtParoleErrate.clear();
    	txtParole.clear();
    	txtErrori.setText("Errori contenuti");
    	txtTempo.setText("Tempo necessario");

    }

    @FXML
    void btnSpellCheck(ActionEvent event) {
    	
    	
    	bottoneClear.setDisable(false);
    	
    	List<String> listaparoleinput = new ArrayList<String>();
    	List<String> listaparoleinputerrate = new ArrayList<String>();

    	String language = SceltaLingua.getValue();
    	if(language == null) {
    		txtParole.setText("Scegliere una lingua");
    		return ; 
    	}
    	if(!dictionary.loadDictionary(language)) {
    		txtParole.setText("Errore caricamento dizionario");
    		return;
    	} 
    	String paroleinput= txtParole.getText().toLowerCase();
    	if(paroleinput.isEmpty()) {
    		txtParole.setText("Inserire un testo");
    		return;
    	}
    	
    	
    	paroleinput = paroleinput.replaceAll("\n", " ");
		paroleinput = paroleinput.replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]]", "");    	
    	String parole[] = paroleinput.split(" ");
    	for(String s : parole) {
    		if(s!=null) listaparoleinput.add(s);}
    	
    	long start = System.nanoTime();
    	List<RichWord> parolecorrette = dictionary.SpellCheckTest(listaparoleinput);
    	
    	for(RichWord rw : parolecorrette ) {
    		if(rw.isCorretta()==false) {
    			listaparoleinputerrate.add(rw.getInput());
    		}
    	}
    	String paroleerrate="";
    	for(String st : listaparoleinputerrate) {
    		if(paroleerrate=="") paroleerrate+=st;
    		else paroleerrate+="\n"+st;
    	}
    	
    	
    	long end = System.nanoTime();
    	txtParoleErrate.setText(paroleerrate);
    	txtErrori.setText("La frase contiene "+ listaparoleinputerrate.size()+" errori.");
    	txtTempo.setText("Spell check avvenuto in "+ (end-start)+" ms");
    	
    	
    	

    }
    
    public void setModel(Dictionary d ) {
    	this.dictionary=d;
    	String language[] = {"English","Italiano"};
    	this.SceltaLingua.getItems().addAll(language);
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert SceltaLingua != null : "fx:id=\"SceltaLingua\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtParole != null : "fx:id=\"txtParole\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtParoleErrate != null : "fx:id=\"txtParoleErrate\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtErrori != null : "fx:id=\"txtErrori\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTempo != null : "fx:id=\"txtTempo\" was not injected: check your FXML file 'Scene.fxml'.";

    }
}



