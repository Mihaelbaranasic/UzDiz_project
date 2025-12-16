package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.tvornica.konkretni;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.naredbe.ItasKomanda;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.naredbe.Komanda;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.tvornica.KomandaCreator;

/**
 * ConcreteCreator za ITAS komandu.
 * Format: ITAS [oznaka]
 */
public class ItasKomandaCreator extends KomandaCreator {
    
    @Override
    public Komanda kreirajKomandu(String[] argumenti) {
        if (argumenti.length >= 2) {
            String oznaka = argumenti[1];
            return new ItasKomanda(oznaka);
        } else {
            return new ItasKomanda(null);
        }
    }
}