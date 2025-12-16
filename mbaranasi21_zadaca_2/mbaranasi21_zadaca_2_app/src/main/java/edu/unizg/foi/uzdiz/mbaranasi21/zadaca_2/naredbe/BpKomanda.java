package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.naredbe;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.singleton.TuristickaAgencija;

/**
 * Komanda za brisanje svih podataka iz sustava.
 * Resetira TuristickaAgencija Singleton.
 */
public class BpKomanda implements Komanda {
    
    @Override
    public boolean izvrsi() {
        TuristickaAgencija agencija = TuristickaAgencija.getInstance();
        agencija.resetiraj();
        
        System.out.println("Svi podaci uspješno obrisani.");
        return true;
    }
    
    @Override
    public String getOpis() {
        return "Brisanje svih podataka iz sustava";
    }
}