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

/**
 * Glavna klasa aplikacije za upravljanje turističkom agencijom.
 * Entry point programa - parsira argumente i pokreće sustav.
 */
public class Agencija {

    public static void main(String[] args) {
        
        Map<String, String> argumenti = parsirajArgumente(args);
        
        if (!provjeriArgumente(argumenti)) {
            ispisiUpute();
            System.exit(1);
        }
        
        String datotekaAranzmana = argumenti.get("--ta");
        String datotekaRezervacija = argumenti.get("--rta");
                
        ucitajPodatke(datotekaAranzmana, datotekaRezervacija);
        
        pokreniInteraktivniMod();
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

    private static void ucitajPodatke(String datotekaAranzmana, 
                                     String datotekaRezervacija) {
        TuristickaAgencija agencija = TuristickaAgencija.getInstance();
        
        CsvCitacAranzmana citacAranzmana = new CsvCitacAranzmana();
        List<Aranzman> aranzmani = citacAranzmana.ucitaj(datotekaAranzmana);
        
        for (Aranzman a : aranzmani) {
            agencija.dodajAranzman(a);
        }
        
        CsvCitacRezervacija citacRezervacija = new CsvCitacRezervacija();
        List<Rezervacija> rezervacije = citacRezervacija.ucitaj(datotekaRezervacija);
        
        for (Rezervacija r : rezervacije) {
            agencija.dodajRezervaciju(r);
        }
        
        System.out.println();
    }

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
            
            aranzmani = aranzmani.stream()
                .filter(a -> {
                    LocalDate pocetak = a.getPocetniDatum();
                    LocalDate kraj = a.getZavrsniDatum();
                    return !pocetak.isAfter(do_) && !kraj.isBefore(od);
                })
                .collect(java.util.stream.Collectors.toList());
        }
        
