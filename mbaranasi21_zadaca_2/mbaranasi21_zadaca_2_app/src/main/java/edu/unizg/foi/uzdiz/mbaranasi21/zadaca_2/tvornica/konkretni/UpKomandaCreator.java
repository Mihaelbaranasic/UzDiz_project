package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.tvornica.konkretni;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.naredbe.Komanda;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.naredbe.UpKomanda;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.tvornica.KomandaCreator;

/**
 * ConcreteCreator za UP komandu.
 * Format: UP datoteka_aranzmana datoteka_rezervacija
 */
public class UpKomandaCreator extends KomandaCreator {
    
    @Override
    public Komanda kreirajKomandu(String[] argumenti) {
        if (!provjeriArgumente(argumenti, 3)) {
            System.err.println("Format: UP datoteka_aranzmana datoteka_rezervacija");
            return null;
        }
        
        String datotekaAranzmana = argumenti[1];
        String datotekaRezervacija = argumenti[2];
        
        return new UpKomanda(datotekaAranzmana, datotekaRezervacija);
    }
}