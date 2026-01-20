package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.naredbe;

import java.time.LocalDateTime;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.model.Osoba;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.singleton.TuristickaAgencija;

public class DrtaKomanda implements Komanda {
    
    private String ime;
    private String prezime;
    private String oznaka;
    private LocalDateTime datumVrijeme;
    
    public DrtaKomanda(String ime, String prezime, String oznaka, LocalDateTime datumVrijeme) {
        this.ime = ime;
        this.prezime = prezime;
        this.oznaka = oznaka;
        this.datumVrijeme = datumVrijeme;
    }
    
    @Override
    public boolean izvrsi() {
        Osoba osoba = new Osoba(ime, prezime);
        TuristickaAgencija agencija = TuristickaAgencija.getInstance();
        
        boolean uspjeh = agencija.dodajNovuRezervaciju(osoba, oznaka, datumVrijeme);
        
        if (uspjeh) {
            System.out.println("Rezervacija uspješno dodana.");
        }
        
        return uspjeh;
    }
    
    @Override
    public String getOpis() {
        return "Dodavanje rezervacije turističkog aranžmana";
    }
}