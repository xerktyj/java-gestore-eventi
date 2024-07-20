package org.java.eventi;

import java.time.LocalDate;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
				
		String titolo;
		int anno;
		int mese;
		int giorno;		
		LocalDate data = LocalDate.now(); 
		int numPostiEvento;
		boolean isPrenotazione;
		int numPostiPrenotare;
		boolean isDisdetta;
		int numPostiDisdetta;
		int postiDisponibili;
		boolean isDayOfMonth ; 
		boolean isMonthOfYear ; 
		boolean isYearOfCentury;
		boolean isEccezione;

		Scanner in = new Scanner(System.in);

		System.out.println("inserisci nuovo evento");
		System.out.println("dimmi il titolo dell'evento");
		titolo = in.nextLine();

		
		do {
			isEccezione = false;
			try {
				System.out.println("inserisci la data partendo dall'anno yyyy");
				while (!in.hasNextInt()) {
					String parola = in.next();
					System.out.println(parola + " non usare lettere");
				}
				anno = in.nextInt();
				isYearOfCentury = anno >= 2000;
				if(!isYearOfCentury)
					throw new Exception ("Eccezione: anno prima del 21 secolo");			
				System.out.println("scrivi il mese mm");
				while (!in.hasNextInt()) {
					System.out.println("non usare lettere");
				}
				mese = in.nextInt();
				isMonthOfYear = mese > 0 && mese <= 12;
				if(!isMonthOfYear)
					throw new Exception ("Eccezione: i mesi dell'anno sono 12");							
				System.out.println("scrivi il giorno del mese");
				while (!in.hasNextInt()) {
					String parola = in.next();
					System.out.println("non usare lettere");
				}
				giorno = in.nextInt();
				isDayOfMonth = giorno > 0 && giorno <= 31;
				if(!isDayOfMonth) 
					throw new Exception ("Eccezione: i giorni di un mese sono 31");
				//metto qua cosi non mi carica la data se va un'eccezione e non mi da errore
				data = LocalDate.of(anno, mese, giorno);
				System.out.println("vuoi impostare un altra data, true o false ?");
				isEccezione = in.nextBoolean();
				
				}catch(Exception parametriData){
					System.out.println(parametriData.getMessage());
					System.out.println("riscrivi la data");
					isEccezione = true;
				}		
			
								
		} while (isEccezione);
		in.nextLine();
		
		
		System.out.println("scrivi numero posti totali evento");
		while (!in.hasNextInt()) {
			String parola = in.next();
			System.out.println(parola + " non è un numero, scrivi un numero");
		}
		numPostiEvento = in.nextInt();
		
		
		
		Evento evento = new Evento(data, titolo, numPostiEvento);
		System.out.println(evento);
		System.out.println("numero posti totali: " + evento.getNumPostiTotali());
		

		try {

			if (data.equals(LocalDate.now()) || data.isBefore(LocalDate.now()) || numPostiEvento <= 0)
				throw new Exception("Non puoi prenotare questo evento");

			// prenotazione
			System.out.println("vuoi prenotare per l'evento, true o false?");
			isPrenotazione = in.nextBoolean();

			while (isPrenotazione) {
				try {

					System.out.println("quanti posti vuoi prenotare");
					numPostiPrenotare = in.nextInt();

					if (evento.getNumPostiTotali() == evento.getNumPostiPrenotati())
						throw new Exception("L'evento è fully booked");

					evento.prenota(numPostiPrenotare);
					postiDisponibili = evento.getNumPostiTotali() - evento.getNumPostiPrenotati();
					System.out.println("numero posti prenotati: " + evento.getNumPostiPrenotati()
							+ ", Posti disponibili: " + postiDisponibili);

				} catch (Exception prenota) {
					System.out.println(prenota.getMessage());
					break;
				}

				System.out.println("vuoi prenotare ancora, true o false?");
				isPrenotazione = in.nextBoolean();
			}

			// disdetta
			System.out.println("devi disdire una prenotazione true o false ?");
			isDisdetta = in.nextBoolean();

			while (isDisdetta) {
				try {
					System.out.println("quanti posti devi disdire ?");
					numPostiDisdetta = in.nextInt();

					if (evento.getNumPostiPrenotati() == 0)
						throw new Exception("non puoi disdire nulla:  \n" + " numero posti prenotati: "
								+ evento.getNumPostiPrenotati() + ", numero posti da disdire: " + numPostiDisdetta);

					evento.disdici(numPostiDisdetta);
					postiDisponibili = evento.getNumPostiTotali() - evento.getNumPostiPrenotati();
					System.out.println("numero posti prenotati: " + evento.getNumPostiPrenotati()
							+ ", numero posti disponibili: " + postiDisponibili);

				} catch (Exception disdetta) {
					System.out.println(disdetta.getMessage());
					break;
				}

				System.out.println("vuoi continuare con un'altra disdetta, true o false?");
				isDisdetta = in.nextBoolean();

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		in.close();

	}

}