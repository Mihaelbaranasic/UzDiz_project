package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_1;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_1.konfiguracija.citac.CsvCitacAranzmana;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_1.konfiguracija.citac.CsvCitacRezervacija;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_1.konfiguracija.pomocne.DatumParser;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_1.model.Aranzman;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_1.model.Osoba;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_1.model.Rezervacija;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_1.model.StanjeRezervacije;

/**
 * Glavna klasa aplikacije za upravljanje turističkom agencijom.
 * Entry point programa - parsira argumente i pokreće sustav.
 */
public class Agencija {

    public static void main(String[] args) {
        ispisiZaglavlje();
        
        Map<String, String> argumenti = parsirajArgumente(args);
        
        if (!provjeriArgumente(argumenti)) {
            ispisiUpute();
            System.exit(1);
        }
        
        String datotekaAranzmana = argumenti.get("--ta");
        String datotekaRezervacija = argumenti.get("--rta");
        
        ispisiDatoteke(datotekaAranzmana, datotekaRezervacija);
        
        ucitajPodatke(datotekaAranzmana, datotekaRezervacija);
        
        pokreniInteraktivniMod();
    }

    private static void ispisiZaglavlje() {
        System.out.println("=========================================================");
        System.out.println("  Turistička Agencija");
        System.out.println("  Sustav za upravljanje rezervacijama");
        System.out.println("=========================================================");
        System.out.println();
    }

    private static Map<String, String> parsirajArgumente(String[] args) {
        Map<String, String> mapa = new HashMap<>();
        
        for (int i = 0; i < args.length - 1; i++) {
            if (args[i].startsWith("--")) {
                mapa.put(args[i], args[i + 1]);
            }
        }
        
        return mapa;
    }

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

    private static void ispisiDatoteke(String datotekaAranzmana, 
                                       String datotekaRezervacija) {
        System.out.println("Učitavanje podataka:");
        System.out.println("  Aranžmani:    " + datotekaAranzmana);
        System.out.println("  Rezervacije:  " + datotekaRezervacija);
        System.out.println();
    }

    private static void ucitajPodatke(String datotekaAranzmana, 
                                     String datotekaRezervacija) {
        TuristickaAgencija agencija = TuristickaAgencija.getInstance();
        
        CsvCitacAranzmana citacAranzmana = new CsvCitacAranzmana();
        List<Aranzman> aranzmani = citacAranzmana.ucitaj(datotekaAranzmana);
        System.out.println("Učitano " + aranzmani.size() + " aranžmana.");
        
        for (Aranzman a : aranzmani) {
            agencija.dodajAranzman(a);
        }
        
        CsvCitacRezervacija citacRezervacija = new CsvCitacRezervacija();
        List<Rezervacija> rezervacije = citacRezervacija.ucitaj(datotekaRezervacija);
        System.out.println("Učitano " + rezervacije.size() + " rezervacija.");
        
        for (Rezervacija r : rezervacije) {
            agencija.dodajRezervaciju(r);
        }
        
        System.out.println();
    }

    private static void pokreniInteraktivniMod() {
        System.out.println("=========================================================");
        System.out.println("  INTERAKTIVNI NAČIN RADA");
        System.out.println("=========================================================");
        ispisiDostupneNaredbe();
        
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.print("\n> ");
            String naredba = scanner.nextLine().trim();
            
            if (naredba.isEmpty()) {
                continue;
            }
            
            if (naredba.equalsIgnoreCase("Q")) {
                System.out.println("\nIzlaz iz programa. Doviđenja!");
                break;
            }
            
            obradiNaredbu(naredba);
        }
        
