package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.tvornica.konkretni;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.naredbe.IroKomanda;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.naredbe.Komanda;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.tvornica.KomandaCreator;

/**
 * ConcreteCreator za IRO komandu.
 */
public class IroKomandaCreator extends KomandaCreator {
    
    @Override
    public Komanda kreirajKomandu(String[] argumenti) {
        if (!provjeriArgumente(argumenti, 3)) {
            System.err.println("Format: IRO ime prezime");
            return null;
        }
        
        String ime = argumenti[1];
        String prezime = argumenti[2];
        
        return new IroKomanda(ime, prezime);
    }
}