package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.stanje.rezervacija;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.model.Rezervacija;

/**
 * Stanje OTKAZANA - rezervacija je otkazana.
 */
public class OtkazanaRezervacija implements RezervacijaStanje {
    
    @Override
    public boolean primljena(Rezervacija rezervacija) {
        // Otkazana -> Primljena (NE MOŽE)
        return false;
    }
    
    @Override
    public boolean aktiviraj(Rezervacija rezervacija) {
        // Otkazana -> Aktivna (NE MOŽE)
        return false;
    }
    
    @Override
    public boolean stavi_na_cekanje(Rezervacija rezervacija) {
        // Otkazana -> Na čekanju (NE MOŽE)
        return false;
    }
    
    @Override
    public boolean odgodi(Rezervacija rezervacija) {
        // Otkazana -> Odgođena (NE MOŽE)
        return false;
    }
    
    @Override
    public boolean otkazi(Rezervacija rezervacija) {
        // Otkazana -> Otkazana (OSTAJE)
        return true;
    }
    
    @Override
    public String getNazivStanja() {
        return "OTKAZANA";
    }
}