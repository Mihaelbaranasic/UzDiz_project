package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.tvornica.konkretni;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.naredbe.Komanda;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.naredbe.PstarKomanda;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.tvornica.KomandaCreator;

/**
 * ConcreteCreator za PSTAR komandu.
 * Format: PSTAR oznaka
 */
public class PstarKomandaCreator extends KomandaCreator {
    
    @Override
    public Komanda kreirajKomandu(String[] argumenti) {
        if (argumenti.length < 2) {
            throw new IllegalArgumentException(
                "PSTAR komanda zahtijeva 1 argument: PSTAR oznaka");
        }
        
        String oznaka = argumenti[1];
        return new PstarKomanda(oznaka);
    }
}