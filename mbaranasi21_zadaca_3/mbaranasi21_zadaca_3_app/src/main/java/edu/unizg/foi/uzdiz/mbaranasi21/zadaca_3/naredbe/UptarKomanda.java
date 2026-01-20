package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.naredbe;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.singleton.TuristickaAgencija;

/**
 * Komanda za ukidanje pretplate na obavijesti.
 * Koristi Observer pattern.
 */
public class UptarKomanda implements Komanda {
    
    private String ime;
    private String prezime;
    private String oznakaAranzmana;
    private boolean ukiniSve;
    
    /**
     * Konstruktor za ukidanje pojedinačne pretplate.
     */
    public UptarKomanda(String ime, String prezime, String oznakaAranzmana) {
        this.ime = ime;
        this.prezime = prezime;
        this.oznakaAranzmana = oznakaAranzmana;
        this.ukiniSve = false;
    }
    
    /**
     * Konstruktor za ukidanje svih pretplata za aranžman.
     */
    public UptarKomanda(String oznakaAranzmana) {
        this.oznakaAranzmana = oznakaAranzmana;
        this.ukiniSve = true;
    }
    
    @Override
    public boolean izvrsi() {
        if (ukiniSve) {
            System.out.println("UPTAR " + oznakaAranzmana);
        } else {
            System.out.println("UPTAR " + ime + " " + prezime + " " + oznakaAranzmana);
        }
        
        TuristickaAgencija agencija = TuristickaAgencija.getInstance();
        
        if (ukiniSve) {
            return agencija.odjaviSvePromatraceZaAranzman(oznakaAranzmana);
        } else {
            return agencija.odjaviPromatraca(ime, prezime, oznakaAranzmana);
        }
    }
    
    @Override
    public String getOpis() {
        return "Ukidanje pretplate na obavijesti";
    }
}