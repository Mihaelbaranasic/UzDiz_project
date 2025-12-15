package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.stanje.rezervacija;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.model.Rezervacija;

/**
 * Stanje PRIMLJENA - broj prijava nije dostigao min putnika.
 */
public class PrimljenaRezervacija implements RezervacijaStanje {
    
    @Override
    public boolean primljena(Rezervacija rezervacija) {
        // Primljena -> Primljena (OSTAJE)
        return true;
    }
    
    @Override
    public boolean aktiviraj(Rezervacija rezervacija) {
        // Primljena -> Aktivna (DOZVOLJENO - dostignut min)
        rezervacija.setStanje(new AktivnaRezervacija());
        return true;
    }
    
    @Override
    public boolean stavi_na_cekanje(Rezervacija rezervacija) {
        // Primljena -> Na čekanju (NE MOŽE direktno)
        return false;
    }
    
    @Override
    public boolean odgodi(Rezervacija rezervacija) {
        // Primljena -> Odgođena (NE MOŽE - nema aktivnih)
        return false;
    }
    
    @Override
    public boolean otkazi(Rezervacija rezervacija) {
        // Primljena -> Otkazana (DOZVOLJENO)
        rezervacija.setStanje(new OtkazanaRezervacija());
        return true;
    }
    
    @Override
    public String getNazivStanja() {
        return "PRIMLJENA";
    }
}