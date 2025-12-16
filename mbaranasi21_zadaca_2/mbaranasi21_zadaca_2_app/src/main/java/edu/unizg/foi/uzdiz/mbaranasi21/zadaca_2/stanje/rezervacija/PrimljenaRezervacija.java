package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.stanje.rezervacija;

import java.time.LocalDateTime;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.model.Rezervacija;

/**
 * ConcreteState - PRIMLJENA rezervacija.
 */
public class PrimljenaRezervacija implements RezervacijaStanje {
    
    @Override
    public void nova(Rezervacija rezervacija) {
        rezervacija.setStanje(new NovaRezervacija());
    }
    
    @Override
    public void primljena(Rezervacija rezervacija) {
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
        return "PRIMLJENA";
    }
}