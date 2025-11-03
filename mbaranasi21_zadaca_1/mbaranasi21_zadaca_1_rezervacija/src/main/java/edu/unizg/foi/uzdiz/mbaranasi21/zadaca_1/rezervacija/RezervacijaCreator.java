package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_1.rezervacija;

import java.time.LocalDateTime;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_1.model.Osoba;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_1.model.Rezervacija;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_1.model.StanjeRezervacije;

/**
 * Apstraktni kreator rezervacija.
 */
public abstract class RezervacijaCreator {
    
    /**
     * Factory Method - kreira rezervaciju.
     * Podklase implementiraju specifičnu logiku kreiranja.
     *
     * @param osoba Osoba koja rezervira
     * @param oznakaAranzmana Oznaka aranžmana
     * @param datumVrijeme Datum i vrijeme rezervacije
     * @param stanje Početno stanje rezervacije
     * @return Nova rezervacija
     */
    public abstract Rezervacija kreirajRezervaciju(Osoba osoba, 
                                                    String oznakaAranzmana,
                                                    LocalDateTime datumVrijeme, 
                                                    StanjeRezervacije stanje);
}