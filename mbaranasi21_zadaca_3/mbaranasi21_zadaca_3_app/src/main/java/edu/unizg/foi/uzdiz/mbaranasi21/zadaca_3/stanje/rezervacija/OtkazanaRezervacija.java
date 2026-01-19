package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.stanje.rezervacija;

import java.time.LocalDateTime;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.model.Rezervacija;

/**
 * ConcreteState - OTKAZANA rezervacija.
 */
public class OtkazanaRezervacija implements RezervacijaStanje {
    
    @Override
    public void nova(Rezervacija rezervacija) {
        System.err.println("Otkazana rezervacija ne može postati nova!");
    }
    
    @Override
    public void primljena(Rezervacija rezervacija) {
        System.err.println("Otkazana rezervacija ne može postati primljena!");
    }
    
    @Override
    public void aktiviraj(Rezervacija rezervacija) {
        System.err.println("Otkazana rezervacija ne može postati aktivna!");
    }
    
    @Override
    public void staviNaCekanje(Rezervacija rezervacija) {
        System.err.println("Otkazana rezervacija ne može biti na čekanju!");
    }
    
    @Override
    public void odgodi(Rezervacija rezervacija) {
        System.err.println("Otkazana rezervacija ne može biti odgođena!");
    }
    
    @Override
    public void otkazi(Rezervacija rezervacija, LocalDateTime datumOtkaza) {
    }
    
    @Override
    public String getNazivStanja() {
        return "OTKAZANA";
    }
}