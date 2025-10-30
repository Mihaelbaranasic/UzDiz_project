package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_1.rezervacija;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_1.model.Osoba;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_1.model.Rezervacija;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_1.model.StanjeRezervacije;
import java.time.LocalDateTime;

/**
 * Standardni kreator rezervacija.
 * 
 * <p>Konkretna implementacija Factory Method uzorka koja kreira
 * standardne rezervacije.</p>
 */
public class StandardniRezervacijaCreator extends RezervacijaCreator {
    
    /**
     * Kreira standardnu rezervaciju sa zadanim parametrima.
     *
     * @param osoba Osoba koja rezervira
     * @param oznakaAranzmana Oznaka aranžmana
     * @param datumVrijeme Datum i vrijeme rezervacije
     * @param stanje Početno stanje rezervacije
     * @return Nova rezervacija
     */
    @Override
    public Rezervacija kreirajRezervaciju(Osoba osoba, 
                                          String oznakaAranzmana,
                                          LocalDateTime datumVrijeme, 
                                          StanjeRezervacije stanje) {
        return new Rezervacija(osoba, oznakaAranzmana, datumVrijeme, stanje);
    }
}