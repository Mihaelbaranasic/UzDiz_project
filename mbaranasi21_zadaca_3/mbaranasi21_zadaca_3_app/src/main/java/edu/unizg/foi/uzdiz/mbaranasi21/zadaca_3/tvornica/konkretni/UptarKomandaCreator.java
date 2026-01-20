package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.tvornica.konkretni;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.naredbe.Komanda;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.naredbe.UptarKomanda;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.tvornica.KomandaCreator;

/**
 * ConcreteCreator za UPTAR komandu.
 */
public class UptarKomandaCreator extends KomandaCreator {
    
    @Override
    public Komanda kreirajKomandu(String[] argumenti) {
        if (argumenti.length == 2) {
            String oznaka = argumenti[1];
            return new UptarKomanda(oznaka);
        } else if (argumenti.length == 4) {
            String ime = argumenti[1];
            String prezime = argumenti[2];
            String oznaka = argumenti[3];
            return new UptarKomanda(ime, prezime, oznaka);
        } else {
            System.err.println("Format: UPTAR [ime prezime oznaka] | [oznaka]");
            return null;
        }
    }
}