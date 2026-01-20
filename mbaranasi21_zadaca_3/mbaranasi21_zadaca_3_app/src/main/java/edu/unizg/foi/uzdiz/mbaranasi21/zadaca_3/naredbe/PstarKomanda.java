package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.naredbe;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.singleton.TuristickaAgencija;

/**
 * Komanda za pohranjivanje stanja aranžmana i njegovih rezervacija.
 * Format: PSTAR oznaka
 * Koristi Memento pattern.
 */
public class PstarKomanda implements Komanda {
    
    private String oznakaAranzmana;
    
    public PstarKomanda(String oznakaAranzmana) {
        this.oznakaAranzmana = oznakaAranzmana;
    }
    
    @Override
    public boolean izvrsi() {
        System.out.println("PSTAR " + oznakaAranzmana);
        
        TuristickaAgencija agencija = TuristickaAgencija.getInstance();
        return agencija.spremiStanjeAranzmana(oznakaAranzmana);
    }
    
    @Override
    public String getOpis() {
        return "Pohranjivanje stanja aranžmana";
    }
}