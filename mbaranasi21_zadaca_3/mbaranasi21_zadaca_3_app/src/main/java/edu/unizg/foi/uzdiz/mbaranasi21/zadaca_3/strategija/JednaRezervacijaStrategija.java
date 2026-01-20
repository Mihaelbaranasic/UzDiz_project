package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.strategija;

import java.util.List;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.model.Aranzman;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.model.Osoba;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.model.Rezervacija;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.model.StanjeRezervacije;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.singleton.TuristickaAgencija;

/**
 * ConcreteStrategy za --jdr opciju.
 * Jedna osoba u jednom trenutku može imati samo jednu aktivnu rezervaciju.
 */
public class JednaRezervacijaStrategija extends RezervacijaStrategija {
    
    @Override
    public boolean dozvoliRezervaciju(Osoba osoba, Aranzman aranzman, 
                                      List<Rezervacija> sveRezervacije) {
        
        TuristickaAgencija agencija = TuristickaAgencija.getInstance();
        
        for (Rezervacija rez : sveRezervacije) {
            if (!rez.getOsoba().getIme().equals(osoba.getIme()) 
                || !rez.getOsoba().getPrezime().equals(osoba.getPrezime())) {
                continue;
            }
            
            if (rez.getStanje() != StanjeRezervacije.AKTIVNA) {
                continue;
            }
            
            Aranzman postojeciAranzman = agencija.dohvatiAranzman(
                rez.getOznakaAranzmana());
            
            if (postojeciAranzman == null) {
                continue;
            }
            
            if (aranzman.preklapa(postojeciAranzman)) {
                return false;
            }
        }
        
        return true;
    }
    
    @Override
    public String getNaziv() {
        return "Jedna rezervacija po osobi (--jdr)";
    }
}