package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.naredbe;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.singleton.TuristickaAgencija;

/**
 * Komanda za pretplatu na obavijesti o aranžmanu.
 * Koristi Observer pattern.
 */
public class PtarKomanda implements Komanda {
    
    private String ime;
    private String prezime;
    private String oznakaAranzmana;
    
    public PtarKomanda(String ime, String prezime, String oznakaAranzmana) {
        this.ime = ime;
        this.prezime = prezime;
        this.oznakaAranzmana = oznakaAranzmana;
    }
    
    @Override
    public boolean izvrsi() {
        System.out.println("PTAR " + ime + " " + prezime + " " + oznakaAranzmana);
        
        TuristickaAgencija agencija = TuristickaAgencija.getInstance();
        return agencija.prijaviPromatraca(ime, prezime, oznakaAranzmana);
    }
    
    @Override
    public String getOpis() {
        return "Pretplata na obavijesti za aranžman";
    }
}