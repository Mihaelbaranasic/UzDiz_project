package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.stanje.rezervacija;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.model.Rezervacija;

/**
 * Stanje ODGOĐENA - osoba ima drugu raniju aktivnu rezervaciju.
 */
public class OdgodenaRezervacija implements RezervacijaStanje {
    
    @Override
    public boolean primljena(Rezervacija rezervacija) {
        // Odgođena -> Primljena (NE MOŽE)
        return false;
    }
    
    @Override
    public boolean aktiviraj(Rezervacija rezervacija) {
        // Odgođena -> Aktivna (DOZVOLJENO - ako se ranije otkaže)
        rezervacija.setStanje(new AktivnaRezervacija());
        return true;
    }
    
    @Override
    public boolean stavi_na_cekanje(Rezervacija rezervacija) {
        // Odgođena -> Na čekanju (NE MOŽE)
        return false;
    }
    
    @Override
    public boolean odgodi(Rezervacija rezervacija) {
        // Odgođena -> Odgođena (OSTAJE)
        return true;
    }
    
    @Override
    public boolean otkazi(Rezervacija rezervacija) {
        // Odgođena -> Otkazana (DOZVOLJENO)
        rezervacija.setStanje(new OtkazanaRezervacija());
        return true;
    }
    
    @Override
    public String getNazivStanja() {
        return "ODGOĐENA";
    }
}