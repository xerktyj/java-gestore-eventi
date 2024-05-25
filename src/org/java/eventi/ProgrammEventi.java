package org.java.eventi;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProgrammEventi {
	
	private String titolo;
	private List<Evento> eventi;

	public ProgrammEventi(String titolo) {
		this.titolo = titolo;
		this.eventi = new ArrayList<Evento>();		
	}
	
	
	public void addEvento(Evento evento) {
		eventi.add(evento);
	}
	
	public List getEventi(LocalDate data) {		
		List<Evento> eventiInData = new ArrayList<Evento>();
		for (Iterator it = eventi.iterator(); it.hasNext();) {
			Evento evento = (Evento) it.next();
			LocalDate dataEvento = evento.getData();
			if (data.equals(dataEvento)) {
				eventiInData.add(evento);
			}
		}	
		return eventiInData;
	}
	
	
	public int numEventi() {
		return eventi.size();
	}
	
	
	public void removeAllEventi() {
		eventi.clear();
	}
	
	
	public String eventiToString() {
		String s = "";
		for (Iterator it = eventi.iterator(); it.hasNext();) {
			Evento evento = (Evento) it.next();
			s +=  evento.toString() + "\n";
		}
		return s;
	}

}
