package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.stanje.aranzman;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.singleton.TuristickaAgencija;

/**
 * Stanje POPUNJEN - broj prijava prešao max.
 */
public class PopunjenAranzman implements AranzmanStanje {
    
    @Override
    public boolean uPripremi(TuristickaAgencija agencija, String oznaka) {
        return false;
    }
    
    @Override
    public boolean aktiviraj(TuristickaAgencija agencija, String oznaka) {
        agencija.postaviStanjeAranzmana(oznaka, new AktivniAranzman());
        return true;
    }
    
    @Override
    public boolean popuni(TuristickaAgencija agencija, String oznaka) {
        return true;
    }
    
    @Override
    public boolean otkazi(TuristickaAgencija agencija, String oznaka) {
        agencija.postaviStanjeAranzmana(oznaka, new OtkazanAranzman());
        return true;
    }
    
    @Override
    public String getNazivStanja() {
        return "POPUNJEN";
    }
}