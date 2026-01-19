package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.tvornica.konkretni;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.naredbe.IpKomanda;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.naredbe.Komanda;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.tvornica.KomandaCreator;

/**
 * ConcreteCreator za IP komandu.
 * Format: IP kriterij
 */
public class IpKomandaCreator extends KomandaCreator {
    
    @Override
    public Komanda kreirajKomandu(String[] argumenti) {
        if (!provjeriArgumente(argumenti, 2)) {
            System.err.println("Format: IP kriterij");
            System.err.println("  A = Abeceda");
            System.err.println("  D = Datum");
            System.err.println("  C = Cijena");
            return null;
        }
        
        String kriterij = argumenti[1];
        return new IpKomanda(kriterij);
    }
}