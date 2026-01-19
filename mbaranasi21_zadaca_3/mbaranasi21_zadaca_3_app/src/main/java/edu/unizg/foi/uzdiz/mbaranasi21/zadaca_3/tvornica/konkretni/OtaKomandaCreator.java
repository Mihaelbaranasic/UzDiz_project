package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.tvornica.konkretni;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.naredbe.Komanda;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.naredbe.OtaKomanda;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.tvornica.KomandaCreator;

/**
 * ConcreteCreator za OTA komandu.
 * Format: OTA oznaka
 */
public class OtaKomandaCreator extends KomandaCreator {
    
    @Override
    public Komanda kreirajKomandu(String[] argumenti) {
        if (!provjeriArgumente(argumenti, 2)) {
            System.err.println("Format: OTA oznaka");
            return null;
        }
        
        String oznaka = argumenti[1];
        return new OtaKomanda(oznaka);
    }
}