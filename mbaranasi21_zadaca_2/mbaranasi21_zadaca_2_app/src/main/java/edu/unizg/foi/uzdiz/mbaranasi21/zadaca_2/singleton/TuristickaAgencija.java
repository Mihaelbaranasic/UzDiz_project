package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.singleton;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.model.Aranzman;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.model.Osoba;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.model.Rezervacija;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.model.StanjeRezervacije;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.stanje.aranzman.AranzmanStanje;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.stanje.aranzman.UPripremiAranzman;

/**
 * Singleton klasa koja upravlja cijelim sustavom turističke agencije.
 */
public class TuristickaAgencija {

	private static volatile TuristickaAgencija instanca;

	private Map<String, Aranzman> aranzmani;
	private Map<String, List<Rezervacija>> rezervacijePoAranzmanu;
	private Map<String, AranzmanStanje> stanjaAranzmana; // ← NOVO - State za aranžmane

	/**
	 * Privatni konstruktor - onemogućava direktno instanciranje.
	 */
	private TuristickaAgencija() {
		this.aranzmani = new HashMap<>();
		this.rezervacijePoAranzmanu = new HashMap<>();
		this.stanjaAranzmana = new HashMap<>(); // ← NOVO
	}

	/**
	 * Dohvaća jedinstvenu instancu turističke agencije.
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
	 */
	public void dodajAranzman(Aranzman aranzman) {
		aranzmani.put(aranzman.getOznaka(), aranzman);
		rezervacijePoAranzmanu.put(aranzman.getOznaka(), new ArrayList<>());
		stanjaAranzmana.put(aranzman.getOznaka(), new UPripremiAranzman()); // ← NOVO - inicijalno stanje
	}

	/**
	 * Dohvaća aranžman prema oznaci.
	 */
	public Aranzman dohvatiAranzman(String oznaka) {
		return aranzmani.get(oznaka);
	}
	
	/**
	 * Dohvaća stanje aranžmana.
	 */
	public AranzmanStanje dohvatiStanjeAranzmana(String oznaka) {
		return stanjaAranzmana.get(oznaka);
	}
	
	/**
	 * Postavlja stanje aranžmana.
	 */
	public void postaviStanjeAranzmana(String oznaka, AranzmanStanje novoStanje) {
		stanjaAranzmana.put(oznaka, novoStanje);
	}

	/**
	 * Dohvaća sve aranžmane.
	 */
	public List<Aranzman> dohvatiSveAranzmane() {
		return new ArrayList<>(aranzmani.values());
	}

	/**
	 * Dodaje rezervaciju za aranžman.
	 */
	public void dodajRezervaciju(Rezervacija rezervacija) {
		String oznaka = rezervacija.getOznakaAranzmana();

		if (!rezervacijePoAranzmanu.containsKey(oznaka)) {
			rezervacijePoAranzmanu.put(oznaka, new ArrayList<>());
		}

		rezervacijePoAranzmanu.get(oznaka).add(rezervacija);
		azurirajStanjaRezervacija(oznaka);
		azurirajStanjeAranzmana(oznaka); // ← NOVO - ažuriraj stanje aranžmana
	}

	/**
	 * Kreira i dodaje novu rezervaciju uz provjeru pravila.
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

		Rezervacija rezervacija = new Rezervacija(osoba, oznakaAranzmana, datumVrijeme, 
				StanjeRezervacije.PRIMLJENA);

		dodajRezervaciju(rezervacija);
		return true;
	}

	/**
	 * Dohvaća sve rezervacije za određeni aranžman.
	 */
	public List<Rezervacija> dohvatiRezervacije(String oznakaAranzmana) {
		return rezervacijePoAranzmanu.getOrDefault(oznakaAranzmana, new ArrayList<>());
	}

	/**
	 * Dohvaća rezervacije za aranžman prema stanju.
	 */
	public List<Rezervacija> dohvatiRezervacijePoStanju(String oznakaAranzmana, StanjeRezervacije stanje) {
		return dohvatiRezervacije(oznakaAranzmana).stream().filter(r -> r.getStanje() == stanje)
				.collect(Collectors.toList());
	}