        ispisiTablicuAranzmana(aranzmani);
    }

    private static void ispisiTablicuAranzmana(List<Aranzman> aranzmani) {
        if (aranzmani.isEmpty()) {
            System.out.println("Nema aranžmana za prikaz.");
            return;
        }
        
        System.out.printf("%-10s %-30s %-12s %-12s %-10s %-10s %-10s %-8s %-8s%n",
            "Oznaka", "Naziv", "Početni", "Završni", "Kretanje", "Povratak", 
            "Cijena", "Min", "Maks");
        System.out.println(String.format("%0" + 120 + "d", 0).replace("0", "-"));
        
        for (Aranzman a : aranzmani) {
            String kretanje = a.getVrijemeKretanja() != null 
                ? a.getVrijemeKretanja().toString() : "-";
            String povratak = a.getVrijemePovratka() != null 
                ? a.getVrijemePovratka().toString() : "-";
            
            System.out.printf("%-10s %-30s %-12s %-12s %-10s %-10s %-10.2f %-8d %-8d%n",
                a.getOznaka(),
                skratiTekst(a.getNaziv(), 30),
                a.getPocetniDatum(),
                a.getZavrsniDatum(),
                kretanje,
                povratak,
                a.getCijena(),
                a.getMinBrojPutnika(),
                a.getMaksBrojPutnika()
            );
        }
    }

    private static String skratiTekst(String tekst, int maxDuljina) {
        if (tekst.length() <= maxDuljina) {
            return tekst;
        }
        return tekst.substring(0, maxDuljina - 3) + "...";
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
        
        System.out.println("Oznaka: " + a.getOznaka());
        System.out.println("Naziv: " + a.getNaziv());
        System.out.println("Program: " + (a.getProgram() != null ? a.getProgram() : "-"));
        System.out.println("Početni datum: " + a.getPocetniDatum());
        System.out.println("Završni datum: " + a.getZavrsniDatum());
        System.out.println("Vrijeme kretanja: " + 
            (a.getVrijemeKretanja() != null ? a.getVrijemeKretanja() : "-"));
        System.out.println("Vrijeme povratka: " + 
            (a.getVrijemePovratka() != null ? a.getVrijemePovratka() : "-"));
        System.out.println("Cijena: " + a.getCijena() + " EUR");
        System.out.println("Min broj putnika: " + a.getMinBrojPutnika());
        System.out.println("Maks broj putnika: " + a.getMaksBrojPutnika());
        System.out.println("Broj noćenja: " + a.getBrojNocenja());
        System.out.println("Doplata za jednokrevetnu sobu: " + 
            (a.getDoplataZaJednokrevetnuSobu() != null 
                ? a.getDoplataZaJednokrevetnuSobu() + " EUR" : "-"));
        System.out.println("Prijevoz: " + (a.getPrijevoz() != null ? a.getPrijevoz() : "-"));
        System.out.println("Broj doručka: " + a.getBrojDorucka());
        System.out.println("Broj ručkova: " + a.getBrojRuckova());
        System.out.println("Broj večera: " + a.getBrojVecera());
    }

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
            
            if (filter.contains("PA")) {
                rezervacije = agencija.dohvatiRezervacije(oznaka).stream()
                    .filter(r -> r.jePrimljena() || r.jeAktivna())
                    .collect(java.util.stream.Collectors.toList());
            } else if (filter.contains("Č")) {
                rezervacije = agencija.dohvatiRezervacije(oznaka).stream()
                    .filter(r -> r.jeNaCekanju())
                    .collect(java.util.stream.Collectors.toList());
            } else if (filter.contains("O")) {
                prikaziOtkaz = true;
                rezervacije = agencija.dohvatiRezervacije(oznaka);
            } else {
                System.err.println("GREŠKA: Nepoznata oznaka stanja!");
                return;
            }
        } else {
            rezervacije = agencija.dohvatiRezervacije(oznaka);
        }
        
        ispisiTablicuRezervacija(rezervacije, prikaziOtkaz);
    }

    private static void ispisiTablicuRezervacija(List<Rezervacija> rezervacije, 
                                                 boolean prikaziOtkaz) {
        if (rezervacije.isEmpty()) {
            System.out.println("Nema rezervacija za prikaz.");
            return;
        }
        
        if (prikaziOtkaz) {
            System.out.printf("%-15s %-15s %-20s %-12s %-20s%n",
                "Ime", "Prezime", "Datum i vrijeme", "Vrsta", "Datum otkaza");
            System.out.println(String.format("%0" + 82 + "d", 0).replace("0", "-"));
        } else {
            System.out.printf("%-15s %-15s %-20s %-12s%n",
                "Ime", "Prezime", "Datum i vrijeme", "Vrsta");
            System.out.println(String.format("%0" + 62 + "d", 0).replace("0", "-"));
        }
        
        for (Rezervacija r : rezervacije) {
            if (prikaziOtkaz) {
                String datumOtkaza = r.getDatumVrijemeOtkaza() != null 
                    ? r.getDatumVrijemeOtkaza().toString() : "-";
                
                System.out.printf("%-15s %-15s %-20s %-12s %-20s%n",
                    r.getOsoba().getIme(),
                    r.getOsoba().getPrezime(),
                    r.getDatumVrijemePrijema().toString(),
                    r.getStanje().getOznaka(),
                    datumOtkaza
                );
            } else {
                System.out.printf("%-15s %-15s %-20s %-12s%n",
                    r.getOsoba().getIme(),
                    r.getOsoba().getPrezime(),
                    r.getDatumVrijemePrijema().toString(),
                    r.getStanje().getOznaka()
                );
            }
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
        
        if (rezervacije.isEmpty()) {
            System.out.println("Nema rezervacija za osobu " + ime + " " + prezime + ".");
            return;
        }
        
        System.out.printf("%-20s %-10s %-30s %-12s%n",
            "Datum i vrijeme", "Oznaka", "Naziv aranžmana", "Vrsta");
        System.out.println(String.format("%0" + 72 + "d", 0).replace("0", "-"));
        
        for (Rezervacija r : rezervacije) {
            Aranzman a = agencija.dohvatiAranzman(r.getOznakaAranzmana());
            String naziv = a != null ? skratiTekst(a.getNaziv(), 30) : "-";
            
            System.out.printf("%-20s %-10s %-30s %-12s%n",
            	r.getDatumVrijemePrijema().toString(),
                r.getOznakaAranzmana(),
                naziv,
                r.getStanje().getOznaka()
            );
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