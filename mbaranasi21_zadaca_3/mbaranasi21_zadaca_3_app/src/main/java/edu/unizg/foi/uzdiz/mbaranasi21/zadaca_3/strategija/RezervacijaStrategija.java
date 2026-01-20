package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.strategija;

import java.util.List;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.model.Aranzman;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.model.Osoba;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.model.Rezervacija;

/**
 * Strategy - apstraktna klasa za upravljanje dozvoljenim brojem rezervacija.
 * Prema profesorovim materijalima - Strategy pattern.
 */
public abstract class RezervacijaStrategija {
    
    /**
     * Provjerava je li dozvoljena nova rezervacija za osobu.
     * 
     * @param osoba Osoba koja rezervira
     * @param aranzman Aranžman koji se rezervira
     * @param sveRezervacije Sve rezervacije u sustavu
     * @return true ako je rezervacija dozvoljena, false inače
     */
    public abstract boolean dozvoliRezervaciju(Osoba osoba, Aranzman aranzman, 
                                               List<Rezervacija> sveRezervacije);
    
    /**
     * Vraća naziv strategije.
     * 
     * @return Naziv strategije
     */
    public abstract String getNaziv();
}