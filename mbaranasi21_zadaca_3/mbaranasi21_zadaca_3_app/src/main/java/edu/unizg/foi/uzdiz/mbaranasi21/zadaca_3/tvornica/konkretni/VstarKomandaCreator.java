package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.tvornica.konkretni;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.naredbe.Komanda;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.naredbe.VstarKomanda;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.tvornica.KomandaCreator;

/**
 * ConcreteCreator za VSTAR komandu.
 * Format: VSTAR oznaka
 */
public class VstarKomandaCreator extends KomandaCreator {
    
    @Override
    public Komanda kreirajKomandu(String[] argumenti) {
        if (argumenti.length < 2) {
            throw new IllegalArgumentException(
                "VSTAR komanda zahtijeva 1 argument: VSTAR oznaka");
        }
        
        String oznaka = argumenti[1];
        return new VstarKomanda(oznaka);
    }
}