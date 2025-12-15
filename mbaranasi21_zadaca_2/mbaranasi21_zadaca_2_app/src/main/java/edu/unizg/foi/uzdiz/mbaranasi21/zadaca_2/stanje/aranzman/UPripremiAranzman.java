package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.stanje.aranzman;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.singleton.TuristickaAgencija;

public class UPripremiAranzman implements AranzmanStanje {
    
    @Override
    public boolean uPripremi(TuristickaAgencija agencija, String oznaka) {
        return true;
    }
    
    @Override
    public boolean aktiviraj(TuristickaAgencija agencija, String oznaka) {
        agencija.postaviStanjeAranzmana(oznaka, new AktivniAranzman());
        return true;
    }
    
    @Override
    public boolean popuni(TuristickaAgencija agencija, String oznaka) {
        return false;
    }
    
    @Override
    public boolean otkazi(TuristickaAgencija agencija, String oznaka) {
        agencija.postaviStanjeAranzmana(oznaka, new OtkazanAranzman());
        return true;
    }
    
    @Override
    public String getNazivStanja() {
        return "U_PRIPREMI";
    }
}