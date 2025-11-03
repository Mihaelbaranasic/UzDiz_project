package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_1;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_1.model.Aranzman;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_1.model.Osoba;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_1.model.Rezervacija;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_1.model.StanjeRezervacije;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_1.rezervacija.RezervacijaCreator;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_1.rezervacija.StandardniRezervacijaCreator;

/**
 * Singleton klasa koja upravlja cijelim sustavom turističke agencije.
 * 
 * <p>
 * SINGLETON UZORAK - osigurava da postoji samo jedna instanca turističke
 * agencije u cijelom sustavu.
 * </p>
 */
public class TuristickaAgencija {

	private static volatile TuristickaAgencija instanca;

	private Map<String, Aranzman> aranzmani;
	private Map<String, List<Rezervacija>> rezervacijePoAranzmanu;
	private RezervacijaCreator rezervacijaCreator;

	/**
	 * Privatni konstruktor - onemogućava direktno instanciranje.
	 */
	private TuristickaAgencija() {
		this.aranzmani = new HashMap<>();
		this.rezervacijePoAranzmanu = new HashMap<>();
		this.rezervacijaCreator = new StandardniRezervacijaCreator();
	}

	/**
	 * Dohvaća jedinstvenu instancu turističke agencije. Koristi double-checked
	 * locking za thread safety.
	 *
	 * @return Instanca turističke agencije
	 */
	public static TuristickaAgencija getInstance() {
		if (instanca == null) {
			synchronized (TuristickaAgencija.class) {
				if (instanca == null) {
					instanca = new TuristickaAgencija();
				}
			}
		}
		return instanca;
	}

	/**
	 * Dodaje aranžman u sustav.
	 *
	 * @param aranzman Aranžman za dodavanje
	 */
	public void dodajAranzman(Aranzman aranzman) {
		aranzmani.put(aranzman.getOznaka(), aranzman);
		rezervacijePoAranzmanu.put(aranzman.getOznaka(), new ArrayList<>());
	}

	/**
	 * Dohvaća aranžman prema oznaci.
	 *
	 * @param oznaka Oznaka aranžmana
	 * @return Aranžman ili null ako ne postoji
	 */
	public Aranzman dohvatiAranzman(String oznaka) {
		return aranzmani.get(oznaka);
	}

	/**
	 * Dohvaća sve aranžmane.
	 *
	 * @return Lista svih aranžmana
	 */
	public List<Aranzman> dohvatiSveAranzmane() {
		return new ArrayList<>(aranzmani.values());
	}

	/**
	 * Dodaje rezervaciju za aranžman.
	 *
	 * @param rezervacija Rezervacija za dodavanje
	 */
	public void dodajRezervaciju(Rezervacija rezervacija) {
		String oznaka = rezervacija.getOznakaAranzmana();

		if (!rezervacijePoAranzmanu.containsKey(oznaka)) {
			rezervacijePoAranzmanu.put(oznaka, new ArrayList<>());
		}

		rezervacijePoAranzmanu.get(oznaka).add(rezervacija);
		azurirajStanjaRezervacija(oznaka);
	}

	/**
	 * Kreira i dodaje novu rezervaciju uz provjeru pravila.
	 *
	 * @param osoba           Osoba koja rezervira
	 * @param oznakaAranzmana Oznaka aranžmana
	 * @param datumVrijeme    Datum i vrijeme rezervacije
	 * @return true ako je rezervacija uspješno dodana, inače false
	 */
	public boolean dodajNovuRezervaciju(Osoba osoba, String oznakaAranzmana, LocalDateTime datumVrijeme) {
		if (!aranzmani.containsKey(oznakaAranzmana)) {
			System.err.println("GREŠKA: Aranžman " + oznakaAranzmana + " ne postoji.");
			return false;
		}

		Aranzman noviAranzman = aranzmani.get(oznakaAranzmana);

		List<Rezervacija> rezervacijeNaAranzmanu = dohvatiRezervacije(oznakaAranzmana);

		for (Rezervacija r : rezervacijeNaAranzmanu) {
			if (r.getOsoba().getIme().equals(osoba.getIme()) && r.getOsoba().getPrezime().equals(osoba.getPrezime())
					&& r.jeAktivna()) {
				System.err.println("GREŠKA: Rezervacija nije dodana. Korisnik " + osoba.getIme() + " "
						+ osoba.getPrezime() + " već ima aktivnu rezervaciju za aranžman " + oznakaAranzmana + ".");
				return false;
			}
		}

		List<Rezervacija> osobneRezervacije = dohvatiRezervacijeOsobe(osoba.getIme(), osoba.getPrezime());

		for (Rezervacija r : osobneRezervacije) {
			if (r.jeAktivna()) {
				Aranzman postojeciAranzman = aranzmani.get(r.getOznakaAranzmana());
				if (postojeciAranzman != null && noviAranzman.preklapa(postojeciAranzman)) {
					System.err.println("GREŠKA: Rezervacija nije dodana. " + "Korisnik " + osoba.getIme() + " "
							+ osoba.getPrezime() + " ima aktivnu rezervaciju na aranžmanu " + r.getOznakaAranzmana()
							+ " koji se preklapa s " + "aranžmanom " + oznakaAranzmana + ".");
					return false;
				}
			}
		}

		Rezervacija rezervacija = rezervacijaCreator.kreirajRezervaciju(osoba, oznakaAranzmana, datumVrijeme,
				StanjeRezervacije.PRIMLJENA);

		dodajRezervaciju(rezervacija);
		return true;
	}

