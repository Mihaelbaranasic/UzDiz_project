package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.strategija;

import java.util.List;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.model.Aranzman;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.model.Osoba;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.model.Rezervacija;

/**
 * Null Object - kad nije zadana ni --jdr ni --vdr opcija.
 * Dozvoljava sve rezervacije bez ograničenja.
 */
public class NullRezervacijaStrategija extends RezervacijaStrategija {
    
    @Override
    public boolean dozvoliRezervaciju(Osoba osoba, Aranzman aranzman, 
                                      List<Rezervacija> sveRezervacije) {
        return true;
    }
    
    @Override
    public String getNaziv() {
        return "Bez ograničenja (default)";
    }
}