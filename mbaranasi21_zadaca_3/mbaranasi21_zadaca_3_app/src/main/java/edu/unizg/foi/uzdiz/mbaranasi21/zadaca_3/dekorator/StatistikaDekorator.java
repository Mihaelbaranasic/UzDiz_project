package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.dekorator;

import java.util.List;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.model.Aranzman;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.model.Rezervacija;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.singleton.TuristickaAgencija;

/**
 * ConcreteDecorator - dodaje statističke podatke.
 */
public class StatistikaDekorator extends IzvjestajDekorator {
    
    private String oznakaAranzmana;
    
    /**
     * Konstruktor.
     * 
     * @param komponenta Komponenta koju dekoriramo
     * @param oznakaAranzmana Oznaka aranžmana
     */
    public StatistikaDekorator(IzvjestajKomponenta komponenta, String oznakaAranzmana) {
        super(komponenta);
        this.oznakaAranzmana = oznakaAranzmana;
    }
    
    @Override
    public void generiraj() {
        super.generiraj();
        dodajStatistiku();
    }
    
    /**
     * Dodaje statističke podatke izvještaju.
     */
    private void dodajStatistiku() {
        TuristickaAgencija agencija = TuristickaAgencija.getInstance();
        Aranzman aranzman = agencija.dohvatiAranzman(oznakaAranzmana);
        
        if (aranzman == null) {
            return;
        }
        
        List<Rezervacija> rezervacije = agencija.dohvatiRezervacije(oznakaAranzmana);
        
        StringBuilder dodatak = new StringBuilder();
        dodatak.append("───────────────────────────────────────────────────────\n");
        dodatak.append("  STATISTIKA\n");
        dodatak.append("───────────────────────────────────────────────────────\n\n");
        
        izracunajPopunjenost(dodatak, rezervacije, aranzman);
        izracunajStopuOtkaza(dodatak, rezervacije);
        
        komponenta = new DekoriranaKomponenta(komponenta, dodatak.toString());
    }
    
    /**
     * Izračunava postotak popunjenosti.
     */
    private void izracunajPopunjenost(StringBuilder dodatak, 
            List<Rezervacija> rezervacije, Aranzman aranzman) {
        long aktivne = 0;
        for (Rezervacija r : rezervacije) {
            if (r.jeAktivna()) {
                aktivne++;
            }
        }
        
        double postotakPopunjenosti = (double) aktivne / aranzman.getMaksBrojPutnika() * 100;
        dodatak.append("Popunjenost: ").append(aktivne).append(" / ")
               .append(aranzman.getMaksBrojPutnika())
               .append(String.format(" (%.2f%%)", postotakPopunjenosti)).append("\n");
    }
    
    /**
     * Izračunava stopu otkaza.
     */
    private void izracunajStopuOtkaza(StringBuilder dodatak, List<Rezervacija> rezervacije) {
        long otkazane = 0;
        for (Rezervacija r : rezervacije) {
            if (r.jeOtkazana()) {
                otkazane++;
            }
        }
        
        if (rezervacije.size() > 0) {
            double stopaOtkaza = (double) otkazane / rezervacije.size() * 100;
            dodatak.append("Stopa otkaza: ").append(otkazane).append(" / ")
                   .append(rezervacije.size())
                   .append(String.format(" (%.2f%%)", stopaOtkaza)).append("\n\n");
        } else {
            dodatak.append("Stopa otkaza: N/A (nema rezervacija)\n\n");
        }
    }
    
    /**
     * Pomoćna klasa za dodavanje dodatnog sadržaja.
     */
    private static class DekoriranaKomponenta implements IzvjestajKomponenta {
        private IzvjestajKomponenta originalna;
        private String dodatniSadrzaj;
        
        public DekoriranaKomponenta(IzvjestajKomponenta originalna, String dodatniSadrzaj) {
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