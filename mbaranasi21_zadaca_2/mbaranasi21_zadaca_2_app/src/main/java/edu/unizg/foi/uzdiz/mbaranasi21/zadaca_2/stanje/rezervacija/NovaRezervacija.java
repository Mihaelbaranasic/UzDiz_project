package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.stanje.rezervacija;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.model.Rezervacija;

/**
 * Stanje NOVA - inicijalno stanje rezervacije.
 */
public class NovaRezervacija implements RezervacijaStanje {
    
    @Override
    public boolean primljena(Rezervacija rezervacija) {
        // Nova -> Primljena (DOZVOLJENO)
        rezervacija.setStanje(new PrimljenaRezervacija());
        return true;
    }
    
    @Override
    public boolean aktiviraj(Rezervacija rezervacija) {
        // Nova -> Aktivna (NE MOŽE direktno)
        return false;
    }
    
    @Override
    public boolean stavi_na_cekanje(Rezervacija rezervacija) {
        // Nova -> Na čekanju (NE MOŽE direktno)
        return false;
    }
    
    @Override
    public boolean odgodi(Rezervacija rezervacija) {
        // Nova -> Odgođena (NE MOŽE direktno)
        return false;
    }
    
    @Override
    public boolean otkazi(Rezervacija rezervacija) {
        // Nova -> Otkazana (DOZVOLJENO)
        rezervacija.setStanje(new OtkazanaRezervacija());
        return true;
    }
    
    @Override
    public String getNazivStanja() {
        return "NOVA";
    }
}