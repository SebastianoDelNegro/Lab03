package it.polito.tdp.spellchecker.model;

public class RichWord {
	String input;
	boolean corretta;
	
	public RichWord(String input) {
		this.input=input;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public boolean isCorretta() {
		return corretta;
	}

	public void setCorretta(boolean corretta) {
		this.corretta = corretta;
	}
	
	

}
