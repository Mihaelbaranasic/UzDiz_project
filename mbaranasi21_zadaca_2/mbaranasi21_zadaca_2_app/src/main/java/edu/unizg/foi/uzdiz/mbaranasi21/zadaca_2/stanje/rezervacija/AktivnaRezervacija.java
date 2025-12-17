package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.stanje.rezervacija;

import java.time.LocalDateTime;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.model.Rezervacija;

/**
 * ConcreteState - AKTIVNA rezervacija.
 */
public class AktivnaRezervacija implements RezervacijaStanje {
    
    @Override
    public void nova(Rezervacija rezervacija) {
        System.err.println("Aktivna rezervacija ne može postati nova!");
    }
    
    @Override
    public void primljena(Rezervacija rezervacija) {
        rezervacija.setStanje(new PrimljenaRezervacija());
    }
    
    @Override
    public void aktiviraj(Rezervacija rezervacija) {
    }
    
    @Override
    public void staviNaCekanje(Rezervacija rezervacija) {
        rezervacija.setStanje(new NaCekanjuRezervacija());
    }
    
    @Override
    public void odgodi(Rezervacija rezervacija) {
        rezervacija.setStanje(new OdgodenaRezervacija());
    }
    
    @Override
    public void otkazi(Rezervacija rezervacija, LocalDateTime datumOtkaza) {
        rezervacija.setDatumOtkaza(datumOtkaza);
        rezervacija.setStanje(new OtkazanaRezervacija());
    }
    
    @Override
    public String getNazivStanja() {
        return "AKTIVNA";
    }
}