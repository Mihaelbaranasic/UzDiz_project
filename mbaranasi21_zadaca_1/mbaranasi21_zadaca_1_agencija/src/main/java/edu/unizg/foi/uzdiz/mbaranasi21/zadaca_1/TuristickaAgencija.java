package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_1;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_1.model.Aranzman;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_1.model.Osoba;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_1.model.Rezervacija;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_1.model.StanjeRezervacije;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_1.rezervacija.RezervacijaCreator;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_1.rezervacija.StandardniRezervacijaCreator;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Singleton klasa koja upravlja cijelim sustavom turističke agencije.
 * 
 * <p>SINGLETON UZORAK - osigurava da postoji samo jedna instanca
 * turističke agencije u cijelom sustavu.</p>
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
     * Dohvaća jedinstvenu instancu turističke agencije.
     * Koristi double-checked locking za thread safety.
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
     * Kreira i dodaje novu rezervaciju koristeći Factory Method.
     *
     * @param osoba Osoba koja rezervira
     * @param oznakaAranzmana Oznaka aranžmana
     * @param datumVrijeme Datum i vrijeme rezervacije
     * @return true ako je rezervacija uspješno dodana, inače false
     */
    public boolean dodajNovuRezervaciju(Osoba osoba, String oznakaAranzmana,
                                        LocalDateTime datumVrijeme) {
        if (!aranzmani.containsKey(oznakaAranzmana)) {
            return false;
        }
        
        Rezervacija rezervacija = rezervacijaCreator.kreirajRezervaciju(
            osoba, oznakaAranzmana, datumVrijeme, StanjeRezervacije.PRIMLJENA
        );
        
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
        return rezervacijePoAranzmanu.getOrDefault(oznakaAranzmana, 
                                                   new ArrayList<>());
    }
    
    /**
     * Dohvaća rezervacije za aranžman prema stanju.
     *
     * @param oznakaAranzmana Oznaka aranžmana
     * @param stanje Stanje rezervacije
     * @return Lista rezervacija u određenom stanju
     */
    public List<Rezervacija> dohvatiRezervacijePoStanju(String oznakaAranzmana, 
                                                         StanjeRezervacije stanje) {
        return dohvatiRezervacije(oznakaAranzmana).stream()
            .filter(r -> r.getStanje() == stanje)
            .collect(Collectors.toList());
    }
    
    /**
     * Dohvaća sve rezervacije za osobu.
     *
     * @param ime Ime osobe
     * @param prezime Prezime osobe
     * @return Lista rezervacija za tu osobu
     */
    public List<Rezervacija> dohvatiRezervacijeOsobe(String ime, String prezime) {
        List<Rezervacija> rezultat = new ArrayList<>();
        
        for (List<Rezervacija> lista : rezervacijePoAranzmanu.values()) {
            for (Rezervacija r : lista) {
                if (r.getOsoba().getIme().equals(ime) && 
                    r.getOsoba().getPrezime().equals(prezime)) {
                    rezultat.add(r);
                }
            }
        }
        
        return rezultat;
    }
    
    /**
     * Otkazuje rezervaciju za osobu.
     *
     * @param ime Ime osobe
     * @param prezime Prezime osobe
     * @param oznakaAranzmana Oznaka aranžmana
     * @param datumVrijeme Datum i vrijeme otkaza
     * @return true ako je rezervacija otkazana, inače false
     */
    public boolean otkaziRezervaciju(String ime, String prezime, 
                                     String oznakaAranzmana,
                                     LocalDateTime datumVrijeme) {
        List<Rezervacija> rezervacije = dohvatiRezervacije(oznakaAranzmana);
        
        for (Rezervacija r : rezervacije) {
            if (r.getOsoba().getIme().equals(ime) && 
                r.getOsoba().getPrezime().equals(prezime) &&
                !r.jeOtkazana()) {
                r.otkazi(datumVrijeme);
                azurirajStanjaRezervacija(oznakaAranzmana);
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Ažurira stanja svih rezervacija za aranžman prema pravilima.
     *
     * @param oznakaAranzmana Oznaka aranžmana
     */
    private void azurirajStanjaRezervacija(String oznakaAranzmana) {
        Aranzman aranzman = dohvatiAranzman(oznakaAranzmana);
        if (aranzman == null) {
            return;
        }
        
        List<Rezervacija> rezervacije = dohvatiRezervacije(oznakaAranzmana);
        List<Rezervacija> aktivneIPrimljene = rezervacije.stream()
            .filter(r -> r.jeAktivna() || r.jePrimljena())
            .collect(Collectors.toList());
        
        int brojRezervacija = aktivneIPrimljene.size();
        
        if (brojRezervacija >= aranzman.getMinBrojPutnika()) {
            for (int i = 0; i < aktivneIPrimljene.size(); i++) {
                Rezervacija rez = aktivneIPrimljene.get(i);
                
                if (i < aranzman.getMaksBrojPutnika()) {
                    rez.postaviStanje(StanjeRezervacije.AKTIVNA);
                } else {
                    rez.postaviStanje(StanjeRezervacije.NA_CEKANJU);
                }
            }
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