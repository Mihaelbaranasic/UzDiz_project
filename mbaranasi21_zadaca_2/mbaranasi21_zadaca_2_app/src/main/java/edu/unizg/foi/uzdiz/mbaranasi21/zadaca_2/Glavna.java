package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.lib.citac.CsvCitacAranzmana;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.lib.citac.CsvCitacRezervacija;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.lib.pomocne.DatumParser;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.model.Aranzman;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.model.Osoba;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.model.Rezervacija;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.singleton.TuristickaAgencija;

/**
 * Glavna klasa aplikacije za upravljanje turističkom agencijom. Entry point
 * programa - parsira argumente i pokreće sustav.
 */
public class Glavna {

	/**
	 * Ulazna točka programa. Parsira argumente komandne linije, učitava podatke i
	 * pokreće interaktivni mod.
	 *
	 * @param args Argumenti komandne linije (--ta putanja --rta putanja)
	 */
	public static void main(String[] args) {
		Map<String, String> argumenti = parsirajArgumente(args);

		if (!provjeriArgumente(argumenti)) {
			System.exit(1);
		}

		String datotekaAranzmana = argumenti.get("--ta");
		String datotekaRezervacija = argumenti.get("--rta");

		ucitajPodatke(datotekaAranzmana, datotekaRezervacija);
		pokreniInteraktivniMod();
	}

	/**
	 * Parsira argumente komandne linije i vraća ih u obliku mape. Očekivani format:
	 * --naziv vrijednost
	 *
	 * @param args Argumenti komandne linije
	 * @return Mapa argumenta (ključ = --naziv, vrijednost = vrijednost)
	 */
	private static Map<String, String> parsirajArgumente(String[] args) {
		Map<String, String> mapa = new HashMap<>();

		for (int i = 0; i < args.length - 1; i++) {
			if (args[i].startsWith("--")) {
				mapa.put(args[i], args[i + 1]);
			}
		}

		return mapa;
	}

	/**
	 * Provjerava jesu li svi potrebni argumenti prisutni. Potrebni argumenti: --ta
	 * (aranžmani) i --rta (rezervacije).
	 *
	 * @param argumenti Mapa parsiranih argumenata
	 * @return true ako su svi argumenti prisutni, false inače
	 */
	private static boolean provjeriArgumente(Map<String, String> argumenti) {
		if (!argumenti.containsKey("--ta")) {
			System.err.println("GREŠKA: Nedostaje --ta (aranžmani)!");
			return false;
		}

		if (!argumenti.containsKey("--rta")) {
			System.err.println("GREŠKA: Nedostaje --rta (rezervacije)!");
			return false;
		}

		return true;
	}

	/**
	 * Učitava podatke iz CSV datoteka i pohranjuje ih u turističku agenciju.
	 * Koristi čitače za aranžmane i rezervacije.
	 *
	 * @param datotekaAranzmana   Putanja do CSV datoteke s aranžmanima
	 * @param datotekaRezervacija Putanja do CSV datoteke s rezervacijama
	 */
	private static void ucitajPodatke(String datotekaAranzmana, String datotekaRezervacija) {
//		TuristickaAgencija agencija = TuristickaAgencija.getInstance();
//
//		CsvCitacAranzmana citacAranzmana = new CsvCitacAranzmana();
//		CsvCitacRezervacija citacRezervacija = new CsvCitacRezervacija();
//
//		List<Aranzman> aranzmani = citacAranzmana.ucitaj(datotekaAranzmana);
//
//		for (Aranzman a : aranzmani) {
//			agencija.dodajAranzman(a);
//		}
//
//		List<Rezervacija> rezervacije = citacRezervacija.ucitaj(datotekaRezervacija);
//
//		for (Rezervacija r : rezervacije) {
//			agencija.dodajRezervaciju(r);
//		}
	}

