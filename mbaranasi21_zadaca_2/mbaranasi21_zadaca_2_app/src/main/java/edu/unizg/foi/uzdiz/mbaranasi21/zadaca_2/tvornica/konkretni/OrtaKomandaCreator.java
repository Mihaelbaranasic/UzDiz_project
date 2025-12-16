package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.tvornica.konkretni;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.naredbe.OrtaKomanda;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.naredbe.Komanda;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.tvornica.KomandaCreator;

/**
 * ConcreteCreator za ORTA komandu.
 */
public class OrtaKomandaCreator extends KomandaCreator {
    
    @Override
    public Komanda kreirajKomandu(String[] argumenti) {
        if (!provjeriArgumente(argumenti, 4)) {
            System.err.println("Format: ORTA ime prezime oznaka");
            return null;
        }
        
        String ime = argumenti[1];
        String prezime = argumenti[2];
        String oznaka = argumenti[3];
        
        return new OrtaKomanda(ime, prezime, oznaka);
    }
}