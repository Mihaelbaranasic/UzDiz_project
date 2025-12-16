package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.tvornica.konkretni;

import java.time.LocalDate;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.lib.pomocne.DatumParser;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.naredbe.ItakKomanda;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.naredbe.Komanda;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.tvornica.KomandaCreator;

/**
 * ConcreteCreator za ITAK komandu.
 */
public class ItakKomandaCreator extends KomandaCreator {
    
    @Override
    public Komanda kreirajKomandu(String[] argumenti) {
        
        if (argumenti.length == 1) {
            return new ItakKomanda();
        } else if (argumenti.length >= 3) {
            LocalDate od = DatumParser.parsirajDatum(argumenti[1]);
            LocalDate do_ = DatumParser.parsirajDatum(argumenti[2]);
            
            if (od == null || do_ == null) {
                System.err.println("GREŠKA: Neispravan format datuma!");
                System.err.println("Format: ITAK dd.MM.yyyy. dd.MM.yyyy.");
                return null;
            }
            
            return new ItakKomanda(od, do_);
        } else {
            System.err.println("GREŠKA: Neispravan broj argumenata za ITAK!");
            System.err.println("Format: ITAK [datum_od datum_do]");
            return null;
        }
    }
}