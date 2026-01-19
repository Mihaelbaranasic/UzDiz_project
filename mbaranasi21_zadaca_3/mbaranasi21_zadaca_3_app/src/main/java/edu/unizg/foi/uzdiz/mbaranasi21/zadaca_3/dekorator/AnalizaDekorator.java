package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.dekorator;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.lib.pomocne.DatumParser;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.model.Aranzman;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.model.Rezervacija;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.singleton.TuristickaAgencija;

/**
 * ConcreteDecorator - dodaje analitičke podatke.
 */
public class AnalizaDekorator extends IzvjestajDekorator {
    
    private String oznakaAranzmana;
    
    /**
     * Konstruktor.
     */
    public AnalizaDekorator(IzvjestajKomponenta komponenta, String oznakaAranzmana) {
        super(komponenta);
        this.oznakaAranzmana = oznakaAranzmana;
    }
    
    @Override
    public void generiraj() {
        super.generiraj();
        dodajAnalizu();
    }
    
    /**
     * Dodaje analitičke podatke izvještaju.
     */
    private void dodajAnalizu() {
        TuristickaAgencija agencija = TuristickaAgencija.getInstance();
        Aranzman aranzman = agencija.dohvatiAranzman(oznakaAranzmana);
        
        if (aranzman == null) {
            return;
        }
        
        List<Rezervacija> rezervacije = agencija.dohvatiRezervacije(oznakaAranzmana);
        
        StringBuilder dodatak = new StringBuilder();
        dodatak.append("───────────────────────────────────────────────────────\n");
        dodatak.append("  ANALIZA\n");
        dodatak.append("───────────────────────────────────────────────────────\n\n");
        
        analizirajVremenskiTrend(dodatak, rezervacije);
        analizirajProsjekVremena(dodatak, rezervacije, aranzman);
        
        komponenta = new DekoriranaKomponenta(komponenta, dodatak.toString());
    }
    
    /**
     * Analizira vremenski trend rezervacija.
     */
    private void analizirajVremenskiTrend(StringBuilder dodatak, 
            List<Rezervacija> rezervacije) {
        if (rezervacije.isEmpty()) {
            dodatak.append("Trend rezervacija: N/A (nema rezervacija)\n");
            return;
        }
        
        LocalDateTime prvaRezervacija = null;
        LocalDateTime zadnjaRezervacija = null;
        
        for (Rezervacija r : rezervacije) {
            if (!r.jeOtkazana()) {
                if (prvaRezervacija == null || 
                    r.getDatumVrijemePrijema().isBefore(prvaRezervacija)) {
                    prvaRezervacija = r.getDatumVrijemePrijema();
                }
                if (zadnjaRezervacija == null || 
                    r.getDatumVrijemePrijema().isAfter(zadnjaRezervacija)) {
                    zadnjaRezervacija = r.getDatumVrijemePrijema();
                }
            }
        }
        
        if (prvaRezervacija != null && zadnjaRezervacija != null) {
            dodatak.append("Prva rezervacija: ")
                   .append(DatumParser.formatirajDatumVrijeme(prvaRezervacija))
                   .append("\n");
            dodatak.append("Zadnja rezervacija: ")
                   .append(DatumParser.formatirajDatumVrijeme(zadnjaRezervacija))
                   .append("\n");
        }
    }
    
    /**
     * Analizira prosječno vrijeme do polaska.
     */
    private void analizirajProsjekVremena(StringBuilder dodatak, 
            List<Rezervacija> rezervacije, Aranzman aranzman) {
        if (rezervacije.isEmpty()) {
            dodatak.append("Prosječno vrijeme rezervacije prije polaska: N/A\n\n");
            return;
        }
        
        LocalDateTime pocetakAranzmana = aranzman.getPocetniDatum()
            .atTime(aranzman.getVrijemeKretanja());
        
        long ukupnoDana = 0;
        int brojRezervacija = 0;
        
        for (Rezervacija r : rezervacije) {
            if (!r.jeOtkazana()) {
                long dana = ChronoUnit.DAYS.between(
                    r.getDatumVrijemePrijema(), pocetakAranzmana);
                if (dana >= 0) {
                    ukupnoDana += dana;
                    brojRezervacija++;
                }
            }
        }
        
        if (brojRezervacija > 0) {
            double prosjekDana = (double) ukupnoDana / brojRezervacija;
            dodatak.append("Prosječno vrijeme rezervacije prije polaska: ")
                   .append(String.format("%.1f dana", prosjekDana))
                   .append("\n\n");
        }
    }
    
    /**
     * Pomoćna klasa za dodavanje dodatnog sadržaja.
     */
    private static class DekoriranaKomponenta implements IzvjestajKomponenta {
        private IzvjestajKomponenta originalna;
        private String dodatniSadrzaj;
        
        public DekoriranaKomponenta(IzvjestajKomponenta originalna, 
                String dodatniSadrzaj) {
            this.originalna = originalna;
            this.dodatniSadrzaj = dodatniSadrzaj;
        }
        
        @Override
        public void generiraj() {
            originalna.generiraj();
        }
        
        @Override
        public String dohvatiSadrzaj() {
            return originalna.dohvatiSadrzaj() + dodatniSadrzaj;
        }
    }
}