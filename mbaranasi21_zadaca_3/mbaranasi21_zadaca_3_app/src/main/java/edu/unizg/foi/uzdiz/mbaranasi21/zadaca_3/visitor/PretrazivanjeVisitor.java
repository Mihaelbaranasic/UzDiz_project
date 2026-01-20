package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.visitor;

import java.util.ArrayList;
import java.util.List;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.composite.AranzmanKomponenta;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.composite.RezervacijaKomponenta;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.composite.TuristickaKomponenta;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.model.Aranzman;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.model.Rezervacija;

/**
 * Visitor za pretraživanje aranžmana i rezervacija.
 * Koristi se za PPTAR komandu.
 */
public class PretrazivanjeVisitor implements TuristickaVisitor {
    
    private String kljucnaRijec;
    private boolean pretraziAranzmane;
    private List<String> rezultatiAranzmani;
    private List<String> rezultatiRezervacije;
    
    public PretrazivanjeVisitor(String kljucnaRijec, boolean pretraziAranzmane) {
        this.kljucnaRijec = kljucnaRijec;
        this.pretraziAranzmane = pretraziAranzmane;
        this.rezultatiAranzmani = new ArrayList<>();
        this.rezultatiRezervacije = new ArrayList<>();
    }
    
    @Override
    public void visitAranzman(AranzmanKomponenta aranzmanKomp) {
        if (!pretraziAranzmane) {
            return;
        }
        
        Aranzman aranzman = aranzmanKomp.getAranzman();
        
        if (sadrzavaKljucnuRijec(aranzman.getNaziv()) 
            || sadrzavaKljucnuRijec(aranzman.getProgram())) {
            rezultatiAranzmani.add(aranzman.getOznaka() + " - " + aranzman.getNaziv());
        }
    }
    
    @Override
    public void visitRezervacija(RezervacijaKomponenta rezervacijaKomp) {
        if (pretraziAranzmane) {
            return;
        }
        
        Rezervacija rezervacija = rezervacijaKomp.getRezervacija();
        String ime = rezervacija.getOsoba().getIme();
        String prezime = rezervacija.getOsoba().getPrezime();
        
        if (sadrzavaKljucnuRijec(ime) || sadrzavaKljucnuRijec(prezime)) {
            rezultatiRezervacije.add(ime + " " + prezime + " - " 
                + rezervacija.getOznakaAranzmana());
        }
    }
    
    private boolean sadrzavaKljucnuRijec(String tekst) {
        if (tekst == null) {
            return false;
        }
        return tekst.contains(kljucnaRijec);
    }
    
    public List<String> dohvatiRezultateAranzmani() {
        return rezultatiAranzmani;
    }
    
    public List<String> dohvatiRezultateRezervacije() {
        return rezultatiRezervacije;
    }
}