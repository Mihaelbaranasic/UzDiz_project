package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.naredbe;

import java.util.List;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.lib.pomocne.DatumParser;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.model.Aranzman;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.model.Rezervacija;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.singleton.TuristickaAgencija;

/**
 * Komanda za ispis rezervacija osobe.
 */
public class IroKomanda implements Komanda {
    
    private String ime;
    private String prezime;
    
    public IroKomanda(String ime, String prezime) {
        this.ime = ime;
        this.prezime = prezime;
    }
    
    @Override
    public boolean izvrsi() {
        TuristickaAgencija agencija = TuristickaAgencija.getInstance();
        List<Rezervacija> rezervacije = agencija.dohvatiRezervacijeOsobe(ime, prezime);
        
        if (rezervacije.isEmpty()) {
            System.out.println("Nema rezervacija za osobu " + ime + " " + prezime + ".");
            return true;
        }
        
        System.out.printf("%-20s %-10s %-30s %-12s%n", 
            "Datum i vrijeme", "Oznaka", "Naziv aranžmana", "Vrsta");
        System.out.println(String.format("%0" + 72 + "d", 0).replace("0", "-"));
        
        for (Rezervacija r : rezervacije) {
            Aranzman a = agencija.dohvatiAranzman(r.getOznakaAranzmana());
            String naziv = a != null ? 
                (a.getNaziv().length() > 30 ? a.getNaziv().substring(0, 27) + "..." : a.getNaziv()) : "-";
            
            System.out.printf("%-20s %-10s %-30s %-12s%n",
                DatumParser.formatirajDatumVrijeme(r.getDatumVrijemePrijema()),
                r.getOznakaAranzmana(), naziv, r.getStanje().getOznaka());
        }
        
        return true;
    }
    
    @Override
    public String getOpis() {
        return "Ispis rezervacija osobe";
    }
}