package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.stanje.rezervacija;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.model.Rezervacija;

/**
 * Stanje NA_CEKANJU - broj prijava prešao max.
 */
public class NaCekanjuRezervacija implements RezervacijaStanje {
    
    @Override
    public boolean primljena(Rezervacija rezervacija) {
        // Na čekanju -> Primljena (NE MOŽE)
        return false;
    }
    
    @Override
    public boolean aktiviraj(Rezervacija rezervacija) {
        // Na čekanju -> Aktivna (DOZVOLJENO - oslobodi se mjesto)
        rezervacija.setStanje(new AktivnaRezervacija());
        return true;
    }
    
    @Override
    public boolean stavi_na_cekanje(Rezervacija rezervacija) {
        // Na čekanju -> Na čekanju (OSTAJE)
        return true;
    }
    
    @Override
    public boolean odgodi(Rezervacija rezervacija) {
        // Na čekanju -> Odgođena (NE MOŽE - nije aktivna)
        return false;
    }
    
    @Override
    public boolean otkazi(Rezervacija rezervacija) {
        // Na čekanju -> Otkazana (DOZVOLJENO)
        rezervacija.setStanje(new OtkazanaRezervacija());
        return true;
    }
    
    @Override
    public String getNazivStanja() {
        return "NA_CEKANJU";
    }
}