        scanner.close();
    }

    private static void ispisiDostupneNaredbe() {
        System.out.println("\nDostupne naredbe:");
        System.out.println("  ITAK [od do]        - Ispiši tablicu aranžmana");
        System.out.println("  ITAP oznaka         - Ispiši tablicu aranžmana za putovanje");
        System.out.println("  IRTA oznaka [stanje] - Ispiši rezervacije za aranžman");
        System.out.println("  IRO ime prezime     - Ispiši rezervacije osobe");
        System.out.println("  ORTA ime prezime oznaka - Otkaži rezervaciju");
        System.out.println("  DRTA ime prezime oznaka datum vrijeme - Dodaj rezervaciju");
        System.out.println("  Q                   - Izlaz");
    }

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
                ispisiDostupneNaredbe();
        }
    }

    private static void obradiITAK(String[] dijelovi) {
        TuristickaAgencija agencija = TuristickaAgencija.getInstance();
        List<Aranzman> aranzmani = agencija.dohvatiSveAranzmane();
        
        // Ako su zadani datumi, filtriraj
        if (dijelovi.length >= 3) {
            LocalDate od = DatumParser.parsirajDatum(dijelovi[1]);
            LocalDate do_ = DatumParser.parsirajDatum(dijelovi[2]);
            
            if (od == null || do_ == null) {
                System.err.println("GREŠKA: Neispravan format datuma!");
                System.err.println("Format: ITAK dd.MM.yyyy. dd.MM.yyyy.");
                return;
            }
            
            aranzmani = aranzmani.stream()
                .filter(a -> {
                    LocalDate pocetak = a.getPocetniDatum();
                    LocalDate kraj = a.getZavrsniDatum();
                    // Aranžman se preklapa s rasponom ako:
                    // početak aranžmana <= do AND kraj aranžmana >= od
                    return !pocetak.isAfter(do_) && !kraj.isBefore(od);
                })
                .collect(java.util.stream.Collectors.toList());
            
            System.out.println("\n=== ARANŽMANI " + od + " - " + do_ + " ===");
        } else {
            System.out.println("\n=== SVI ARANŽMANI ===");
        }
        
        System.out.println("Ukupno: " + aranzmani.size());
        System.out.println();
        
        for (Aranzman a : aranzmani) {
            System.out.println(a.getOznaka() + " | " + a.getNaziv() + 
                              " | " + a.getPocetniDatum() + " - " + 
                              a.getZavrsniDatum());
        }
    }

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
        
        System.out.println("\n=== DETALJI ARANŽMANA ===");
        System.out.println("Oznaka: " + a.getOznaka());
        System.out.println("Naziv: " + a.getNaziv());
        System.out.println("Datum: " + a.getPocetniDatum() + " - " + a.getZavrsniDatum());
        System.out.println("Cijena: " + a.getCijena() + " EUR");
        System.out.println("Putnici: " + a.getMinBrojPutnika() + " - " + a.getMaksBrojPutnika());
    }

    private static void obradiIRTA(String[] dijelovi) {
        if (dijelovi.length < 2) {
            System.err.println("GREŠKA: Nedostaje oznaka aranžmana!");
            return;
        }
        
        String oznaka = dijelovi[1];
        TuristickaAgencija agencija = TuristickaAgencija.getInstance();
        List<Rezervacija> rezervacije;
        
        if (dijelovi.length >= 3) {
            StanjeRezervacije stanje = StanjeRezervacije.fromOznaka(dijelovi[2]);
            if (stanje == null) {
                System.err.println("GREŠKA: Nepoznata oznaka stanja!");
                return;
            }
            rezervacije = agencija.dohvatiRezervacijePoStanju(oznaka, stanje);
        } else {
            rezervacije = agencija.dohvatiRezervacije(oznaka);
        }
        
        System.out.println("\n=== REZERVACIJE ZA " + oznaka + " ===");
        System.out.println("Ukupno: " + rezervacije.size());
        
        for (Rezervacija r : rezervacije) {
            System.out.println(r.getOsoba().getPunoIme() + " | " + r.getStanje());
        }
    }

    private static void obradiIRO(String[] dijelovi) {
        if (dijelovi.length < 3) {
            System.err.println("GREŠKA: Nedostaju ime i prezime!");
            return;
        }
        
        String ime = dijelovi[1];
        String prezime = dijelovi[2];
        
        TuristickaAgencija agencija = TuristickaAgencija.getInstance();
        List<Rezervacija> rezervacije = agencija.dohvatiRezervacijeOsobe(ime, prezime);
        
        System.out.println("\n=== REZERVACIJE ZA " + ime + " " + prezime + " ===");
        System.out.println("Ukupno: " + rezervacije.size());
        
        for (Rezervacija r : rezervacije) {
            System.out.println(r.getOznakaAranzmana() + " | " + r.getStanje());
        }
    }

    private static void obradiORTA(String[] dijelovi) {
        if (dijelovi.length < 4) {
            System.err.println("GREŠKA: Nedostaju podaci (ime prezime oznaka)!");
            return;
        }
        
        String ime = dijelovi[1];
        String prezime = dijelovi[2];
        String oznaka = dijelovi[3];
        
        TuristickaAgencija agencija = TuristickaAgencija.getInstance();
        boolean uspjeh = agencija.otkaziRezervaciju(ime, prezime, oznaka, 
                                                     LocalDateTime.now());
        
        if (uspjeh) {
            System.out.println("Rezervacija uspješno otkazana.");
        } else {
            System.err.println("GREŠKA: Rezervacija nije pronađena!");
        }
    }

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
            System.err.println("GREŠKA: Aranžman ne postoji!");
        }
    }

    private static void ispisiUpute() {
        System.out.println("\nKORIŠTENJE:");
        System.out.println("  java -jar mbaranasi21_zadaca_1.jar --ta ARANZMANI.csv --rta REZERVACIJE.csv");
        System.out.println();
        System.out.println("ARGUMENTI:");
        System.out.println("  --ta   Datoteka s aranžmanima (CSV)");
        System.out.println("  --rta  Datoteka s rezervacijama (CSV)");
        System.out.println();
    }
}