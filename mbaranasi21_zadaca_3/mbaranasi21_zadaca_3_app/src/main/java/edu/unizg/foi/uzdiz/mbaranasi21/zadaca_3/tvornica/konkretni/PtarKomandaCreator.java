package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.tvornica.konkretni;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.naredbe.Komanda;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.naredbe.PtarKomanda;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.tvornica.KomandaCreator;

/**
 * ConcreteCreator za PTAR komandu.
 */
public class PtarKomandaCreator extends KomandaCreator {
    
    @Override
    public Komanda kreirajKomandu(String[] argumenti) {
        if (!provjeriArgumente(argumenti, 4)) {
            System.err.println("Format: PTAR ime prezime oznaka");
            return null;
        }
        
        String ime = argumenti[1];
        String prezime = argumenti[2];
        String oznaka = argumenti[3];
        
        return new PtarKomanda(ime, prezime, oznaka);
    }
}