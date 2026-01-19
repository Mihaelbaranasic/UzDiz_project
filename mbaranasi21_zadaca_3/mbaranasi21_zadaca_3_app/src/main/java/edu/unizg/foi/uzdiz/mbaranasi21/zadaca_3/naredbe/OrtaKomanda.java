package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.naredbe;

import java.time.LocalDateTime;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.model.Aranzman;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.singleton.TuristickaAgencija;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.stanje.aranzman.AranzmanStanje;

/**
 * Komanda za otkaz rezervacije.
 */
public class OrtaKomanda implements Komanda {
    
    private String ime;
    private String prezime;
    private String oznaka;
    
    public OrtaKomanda(String ime, String prezime, String oznaka) {
        this.ime = ime;
        this.prezime = prezime;
        this.oznaka = oznaka;
    }
    
    @Override
    public boolean izvrsi() {
        TuristickaAgencija agencija = TuristickaAgencija.getInstance();
        
        Aranzman aranzman = agencija.dohvatiAranzman(oznaka);
        if (aranzman == null) {
            System.err.println("GREŠKA: Aranžman '" + oznaka + "' ne postoji!");
            return false;
        }
        
        AranzmanStanje stanje = agencija.dohvatiStanjeAranzmana(oznaka);
        if (stanje != null && stanje.getNazivStanja().equals("OTKAZAN")) {
            System.err.println("GREŠKA: Aranžman '" + oznaka 
                + "' je otkazan. Ne mogu se otkazivati rezervacije.");
            return false;
        }
        
        LocalDateTime datumOtkaza = LocalDateTime.now();
        boolean uspjeh = agencija.otkaziRezervaciju(ime, prezime, oznaka, datumOtkaza);
        
        if (uspjeh) {
            System.out.println("Rezervacija korisnika " + ime + " " + prezime 
                + " za aranžman " + oznaka + " uspješno otkazana.");
        }
        
        return uspjeh;
    }
    
    @Override
    public String getOpis() {
        return "Otkaz rezervacije turističkog aranžmana";
    }
}