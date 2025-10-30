package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_1.rezervacija;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_1.model.Osoba;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_1.model.Rezervacija;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_1.model.StanjeRezervacije;
import java.time.LocalDateTime;

/**
 * Apstraktni kreator rezervacija.
 * 
 * <p>FACTORY METHOD UZORAK - definira sučelje za kreiranje rezervacija,
 * ali dopušta podklasama da odluče kako kreirati konkretnu rezervaciju.</p>
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