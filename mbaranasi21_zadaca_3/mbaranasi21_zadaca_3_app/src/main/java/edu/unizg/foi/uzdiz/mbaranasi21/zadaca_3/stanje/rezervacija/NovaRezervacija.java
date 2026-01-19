package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.stanje.rezervacija;

import java.time.LocalDateTime;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.model.Rezervacija;

/**
 * ConcreteState - NOVA rezervacija.
 */
public class NovaRezervacija implements RezervacijaStanje {
    
    @Override
    public void nova(Rezervacija rezervacija) {
    }
    
    @Override
    public void primljena(Rezervacija rezervacija) {
        rezervacija.setStanje(new PrimljenaRezervacija());
    }
    
    @Override
    public void aktiviraj(Rezervacija rezervacija) {
        rezervacija.setStanje(new AktivnaRezervacija());
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
        return "NOVA";
    }
}