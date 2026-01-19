package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.tvornica.konkretni;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.naredbe.BpKomanda;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.naredbe.Komanda;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.tvornica.KomandaCreator;

/**
 * ConcreteCreator za BP komandu.
 * Format: BP (nema dodatnih argumenata)
 */
public class BpKomandaCreator extends KomandaCreator {
    
    @Override
    public Komanda kreirajKomandu(String[] argumenti) {
        return new BpKomanda();
    }
}