	/**
	 * Dohvaća sve rezervacije određene osobe.
	 */
	public List<Rezervacija> dohvatiRezervacijeOsobe(String ime, String prezime) {
		List<Rezervacija> rezultat = new ArrayList<>();

		for (List<Rezervacija> listaRezervacija : rezervacijePoAranzmanu.values()) {
			for (Rezervacija r : listaRezervacija) {
				if (r.getOsoba().getIme().equals(ime) && r.getOsoba().getPrezime().equals(prezime)) {
					rezultat.add(r);
				}
			}
		}

		return rezultat;
	}

	/**
	 * Otkazuje rezervaciju korisnika za određeni aranžman.
	 */
	public boolean otkaziRezervaciju(String ime, String prezime, String oznakaAranzmana, LocalDateTime datumVrijeme) {
		List<Rezervacija> rezervacije = dohvatiRezervacije(oznakaAranzmana);

		Rezervacija najstarija = null;

		for (Rezervacija r : rezervacije) {
			if (r.getOsoba().getIme().equals(ime) && r.getOsoba().getPrezime().equals(prezime) && !r.jeOtkazana()) {

				if (najstarija == null || r.getDatumVrijemePrijema().isBefore(najstarija.getDatumVrijemePrijema())) {
					najstarija = r;
				}
			}
		}

		if (najstarija == null) {
			System.err.println("GREŠKA: Rezervacija korisnika " + ime + " " + prezime + " za aranžman "
					+ oznakaAranzmana + " ne postoji ili je već otkazana.");
			return false;
		}

		najstarija.otkazi(datumVrijeme);

		azurirajStanjaRezervacija(oznakaAranzmana);
		azurirajStanjeAranzmana(oznakaAranzmana); // ← NOVO
		return true;
	}

	/**
	 * Ažurira stanja rezervacija za aranžman.
	 */
	private void azurirajStanjaRezervacija(String oznakaAranzmana) {
		Aranzman aranzman = dohvatiAranzman(oznakaAranzmana);
		if (aranzman == null) {
			return;
		}

		List<Rezervacija> rezervacije = dohvatiRezervacije(oznakaAranzmana);

		List<Rezervacija> primljeneIAktivne = new ArrayList<>();
		for (Rezervacija r : rezervacije) {
			if (r.jePrimljena() || r.jeAktivna()) {
				primljeneIAktivne.add(r);
			}
		}

		for (int i = 0; i < primljeneIAktivne.size() - 1; i++) {
			for (int j = 0; j < primljeneIAktivne.size() - i - 1; j++) {
				if (primljeneIAktivne.get(j).getDatumVrijemePrijema()
						.isAfter(primljeneIAktivne.get(j + 1).getDatumVrijemePrijema())) {
					Rezervacija temp = primljeneIAktivne.get(j);
					primljeneIAktivne.set(j, primljeneIAktivne.get(j + 1));
					primljeneIAktivne.set(j + 1, temp);
				}
			}
		}

		int brojRezervacija = primljeneIAktivne.size();

		if (brojRezervacija < aranzman.getMinBrojPutnika()) {
			for (Rezervacija r : primljeneIAktivne) {
				r.primljena();
			}
			return;
		}

		int maxAktivnih = Math.min(brojRezervacija, aranzman.getMaksBrojPutnika());

		Map<String, Boolean> osobaImaAktivu = new HashMap<>();
		Map<String, Boolean> osobaImaPreklop = new HashMap<>();
		List<Rezervacija> zaBrisanje = new ArrayList<>();

		int brojAktivnih = 0;

		for (Rezervacija rez : primljeneIAktivne) {
			String kljucOsobe = rez.getOsoba().getIme() + "|" + rez.getOsoba().getPrezime();

			if (brojAktivnih < maxAktivnih) {
				if (osobaImaAktivu.getOrDefault(kljucOsobe, false)) {
					System.err.println("GREŠKA: Rezervacija korisnika " + rez.getOsoba().getIme() + " "
							+ rez.getOsoba().getPrezime() + " za " + oznakaAranzmana + " ("
							+ rez.getDatumVrijemePrijema() + ") je neispravna - "
							+ "korisnik već ima aktivnu rezervaciju.");
					zaBrisanje.add(rez);
					continue;
				}

				boolean imaPreklop = false;
				if (!osobaImaPreklop.containsKey(kljucOsobe)) {
					imaPreklop = provjeriPreklapanje(rez.getOsoba(), aranzman);
					osobaImaPreklop.put(kljucOsobe, imaPreklop);
				} else {
					imaPreklop = osobaImaPreklop.get(kljucOsobe);
				}

				if (imaPreklop) {
					System.err.println("GREŠKA: Rezervacija korisnika " + rez.getOsoba().getIme() + " "
							+ rez.getOsoba().getPrezime() + " za " + oznakaAranzmana + " ("
							+ rez.getDatumVrijemePrijema() + ") je neispravna - "
							+ "korisnik ima aktivnu rezervaciju na aranžmanu koji se preklapa.");
					zaBrisanje.add(rez);
					continue;
				}

				rez.aktiviraj();
				osobaImaAktivu.put(kljucOsobe, true);
				brojAktivnih++;
			} else {
				rez.staviNaCekanje();
			}
		}

		for (Rezervacija r : zaBrisanje) {
			rezervacije.remove(r);
		}
	}
	
