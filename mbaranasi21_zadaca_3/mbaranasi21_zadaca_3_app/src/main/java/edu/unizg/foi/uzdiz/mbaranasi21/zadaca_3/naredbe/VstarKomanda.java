package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.naredbe;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.singleton.TuristickaAgencija;

/**
 * Komanda za vraćanje zadnje spremljenog stanja aranžmana i njegovih rezervacija.
 * Format: VSTAR oznaka
 * Koristi Memento pattern.
 */
public class VstarKomanda implements Komanda {
    
    private String oznakaAranzmana;
    
    public VstarKomanda(String oznakaAranzmana) {
        this.oznakaAranzmana = oznakaAranzmana;
    }
    
    @Override
    public boolean izvrsi() {
        System.out.println("VSTAR " + oznakaAranzmana);
        
        TuristickaAgencija agencija = TuristickaAgencija.getInstance();
        return agencija.vratiStanjeAranzmana(oznakaAranzmana);
    }
    
    @Override
    public String getOpis() {
        return "Vraćanje stanja aranžmana iz spremišta";
    }
}