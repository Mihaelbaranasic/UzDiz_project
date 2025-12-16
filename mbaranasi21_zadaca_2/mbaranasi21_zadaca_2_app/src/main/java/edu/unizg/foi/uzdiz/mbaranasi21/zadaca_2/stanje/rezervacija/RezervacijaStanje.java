package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.stanje.rezervacija;

import java.time.LocalDateTime;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.model.Rezervacija;

/**
 * State interface za Rezervacija State pattern.
 */
public interface RezervacijaStanje {
    
    void nova(Rezervacija rezervacija);
    void primljena(Rezervacija rezervacija);
    void aktiviraj(Rezervacija rezervacija);
    void staviNaCekanje(Rezervacija rezervacija);
    void odgodi(Rezervacija rezervacija);
    void otkazi(Rezervacija rezervacija, LocalDateTime datumOtkaza);
    
    /**
     * Vraća naziv stanja.
     */
    String getNazivStanja();
}