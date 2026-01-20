package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.strategija;

import java.util.List;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.model.Aranzman;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.model.Osoba;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.model.Rezervacija;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.model.StanjeRezervacije;

/**
 * ConcreteStrategy za --vdr opciju.
 * Jedna osoba može imati više rezervacija, ali ne više od 1/4 maksimalnog broja putnika.
 */
public class ViseRezervacijaStrategija extends RezervacijaStrategija {
    
    @Override
    public boolean dozvoliRezervaciju(Osoba osoba, Aranzman aranzman, 
                                      List<Rezervacija> sveRezervacije) {
        
        int brojAktivnihRezervacija = 0;
        
        for (Rezervacija rez : sveRezervacije) {
            if (!rez.getOsoba().equals(osoba)) {
                continue;
            }
            
            if (rez.getStanje() == StanjeRezervacije.AKTIVNA) {
                brojAktivnihRezervacija++;
            }
        }
        
        int maxDozvoljeno = aranzman.getMaksBrojPutnika() / 4;
        
        if (maxDozvoljeno == 0) {
            maxDozvoljeno = 1;
        }
        
        return brojAktivnihRezervacija < maxDozvoljeno;
    }
    
    @Override
    public String getNaziv() {
        return "Više rezervacija po osobi (--vdr)";
    }
}