package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.naredbe;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.composite.AranzmanKomponenta;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.model.Aranzman;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.singleton.TuristickaAgencija;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.visitor.StatistikaVisitor;

/**
 * Komanda za ispis statistike aranžmana.
 * Koristi Visitor pattern za prikupljanje podataka.
 */
public class ItasKomanda implements Komanda {
    
    private String oznakaAranzmana;
    
    /**
     * Konstruktor.
     * 
     * @param oznakaAranzmana Oznaka aranžmana (null za sve aranžmane)
     */
    public ItasKomanda(String oznakaAranzmana) {
        this.oznakaAranzmana = oznakaAranzmana;
    }
    
    @Override
    public boolean izvrsi() {
        TuristickaAgencija agencija = TuristickaAgencija.getInstance();
        
        if (oznakaAranzmana != null) {
            return ispisiStatistikuJednogAranzmana(agencija);
        } else {
            return ispisiStatistikuSvihAranzmana(agencija);
        }
    }
    
    /**
     * Ispisuje statistiku jednog aranžmana.
     */
    private boolean ispisiStatistikuJednogAranzmana(TuristickaAgencija agencija) {
        AranzmanKomponenta komponenta = 
            agencija.kreirajCompositeStrukturuAranzmana(oznakaAranzmana);
        
        if (komponenta == null) {
            System.err.println("GREŠKA: Aranžman '" + oznakaAranzmana + "' ne postoji!");
            return false;
        }
        
        StatistikaVisitor visitor = new StatistikaVisitor();
        komponenta.prihvati(visitor);
        
        Aranzman aranzman = agencija.dohvatiAranzman(oznakaAranzmana);
        visitor.izracunajPrihod(aranzman.getCijena());
        visitor.ispisiStatistiku();
        
        return true;
    }
    
    /**
     * Ispisuje statistiku svih aranžmana.
     */
    private boolean ispisiStatistikuSvihAranzmana(TuristickaAgencija agencija) {
        StatistikaVisitor visitor = new StatistikaVisitor();
        
        for (String oznaka : agencija.dohvatiSveOznakeAranzmana()) {
            AranzmanKomponenta komponenta = 
                agencija.kreirajCompositeStrukturuAranzmana(oznaka);
            
            if (komponenta != null) {
                komponenta.prihvati(visitor);
            }
        }
        
        visitor.ispisiStatistiku();
        return true;
    }
    
    @Override
    public String getOpis() {
        return "Ispis statistike aranžmana (Visitor pattern)";
    }
}