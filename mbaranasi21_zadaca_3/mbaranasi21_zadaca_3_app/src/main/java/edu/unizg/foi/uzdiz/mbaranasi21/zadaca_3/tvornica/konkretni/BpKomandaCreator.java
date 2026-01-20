package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.tvornica.konkretni;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.naredbe.BpKomanda;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.naredbe.Komanda;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.tvornica.KomandaCreator;

/**
 * ConcreteCreator za BP komandu.
 * Format: BP [A|R]
 */
public class BpKomandaCreator extends KomandaCreator {
    
    @Override
    public Komanda kreirajKomandu(String[] argumenti) {
        if (argumenti.length < 2) {
            throw new IllegalArgumentException(
                "BP komanda zahtijeva 1 argument: BP [A|R]");
        }
        
        String tip = argumenti[1];
        return new BpKomanda(tip);
    }
}