	/**
	 * Pokreće interaktivni mod programa. Čita korisničke naredbe sa standardnog
	 * ulaza i obrađuje ih sve dok korisnik ne unese 'Q' za izlaz.
	 */
	private static void pokreniInteraktivniMod() {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.print("\n> ");
			String naredba = scanner.nextLine().trim();

			if (naredba.isEmpty()) {
				continue;
			}

			if (naredba.equalsIgnoreCase("Q")) {
				break;
			}

			obradiNaredbu(naredba);
		}

		scanner.close();
	}

	/**
	 * Obrađuje unesenu naredbu i poziva odgovarajuću metodu za izvršavanje.
	 * Podržane naredbe: ITAK, ITAP, IRTA, IRO, ORTA, DRTA.
	 *
	 * @param naredba Unesena naredba s parametrima
	 */
	private static void obradiNaredbu(String naredba) {
		String[] dijelovi = naredba.split("\\s+");
		String komanda = dijelovi[0].toUpperCase();

		switch (komanda) {
		case "ITAK":
			obradiITAK(dijelovi);
			break;
		case "ITAP":
			obradiITAP(dijelovi);
			break;
		case "IRTA":
			obradiIRTA(dijelovi);
			break;
		case "IRO":
			obradiIRO(dijelovi);
			break;
		case "ORTA":
			obradiORTA(dijelovi);
			break;
		case "DRTA":
			obradiDRTA(dijelovi);
			break;
		default:
			System.err.println("GREŠKA: Nepoznata naredba '" + komanda + "'!");
		}
	}

	/**
	 * Obrađuje naredbu ITAK - Ispis turističkih aranžmana kataloga. Bez parametara
	 * ispisuje sve aranžmane. S parametrima (datum od, datum do) filtrira aranžmane
	 * po datumskom rasponu.
	 *
	 * @param dijelovi Dijelovi naredbe (komanda, opcionalno: datum_od, datum_do)
	 */
	private static void obradiITAK(String[] dijelovi) {
		TuristickaAgencija agencija = TuristickaAgencija.getInstance();
		List<Aranzman> aranzmani = agencija.dohvatiSveAranzmane();

		if (dijelovi.length >= 3) {
			LocalDate od = DatumParser.parsirajDatum(dijelovi[1]);
			LocalDate do_ = DatumParser.parsirajDatum(dijelovi[2]);

			if (od == null || do_ == null) {
				System.err.println("GREŠKA: Neispravan format datuma!");
				System.err.println("Format: ITAK dd.MM.yyyy. dd.MM.yyyy.");
				return;
			}

			List<Aranzman> filtrirano = new ArrayList<>();
			for (Aranzman a : aranzmani) {
				LocalDate pocetak = a.getPocetniDatum();
				LocalDate kraj = a.getZavrsniDatum();

				if (!pocetak.isAfter(do_) && !kraj.isBefore(od)) {
					filtrirano.add(a);
				}
			}

			aranzmani = filtrirano;
		}

		ispisiTablicuAranzmana(aranzmani);
	}

	/**
	 * Ispisuje tablicu aranžmana sa svim važnim podacima. Tablica uključuje:
	 * oznaku, naziv, početni i završni datum, vrijeme kretanja i povratka, cijenu
	 * te minimalni i maksimalni broj putnika.
	 *
	 * @param aranzmani Lista aranžmana za ispis
	 */
	private static void ispisiTablicuAranzmana(List<Aranzman> aranzmani) {
		if (aranzmani.isEmpty()) {
			System.out.println("Nema aranžmana za prikaz.");
			return;
		}

		System.out.printf("%-10s %-30s %-12s %-12s %-10s %-10s %-10s %-8s %-8s%n", "Oznaka", "Naziv", "Početni",
				"Završni", "Kretanje", "Povratak", "Cijena", "Min", "Maks");
		System.out.println(String.format("%0" + 120 + "d", 0).replace("0", "-"));

		for (Aranzman a : aranzmani) {
			String kretanje = DatumParser.formatirajVrijeme(a.getVrijemeKretanja());
			String povratak = DatumParser.formatirajVrijeme(a.getVrijemePovratka());

			System.out.printf("%-10s %-30s %-12s %-12s %-10s %-10s %-10.2f %-8d %-8d%n", a.getOznaka(),
					skratiTekst(a.getNaziv(), 30), DatumParser.formatirajDatum(a.getPocetniDatum()),
					DatumParser.formatirajDatum(a.getZavrsniDatum()), kretanje, povratak, a.getCijena(),
					a.getMinBrojPutnika(), a.getMaksBrojPutnika());
		}
	}

	/**
	 * Skraćuje tekst na zadanu maksimalnu duljinu. Ako je tekst duži od maksimalne
	 * duljine, dodaje "..." na kraj.
	 *
	 * @param tekst      Tekst za skraćivanje
	 * @param maxDuljina Maksimalna dopuštena duljina
	 * @return Skraćeni tekst ili originalni tekst ako je kraći od maksimalne
	 *         duljine
	 */
	private static String skratiTekst(String tekst, int maxDuljina) {
		if (tekst.length() <= maxDuljina) {
			return tekst;
		}
		return tekst.substring(0, maxDuljina - 3) + "...";
	}

	/**
	 * Obrađuje naredbu ITAP - Ispis turističkog aranžmana po oznaci. Ispisuje sve
	 * detalje pojedinačnog aranžmana.
	 *
	 * @param dijelovi Dijelovi naredbe (komanda, oznaka_aranžmana)
	 */
	private static void obradiITAP(String[] dijelovi) {
		if (dijelovi.length < 2) {
			System.err.println("GREŠKA: Nedostaje oznaka aranžmana!");
			return;
		}

		String oznaka = dijelovi[1];
		TuristickaAgencija agencija = TuristickaAgencija.getInstance();
		Aranzman a = agencija.dohvatiAranzman(oznaka);

		if (a == null) {
			System.err.println("GREŠKA: Aranžman '" + oznaka + "' ne postoji!");
			return;
		}

		System.out.println("Oznaka: " + a.getOznaka());
		System.out.println("Naziv: " + a.getNaziv());

		if (a.getProgram() != null && !a.getProgram().isEmpty()) {
			System.out.println("Program:");
			String program = a.getProgram().replace("\\n", "\n");
			for (String linija : program.split("\n")) {
				System.out.println("  " + linija);
			}
		} else {
			System.out.println("Program: -");
		}

		System.out.println("Početni datum: " + DatumParser.formatirajDatum(a.getPocetniDatum()));
		System.out.println("Završni datum: " + DatumParser.formatirajDatum(a.getZavrsniDatum()));
		System.out.println("Vrijeme kretanja: " + DatumParser.formatirajVrijeme(a.getVrijemeKretanja()));
		System.out.println("Vrijeme povratka: " + DatumParser.formatirajVrijeme(a.getVrijemePovratka()));
		System.out.println("Cijena: " + a.getCijena() + " EUR");
		System.out.println("Min broj putnika: " + a.getMinBrojPutnika());
		System.out.println("Maks broj putnika: " + a.getMaksBrojPutnika());
		System.out.println("Broj noćenja: " + a.getBrojNocenja());
		System.out.println("Doplata za jednokrevetnu sobu: "
				+ (a.getDoplataZaJednokrevetnuSobu() != null ? a.getDoplataZaJednokrevetnuSobu() + " EUR" : "-"));
		System.out.println("Prijevoz: " + (a.getPrijevoz() != null ? a.getPrijevoz() : "-"));
		System.out.println("Broj doručka: " + a.getBrojDorucka());
		System.out.println("Broj ručkova: " + a.getBrojRuckova());
		System.out.println("Broj večera: " + a.getBrojVecera());
	}

	/**
	 * Obrađuje naredbu IRTA - Ispis rezervacija turističkog aranžmana. Bez dodatnih
	 * parametara ispisuje sve rezervacije. S parametrima filtrira po stanju: PA
	 * (primljene i aktivne), Č (na čekanju), O (otkazane), PAČO (sve sa datumom
	 * otkaza).
	 *
	 * @param dijelovi Dijelovi naredbe (komanda, oznaka_aranžmana, opcionalno:
	 *                 filter)
	 */
	private static void obradiIRTA(String[] dijelovi) {
		if (dijelovi.length < 2) {
			System.err.println("GREŠKA: Nedostaje oznaka aranžmana!");
			return;
		}

		String oznaka = dijelovi[1];
		TuristickaAgencija agencija = TuristickaAgencija.getInstance();
		List<Rezervacija> rezervacije;
		boolean prikaziOtkaz = false;

		if (dijelovi.length >= 3) {
			String filter = dijelovi[2].toUpperCase();

			if (filter.equals("PA")) {
				rezervacije = new ArrayList<>();
				for (Rezervacija r : agencija.dohvatiRezervacije(oznaka)) {
					if (r.jePrimljena() || r.jeAktivna()) {
						rezervacije.add(r);
					}
				}
			} else if (filter.equals("Č")) {
				rezervacije = new ArrayList<>();
				for (Rezervacija r : agencija.dohvatiRezervacije(oznaka)) {
					if (r.jeNaCekanju()) {
						rezervacije.add(r);
					}
				}
			} else if (filter.equals("O")) {
				prikaziOtkaz = true;
				rezervacije = new ArrayList<>();
				for (Rezervacija r : agencija.dohvatiRezervacije(oznaka)) {
					if (r.jeOtkazana()) {
						rezervacije.add(r);
					}
				}
			} else if (filter.equals("PAČO")) {
				prikaziOtkaz = true;
				rezervacije = agencija.dohvatiRezervacije(oznaka);
			} else {
				System.err.println("GREŠKA: Nepoznata oznaka stanja! Dopuštene su: PA, Č, O, PAČO");
				return;
			}
		} else {
			rezervacije = agencija.dohvatiRezervacije(oznaka);
		}

		ispisiTablicuRezervacija(rezervacije, prikaziOtkaz);
	}

	/**
	 * Ispisuje tablicu rezervacija. Tablica uključuje ime, prezime, datum i vrijeme
	 * prijema te vrstu rezervacije. Opcionalno prikazuje i datum otkaza ako je
	 * označeno.
	 *
	 * @param rezervacije  Lista rezervacija za ispis
	 * @param prikaziOtkaz True ako treba prikazati stupac s datumom otkaza, false
	 *                     inače
	 */
	private static void ispisiTablicuRezervacija(List<Rezervacija> rezervacije, boolean prikaziOtkaz) {
		if (rezervacije.isEmpty()) {
			System.out.println("Nema rezervacija za prikaz.");
			return;
		}

		if (prikaziOtkaz) {
			System.out.printf("%-15s %-15s %-20s %-12s %-20s%n", "Ime", "Prezime", "Datum i vrijeme", "Vrsta",
					"Datum otkaza");
			System.out.println(String.format("%0" + 82 + "d", 0).replace("0", "-"));
		} else {
			System.out.printf("%-15s %-15s %-20s %-12s%n", "Ime", "Prezime", "Datum i vrijeme", "Vrsta");
			System.out.println(String.format("%0" + 62 + "d", 0).replace("0", "-"));
		}

		for (Rezervacija r : rezervacije) {
			if (prikaziOtkaz) {
				String datumOtkaza = DatumParser.formatirajDatumVrijeme(r.getDatumVrijemeOtkaza());

				System.out.printf("%-15s %-15s %-20s %-12s %-20s%n", r.getOsoba().getIme(), r.getOsoba().getPrezime(),
						DatumParser.formatirajDatumVrijeme(r.getDatumVrijemePrijema()), r.getStanje().getOznaka(),
						datumOtkaza);
			} else {
				System.out.printf("%-15s %-15s %-20s %-12s%n", r.getOsoba().getIme(), r.getOsoba().getPrezime(),
						DatumParser.formatirajDatumVrijeme(r.getDatumVrijemePrijema()), r.getStanje().getOznaka());
			}
		}
	}

	/**
	 * Obrađuje naredbu IRO - Ispis rezervacija osobe. Ispisuje sve rezervacije
	 * određene osobe na svim aranžmanima.
	 *
	 * @param dijelovi Dijelovi naredbe (komanda, ime, prezime)
	 */
	private static void obradiIRO(String[] dijelovi) {
		if (dijelovi.length < 3) {
			System.err.println("GREŠKA: Nedostaju ime i prezime!");
			return;
		}

		String ime = dijelovi[1];
		String prezime = dijelovi[2];

		TuristickaAgencija agencija = TuristickaAgencija.getInstance();
		List<Rezervacija> rezervacije = agencija.dohvatiRezervacijeOsobe(ime, prezime);

		if (rezervacije.isEmpty()) {
			System.out.println("Nema rezervacija za osobu " + ime + " " + prezime + ".");
			return;
		}

		System.out.printf("%-20s %-10s %-30s %-12s%n", "Datum i vrijeme", "Oznaka", "Naziv aranžmana", "Vrsta");
		System.out.println(String.format("%0" + 72 + "d", 0).replace("0", "-"));

		for (Rezervacija r : rezervacije) {
			Aranzman a = agencija.dohvatiAranzman(r.getOznakaAranzmana());
			String naziv = a != null ? skratiTekst(a.getNaziv(), 30) : "-";

			System.out.printf("%-20s %-10s %-30s %-12s%n",
					DatumParser.formatirajDatumVrijeme(r.getDatumVrijemePrijema()), r.getOznakaAranzmana(), naziv,
					r.getStanje().getOznaka());
		}
	}

	/**
	 * Obrađuje naredbu ORTA - Otkaz rezervacije turističkog aranžmana. Otkazuje
	 * najstariju rezervaciju određene osobe za navedeni aranžman. Datum otkaza
	 * postavlja se na trenutno vrijeme.
	 *
	 * @param dijelovi Dijelovi naredbe (komanda, ime, prezime, oznaka_aranžmana)
	 */
	private static void obradiORTA(String[] dijelovi) {
		if (dijelovi.length < 4) {
			System.err.println("GREŠKA: Nedostaju podaci (ime prezime oznaka)!");
			return;
		}

		String ime = dijelovi[1];
		String prezime = dijelovi[2];
		String oznaka = dijelovi[3];

		TuristickaAgencija agencija = TuristickaAgencija.getInstance();
		boolean uspjeh = agencija.otkaziRezervaciju(ime, prezime, oznaka, LocalDateTime.now());

		if (uspjeh) {
			System.out.println("Rezervacija uspješno otkazana.");
		} else {
			System.err.println("GREŠKA: Rezervacija nije pronađena!");
		}
	}

	/**
	 * Obrađuje naredbu DRTA - Dodaj rezervaciju turističkog aranžmana. Dodaje novu
	 * rezervaciju za određenu osobu na navedeni aranžman.
	 *
	 * @param dijelovi Dijelovi naredbe (komanda, ime, prezime, oznaka_aranžmana,
	 *                 datum, vrijeme)
	 */
	private static void obradiDRTA(String[] dijelovi) {
		if (dijelovi.length < 6) {
			System.err.println("GREŠKA: Nedostaju podaci!");
			System.err.println("Format: DRTA ime prezime oznaka datum vrijeme");
			return;
		}

		String ime = dijelovi[1];
		String prezime = dijelovi[2];
		String oznaka = dijelovi[3];
		String datumVrijemeTekst = dijelovi[4] + " " + dijelovi[5];

		LocalDateTime datumVrijeme = DatumParser.parsirajDatumVrijeme(datumVrijemeTekst);
		if (datumVrijeme == null) {
			System.err.println("GREŠKA: Neispravan format datuma/vremena!");
			return;
		}

		Osoba osoba = new Osoba(ime, prezime);
		TuristickaAgencija agencija = TuristickaAgencija.getInstance();
		boolean uspjeh = agencija.dodajNovuRezervaciju(osoba, oznaka, datumVrijeme);

		if (uspjeh) {
			System.out.println("Rezervacija uspješno dodana.");
		} else {
			System.err.println("GREŠKA: Rezervacija nije dodana!");
		}
	}
}