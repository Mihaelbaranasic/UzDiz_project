package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.tvornica.konkretni;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.naredbe.ItapKomanda;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.naredbe.Komanda;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.tvornica.KomandaCreator;

/**
 * ConcreteCreator za ITAP komandu.
 */
public class ItapKomandaCreator extends KomandaCreator {
    
    @Override
    public Komanda kreirajKomandu(String[] argumenti) {
        if (!provjeriArgumente(argumenti, 2)) {
            System.err.println("Format: ITAP oznaka");
            return null;
        }
        
        String oznaka = argumenti[1];
        return new ItapKomanda(oznaka);
    }
}