package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.visitor;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.composite.AranzmanKomponenta;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.composite.RezervacijaKomponenta;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.model.Aranzman;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.model.Rezervacija;

/**
 * ConcreteVisitor - prikuplja statističke podatke obilazeći Composite strukturu.
 * Koristi se za ITAS komandu.
 */
public class StatistikaVisitor implements TuristickaVisitor {
    
    private int ukupnoRezervacija = 0;
    private int brojAktivnih = 0;
    private int brojPrimljenih = 0;
    private int brojNaCekanju = 0;
    private int brojOtkazanih = 0;
    private double ukupnaCijena = 0;
    private int brojAranzmana = 0;
    private double ukupniPrihod = 0;
    
    @Override
    public void visitAranzman(AranzmanKomponenta komponenta) {
        brojAranzmana++;
        Aranzman a = komponenta.getAranzman();
        ukupnaCijena += a.getCijena();
    }
    
    @Override
    public void visitRezervacija(RezervacijaKomponenta komponenta) {
        ukupnoRezervacija++;
        Rezervacija r = komponenta.getRezervacija();
        
        if (r.jeAktivna()) {
            brojAktivnih++;
        } else if (r.jePrimljena()) {
            brojPrimljenih++;
        } else if (r.jeNaCekanju()) {
            brojNaCekanju++;
        } else if (r.jeOtkazana()) {
            brojOtkazanih++;
        }
    }
    
    /**
     * Izračunava ukupni prihod na temelju cijene aranžmana i aktivnih rezervacija.
     */
    public void izracunajPrihod(double cijenaAranzmana) {
        ukupniPrihod = brojAktivnih * cijenaAranzmana;
    }
    
    /**
     * Ispisuje prikupljenu statistiku.
     */
    public void ispisiStatistiku() {
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("  STATISTIKA ARANŽMANA");
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println();
        
        ispisiOsnovnePodatke();
        ispisiRaspodjeluRezervacija();
        ispisiPostotke();
        ispisiFinancijskePodatke();
    }
    
    /**
     * Ispisuje osnovne statističke podatke.
     */
    private void ispisiOsnovnePodatke() {
        System.out.println("Broj aranžmana: " + brojAranzmana);
        System.out.println("Ukupno rezervacija: " + ukupnoRezervacija);
    }
    
    /**
     * Ispisuje raspodjelu rezervacija po stanjima.
     */
    private void ispisiRaspodjeluRezervacija() {
        System.out.println("  - Aktivne: " + brojAktivnih);
        System.out.println("  - Primljene: " + brojPrimljenih);
        System.out.println("  - Na čekanju: " + brojNaCekanju);
        System.out.println("  - Otkazane: " + brojOtkazanih);
        System.out.println();
    }
    
    /**
     * Ispisuje postotke (popunjenost, stopa otkaza).
     */
    private void ispisiPostotke() {
        if (ukupnoRezervacija > 0) {
            double postotakAktivnih = (double) brojAktivnih / ukupnoRezervacija * 100;
            double stopaOtkaza = (double) brojOtkazanih / ukupnoRezervacija * 100;
            
            System.out.println("Postotak aktivnih rezervacija: " 
                + String.format("%.2f%%", postotakAktivnih));
            System.out.println("Stopa otkaza: " 
                + String.format("%.2f%%", stopaOtkaza));
            System.out.println();
        }
    }
    
    /**
     * Ispisuje financijske podatke.
     */
    private void ispisiFinancijskePodatke() {
        if (brojAranzmana > 0) {
            double prosjekCijena = ukupnaCijena / brojAranzmana;
            System.out.println("Prosječna cijena aranžmana: " 
                + String.format("%.2f EUR", prosjekCijena));
        }
        
        if (ukupniPrihod > 0) {
            System.out.println("Ukupni prihod: " 
                + String.format("%.2f EUR", ukupniPrihod));
        }
    }
    
    public int getUkupnoRezervacija() { return ukupnoRezervacija; }
    public int getBrojAktivnih() { return brojAktivnih; }
    public int getBrojPrimljenih() { return brojPrimljenih; }
    public int getBrojNaCekanju() { return brojNaCekanju; }
    public int getBrojOtkazanih() { return brojOtkazanih; }
}