	/**
	 * Ažurira stanje aranžmana na temelju broja rezervacija.
	 * Koristi State pattern za prijelaze.
	 */
	private void azurirajStanjeAranzmana(String oznakaAranzmana) {
		Aranzman aranzman = dohvatiAranzman(oznakaAranzmana);
		if (aranzman == null) {
			return;
		}
		
		AranzmanStanje trenutnoStanje = stanjaAranzmana.get(oznakaAranzmana);
		if (trenutnoStanje == null) {
			trenutnoStanje = new UPripremiAranzman();
			stanjaAranzmana.put(oznakaAranzmana, trenutnoStanje);
		}
		
		// Broji aktivne rezervacije
		List<Rezervacija> rezervacije = dohvatiRezervacije(oznakaAranzmana);
		long brojAktivnih = rezervacije.stream().filter(r -> r.jeAktivna()).count();
		
		// Logika prijelaza na temelju broja rezervacija
		if (brojAktivnih < aranzman.getMinBrojPutnika()) {
			// Prelazi u U_PRIPREMI
			trenutnoStanje.uPripremi(this, oznakaAranzmana);
		} else if (brojAktivnih >= aranzman.getMinBrojPutnika() 
				&& brojAktivnih <= aranzman.getMaksBrojPutnika()) {
			// Prelazi u AKTIVAN
			trenutnoStanje.aktiviraj(this, oznakaAranzmana);
		} else if (brojAktivnih > aranzman.getMaksBrojPutnika()) {
			// Prelazi u POPUNJEN
			trenutnoStanje.popuni(this, oznakaAranzmana);
		}
	}

	/**
	 * Briše sve podatke iz sustava (za testiranje).
	 */
	public void resetiraj() {
		aranzmani.clear();
		rezervacijePoAranzmanu.clear();
		stanjaAranzmana.clear(); // ← NOVO
	}

	/**
	 * Provjerava ima li osoba aktivnu rezervaciju na aranžmanu koji se preklapa.
	 */
	private boolean provjeriPreklapanje(Osoba osoba, Aranzman aranzman) {
		for (String oznakaAranzmana : rezervacijePoAranzmanu.keySet()) {
			if (oznakaAranzmana.equals(aranzman.getOznaka())) {
				continue;
			}

			Aranzman drugiAranzman = dohvatiAranzman(oznakaAranzmana);
			if (drugiAranzman == null) {
				continue;
			}

			if (!aranzman.preklapa(drugiAranzman)) {
				continue;
			}

			List<Rezervacija> rezervacije = dohvatiRezervacije(oznakaAranzmana);
			for (Rezervacija r : rezervacije) {
				if (r.getOsoba().getIme().equals(osoba.getIme()) && r.getOsoba().getPrezime().equals(osoba.getPrezime())
						&& r.jeAktivna()) {
					return true;
				}
			}
		}

		return false;
	}
}