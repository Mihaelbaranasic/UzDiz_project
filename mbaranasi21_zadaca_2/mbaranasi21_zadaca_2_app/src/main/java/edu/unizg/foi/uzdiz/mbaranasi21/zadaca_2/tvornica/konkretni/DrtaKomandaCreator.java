package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.tvornica.konkretni;

import java.time.LocalDateTime;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.lib.pomocne.DatumParser;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.naredbe.DrtaKomanda;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.naredbe.Komanda;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.tvornica.KomandaCreator;

/**
 * ConcreteCreator za DRTA komandu.
 */
public class DrtaKomandaCreator extends KomandaCreator {
    
    @Override
    public Komanda kreirajKomandu(String[] argumenti) {
        if (!provjeriArgumente(argumenti, 6)) {
            System.err.println("Format: DRTA ime prezime oznaka datum vrijeme");
            return null;
        }
        
        String ime = argumenti[1];
        String prezime = argumenti[2];
        String oznaka = argumenti[3];
        String datumVrijemeTekst = argumenti[4] + " " + argumenti[5];
        
        LocalDateTime datumVrijeme = DatumParser.parsirajDatumVrijeme(datumVrijemeTekst);
        if (datumVrijeme == null) {
            System.err.println("GREŠKA: Neispravan format datuma/vremena!");
            System.err.println("Format: dd.MM.yyyy. hh:mm:ss");
            return null;
        }
        
        return new DrtaKomanda(ime, prezime, oznaka, datumVrijeme);
    }
}