package org.java.eventi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Evento {
	
	private String titolo;
	private LocalDate data;
	private int numPostiTotali;
	private int numPostiPrenotati;
	
	
	public Evento(LocalDate data, String titolo, int numPostiTotali) {
		setData(data);
		setPosti(numPostiTotali);
		this.titolo = titolo;
		this.numPostiPrenotati = 0;
	}
	
	private void setPosti(int numPostiTotali) {
		try {
			if (numPostiTotali < 0)
				throw new IllegalArgumentException("numero posti totali negativo");
			this.numPostiTotali = numPostiTotali;
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void setData(LocalDate data) {
		LocalDate dataAttuale = LocalDate.now();
		try {
			if (dataAttuale.isAfter(data))
				throw new IllegalArgumentException("la data è prima della data attuale");
			this.data = data;
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public String getTitolo() {
		return titolo;
	}
	

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	
    
	public LocalDate getData() {
		return data;
	}		

	public int getNumPostiTotali() {
		return numPostiTotali;
	}
	

	public int getNumPostiPrenotati() {
		return numPostiPrenotati;
	}
	
	

	public void prenota(int numPrenotazioni) {
		if (!isDataNull() || eventoNonScaduto()) {
			int numPostiDisponibili = numPostiTotali - numPostiPrenotati;
			boolean prenotabile = numPrenotazioni <= numPostiDisponibili;
			if (prenotabile && numPrenotazioni > 0) {
				numPostiPrenotati += numPrenotazioni;
				System.out.println(toString() + ", hai prenotato: " + numPrenotazioni + " posti");
			} else if (numPrenotazioni <= 0) {
				System.out.println("ci dispiace ma non stai prenotando posti");
			} else {
				System.out.println("ci dispiace rimangono: " + numPostiDisponibili + " posti disponibili");
			}
		}
	}
	
	
	private boolean eventoNonScaduto() {
		LocalDate dataAttuale = LocalDate.now();
		if (dataAttuale.isAfter(data)) {
			System.out.println("evento già passata mi spiace");
			return false;
		}
		return true;
	}
	
	private boolean isDataNull() {
		if(data == null) {
			System.out.println("la data non è inserita per questo evento");
			return true;
		}
		return false;
	}
	
	
	public void disdici(int numPostiDisdire) {
		if (!isDataNull() || eventoNonScaduto()) {
			boolean isPostoDisdibile = numPostiPrenotati >= numPostiDisdire;
			if (isPostoDisdibile && numPostiDisdire > 0) {
				numPostiPrenotati -= numPostiDisdire;
				System.out.println(toString() + ", hai disdetto: : " + numPostiDisdire + " posti");
			} else if(numPostiDisdire <= 0) {
				System.out.println("ci dispiace ma non stai disdendo posti");
			}
			else {
				System.out.println("mi spiace stai disdicendo più posti di quelli prenotati");
			}
		}
	}
	
	protected String dataToString() {
		String s = "";
		if(!isDataNull()) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
			s = data.format(formatter);
		}
		return s;
	}
	
	@Override
	public String toString() {			
		return dataToString() + " - " + titolo;
	}
			
	
	


}