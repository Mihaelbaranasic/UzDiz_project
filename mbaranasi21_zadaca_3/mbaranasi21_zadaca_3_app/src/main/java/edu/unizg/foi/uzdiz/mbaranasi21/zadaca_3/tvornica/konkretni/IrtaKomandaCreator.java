package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.tvornica.konkretni;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.naredbe.IrtaKomanda;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.naredbe.Komanda;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.tvornica.KomandaCreator;

/**
 * ConcreteCreator za IRTA komandu.
 */
public class IrtaKomandaCreator extends KomandaCreator {
    
    @Override
    public Komanda kreirajKomandu(String[] argumenti) {
        if (!provjeriArgumente(argumenti, 2)) {
            System.err.println("Format: IRTA oznaka [PA|Č|O|PAČO]");
            return null;
        }
        
        String oznaka = argumenti[1];
        
        if (argumenti.length >= 3) {
            String filter = argumenti[2];
            return new IrtaKomanda(oznaka, filter);
        } else {
            return new IrtaKomanda(oznaka);
        }
    }
}