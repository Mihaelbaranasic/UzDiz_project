package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.naredbe;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.dekorator.AnalizaDekorator;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.dekorator.FinancijskiDekorator;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.dekorator.IzvjestajKomponenta;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.dekorator.ObicniIzvjestaj;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.dekorator.StatistikaDekorator;

/**
 * Komanda za generiranje ekstenzivnog izvještaja s opcionalnim dekoratorima.
 * Koristi Decorator pattern za dodavanje funkcionalnosti.
 */
public class IeiKomanda implements Komanda {
    
    private String oznakaAranzmana;
    private boolean statistika;
    private boolean analiza;
    private boolean financije;
    
    /**
     * Konstruktor.
     * 
     * @param oznakaAranzmana Oznaka aranžmana
     * @param statistika Uključi statistiku (S)
     * @param analiza Uključi analizu (A)
     * @param financije Uključi financijske podatke (F)
     */
    public IeiKomanda(String oznakaAranzmana, boolean statistika, 
            boolean analiza, boolean financije) {
        this.oznakaAranzmana = oznakaAranzmana;
        this.statistika = statistika;
        this.analiza = analiza;
        this.financije = financije;
    }
    
    @Override
    public boolean izvrsi() {
        IzvjestajKomponenta izvjestaj = kreirajIzvjestaj();
        izvjestaj.generiraj();
        
        String sadrzaj = izvjestaj.dohvatiSadrzaj();
        System.out.println(sadrzaj);
        
        return true;
    }
    
    /**
     * Kreira izvještaj s odgovarajućim dekoratorima.
     * Koristi Decorator pattern za dinamičko dodavanje funkcionalnosti.
     */
    private IzvjestajKomponenta kreirajIzvjestaj() {
        IzvjestajKomponenta izvjestaj = new ObicniIzvjestaj(oznakaAranzmana);
        
        if (statistika) {
            izvjestaj = new StatistikaDekorator(izvjestaj, oznakaAranzmana);
        }
        
        if (analiza) {
            izvjestaj = new AnalizaDekorator(izvjestaj, oznakaAranzmana);
        }
        
        if (financije) {
            izvjestaj = new FinancijskiDekorator(izvjestaj, oznakaAranzmana);
        }
        
        return izvjestaj;
    }
    
    @Override
    public String getOpis() {
        return "Izvještaj ekstenzivni informacija (Decorator pattern)";
    }
}