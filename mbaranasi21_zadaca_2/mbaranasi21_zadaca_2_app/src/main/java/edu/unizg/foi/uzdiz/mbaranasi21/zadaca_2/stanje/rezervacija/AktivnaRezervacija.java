package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.stanje.rezervacija;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.model.Rezervacija;

/**
 * Stanje AKTIVNA - broj prijava dostigao min, nije prešao max.
 */
public class AktivnaRezervacija implements RezervacijaStanje {
    
    @Override
    public boolean primljena(Rezervacija rezervacija) {
        rezervacija.setStanje(new PrimljenaRezervacija());
        return true;
    }
    
    @Override
    public boolean aktiviraj(Rezervacija rezervacija) {
        // Aktivna -> Aktivna (OSTAJE)
        return true;
    }
    
    @Override
    public boolean stavi_na_cekanje(Rezervacija rezervacija) {
        // Aktivna -> Na čekanju (DOZVOLJENO - ako se drugi aktivira)
        rezervacija.setStanje(new NaCekanjuRezervacija());
        return true;
    }
    
    @Override
    public boolean odgodi(Rezervacija rezervacija) {
        // Aktivna -> Odgođena (DOZVOLJENO - preklapanje)
        rezervacija.setStanje(new OdgodenaRezervacija());
        return true;
    }
    
    @Override
    public boolean otkazi(Rezervacija rezervacija) {
        // Aktivna -> Otkazana (DOZVOLJENO)
        rezervacija.setStanje(new OtkazanaRezervacija());
        return true;
    }
    
    @Override
    public String getNazivStanja() {
        return "AKTIVNA";
    }
}