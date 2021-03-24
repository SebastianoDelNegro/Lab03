package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Dictionary {
	List<String> paroleDizionario = new ArrayList<String>();
	
	public boolean loadDictionary(String language) {
		//List<String> paroleDizionario = new ArrayList<String>();
		
		if(language.contains("English")==true) {
			try {
				FileReader fr = new FileReader("src/main/resources/English.txt");
				BufferedReader br = new BufferedReader(fr);
				String word;
				while((word = br.readLine())!=null) {
					paroleDizionario.add(word);
				}
				
			}catch(IOException e) {
				System.out.println("Errore nella lettura del file");
			}
			return true;
		}
		if(language.contains("Italiano")==true) {
			try {
				FileReader fr = new FileReader("src/main/resources/Italian.txt");
				BufferedReader br = new BufferedReader(fr);
				String word;
				while((word = br.readLine())!=null) {
					paroleDizionario.add(word);
				}
				
			}catch(IOException e) {
				System.out.println("Errore nella lettura del file");
			}
			return true;
		}
		return false;
		}
	
	public List<RichWord> SpellCheckTest(List<String> inputtextlist){
		List<RichWord> listaRichWord = new ArrayList<RichWord>();
		int c=0;
		for(String s : inputtextlist) {
			for(String st : paroleDizionario) {
				if(s.equals(st)) {
					RichWord rw = new RichWord(s);
					rw.setCorretta(true);
					listaRichWord.add(rw);
					c++;
				}
				
			}
			if(c==0) {
				RichWord rw = new RichWord(s);
				rw.setCorretta(false);
				listaRichWord.add(rw);
			}
		}
		return listaRichWord;
	}

}
