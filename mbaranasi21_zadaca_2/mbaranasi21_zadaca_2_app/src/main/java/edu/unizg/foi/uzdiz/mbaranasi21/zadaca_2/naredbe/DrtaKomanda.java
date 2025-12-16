package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.naredbe;

import java.time.LocalDateTime;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.model.Osoba;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.model.Rezervacija;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.model.StanjeRezervacije;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.singleton.TuristickaAgencija;

/**
 * Komanda za dodavanje rezervacije.
 */
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
        
        Rezervacija novaRezervacija = new Rezervacija(
            osoba, oznaka, datumVrijeme, StanjeRezervacije.NOVA);
        
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