	/**
	 * Dohvaća sve rezervacije za određeni aranžman.
	 *
	 * @param oznakaAranzmana Oznaka aranžmana
	 * @return Lista rezervacija
	 */
	public List<Rezervacija> dohvatiRezervacije(String oznakaAranzmana) {
		return rezervacijePoAranzmanu.getOrDefault(oznakaAranzmana, new ArrayList<>());
	}

	/**
	 * Dohvaća rezervacije za aranžman prema stanju.
	 *
	 * @param oznakaAranzmana Oznaka aranžmana
	 * @param stanje          Stanje rezervacije
	 * @return Lista rezervacija u određenom stanju
	 */
	public List<Rezervacija> dohvatiRezervacijePoStanju(String oznakaAranzmana, StanjeRezervacije stanje) {
		return dohvatiRezervacije(oznakaAranzmana).stream().filter(r -> r.getStanje() == stanje)
				.collect(Collectors.toList());
	}

	/**
	 * Dohvaća sve rezervacije za osobu.
	 *
	 * @param ime     Ime osobe
	 * @param prezime Prezime osobe
	 * @return Lista rezervacija za tu osobu
	 */
	public List<Rezervacija> dohvatiRezervacijeOsobe(String ime, String prezime) {
		List<Rezervacija> rezultat = new ArrayList<>();

		for (List<Rezervacija> lista : rezervacijePoAranzmanu.values()) {
			for (Rezervacija r : lista) {
				if (r.getOsoba().getIme().equals(ime) && r.getOsoba().getPrezime().equals(prezime)) {
					rezultat.add(r);
				}
			}
		}

		return rezultat;
	}

	/**
	 * Otkazuje rezervaciju za osobu.
	 *
	 * @param ime             Ime osobe
	 * @param prezime         Prezime osobe
	 * @param oznakaAranzmana Oznaka aranžmana
	 * @param datumVrijeme    Datum i vrijeme otkaza
	 * @return true ako je rezervacija otkazana, inače false
	 */
	public boolean otkaziRezervaciju(String ime, String prezime, String oznakaAranzmana, LocalDateTime datumVrijeme) {
		List<Rezervacija> rezervacije = dohvatiRezervacije(oznakaAranzmana);

		for (Rezervacija r : rezervacije) {
			if (r.getOsoba().getIme().equals(ime) && r.getOsoba().getPrezime().equals(prezime) && !r.jeOtkazana()) {

				r.otkazi(datumVrijeme);

				azurirajStanjaRezervacija(oznakaAranzmana);

				System.out.println("Rezervacija korisnika " + ime + " " + prezime + " je otkazana.");
				return true;
			}
		}

		System.err.println("GREŠKA: Rezervacija korisnika " + ime + " " + prezime + " za aranžman " + oznakaAranzmana
				+ " ne postoji ili je već otkazana.");
		return false;
	}

	/**
	 * Ažurira stanja svih rezervacija za aranžman prema pravilima. VAŽNO: Svaka
	 * osoba može imati samo 1 aktivnu rezervaciju po aranžmanu.
	 *
	 * @param oznakaAranzmana Oznaka aranžmana
	 */
	private void azurirajStanjaRezervacija(String oznakaAranzmana) {
		Aranzman aranzman = dohvatiAranzman(oznakaAranzmana);
		if (aranzman == null) {
			return;
		}

		List<Rezervacija> rezervacije = dohvatiRezervacije(oznakaAranzmana);

		List<Rezervacija> aktivneIPrimljene = rezervacije.stream().filter(r -> r.jeAktivna() || r.jePrimljena())
				.sorted((r1, r2) -> r1.getDatumVrijemePrijema().compareTo(r2.getDatumVrijemePrijema()))
				.collect(Collectors.toList());

		int brojRezervacija = aktivneIPrimljene.size();

		if (brojRezervacija >= aranzman.getMinBrojPutnika()) {
			Map<String, Boolean> osobaImaAktivu = new HashMap<>();
			int brojDodijeljenih = 0;

			List<Rezervacija> zaBrisanje = new ArrayList<>();

			for (Rezervacija rez : aktivneIPrimljene) {
				String kljucOsobe = rez.getOsoba().getIme() + "_" + rez.getOsoba().getPrezime();

				if (osobaImaAktivu.getOrDefault(kljucOsobe, false)) {
					System.err.println("GREŠKA: Rezervacija korisnika " + rez.getOsoba().getIme() + " "
							+ rez.getOsoba().getPrezime() + " za " + oznakaAranzmana + " ("
							+ rez.getDatumVrijemePrijema() + ") je neispravna - "
							+ "korisnik već ima aktivnu rezervaciju.");

					zaBrisanje.add(rez);
					continue;
				}

				if (brojDodijeljenih < aranzman.getMaksBrojPutnika()) {
					rez.postaviStanje(StanjeRezervacije.AKTIVNA);
					osobaImaAktivu.put(kljucOsobe, true);
					brojDodijeljenih++;
				} else {
					rez.postaviStanje(StanjeRezervacije.NA_CEKANJU);
				}
			}

			rezervacije.removeAll(zaBrisanje);
		}
	}

	/**
	 * Briše sve podatke iz sustava (za testiranje).
	 */
	public void resetiraj() {
		aranzmani.clear();
		rezervacijePoAranzmanu.clear();
	}
}