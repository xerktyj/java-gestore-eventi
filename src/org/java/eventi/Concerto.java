package org.java.eventi;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Concerto extends Evento{
	
	private LocalTime ora;
	private double prezzo;
	
    //messo 5 argomenti nel costruttore meglio max 3
	public Concerto(LocalDate data, String titolo, int numPostiTotali, LocalTime ora, double prezzo) {
		super(data, titolo, numPostiTotali);
		this.ora = ora;
		setPrezzo(prezzo);
	}
	

	public LocalTime getOra() {
		return ora;
	}

	public void setOra(LocalTime ora) {
		this.ora = ora;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		if (prezzo > 0) {
			this.prezzo = prezzo;
		} else {
			System.out.println("prezzo negativo non va bene, risetta il prezzo");
		}
	}
	
	private String oraToString() {
		DateTimeFormatter formatter = DateTimeFormatter.
	            ofPattern("HH.mm");
		return ora.format(formatter);
	}
	
	private String prezzoToString() {		
		return String.format("%.2f", prezzo) + "€";
	}
	
	
	@Override
	public String toString() {	
		return dataToString() + " - "+ oraToString() +" - " + getTitolo() + " - " + prezzoToString();   
	}
	
	
	
	

	

}
