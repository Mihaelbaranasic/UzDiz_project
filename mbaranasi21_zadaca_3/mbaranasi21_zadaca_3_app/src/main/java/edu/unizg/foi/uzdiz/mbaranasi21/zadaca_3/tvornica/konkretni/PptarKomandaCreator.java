package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.tvornica.konkretni;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.naredbe.Komanda;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.naredbe.PptarKomanda;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.tvornica.KomandaCreator;

/**
 * ConcreteCreator za PPTAR komandu.
 * Format: PPTAR [A|R] riječ
 */
public class PptarKomandaCreator extends KomandaCreator {
    
    @Override
    public Komanda kreirajKomandu(String[] argumenti) {
        if (argumenti.length < 3) {
            throw new IllegalArgumentException(
                "PPTAR komanda zahtijeva 2 argumenta: PPTAR [A|R] riječ");
        }
        
        String tip = argumenti[1];
        String kljucnaRijec = argumenti[2];
        
        return new PptarKomanda(tip, kljucnaRijec);
    }
}