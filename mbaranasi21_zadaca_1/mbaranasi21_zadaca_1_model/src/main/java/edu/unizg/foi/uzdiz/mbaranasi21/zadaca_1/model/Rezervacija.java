package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_1.model;

import java.time.LocalDateTime;

/**
 * Predstavlja rezervaciju turističkog aranžmana za jednu osobu.
 */
public class Rezervacija {
    
    private final Osoba osoba;
    private final String oznakaAranzmana;
    private final LocalDateTime datumVrijemePrijema;
    private StanjeRezervacije stanje;
    private LocalDateTime datumVrijemeOtkaza;
    
    /**
     * Konstruktor za kreiranje rezervacije.
     *
     * @param osoba Osoba koja rezervira
     * @param oznakaAranzmana Oznaka aranžmana
     * @param datumVrijemePrijema Datum i vrijeme prijema rezervacije
     * @param stanje Početno stanje rezervacije
     */
    public Rezervacija(Osoba osoba, String oznakaAranzmana, 
                      LocalDateTime datumVrijemePrijema, StanjeRezervacije stanje) {
        this.osoba = osoba;
        this.oznakaAranzmana = oznakaAranzmana;
        this.datumVrijemePrijema = datumVrijemePrijema;
        this.stanje = stanje;
    }
    
    public Osoba getOsoba() {
        return osoba;
    }
    
    public String getOznakaAranzmana() {
        return oznakaAranzmana;
    }
    
    public LocalDateTime getDatumVrijemePrijema() {
        return datumVrijemePrijema;
    }
    
    public StanjeRezervacije getStanje() {
        return stanje;
    }
    
    public LocalDateTime getDatumVrijemeOtkaza() {
        return datumVrijemeOtkaza;
    }
    
    /**
     * Postavlja novo stanje rezervacije.
     *
     * @param novoStanje Novo stanje
     */
    public void postaviStanje(StanjeRezervacije novoStanje) {
        this.stanje = novoStanje;
    }
    
    /**
     * Označava rezervaciju kao otkazanu i postavlja datum otkaza.
     *
     * @param datumVrijeme Datum i vrijeme otkaza
     */
    public void otkazi(LocalDateTime datumVrijeme) {
        this.stanje = StanjeRezervacije.OTKAZANA;
        this.datumVrijemeOtkaza = datumVrijeme;
    }
    
    /**
     * Provjerava je li rezervacija aktivna.
     *
     * @return true ako je aktivna, inače false
     */
    public boolean jeAktivna() {
        return stanje == StanjeRezervacije.AKTIVNA;
    }
    
    /**
     * Provjerava je li rezervacija primljena.
     *
     * @return true ako je primljena, inače false
     */
    public boolean jePrimljena() {
        return stanje == StanjeRezervacije.PRIMLJENA;
    }
    
    /**
     * Provjerava je li rezervacija na čekanju.
     *
     * @return true ako je na čekanju, inače false
     */
    public boolean jeNaCekanju() {
        return stanje == StanjeRezervacije.NA_CEKANJU;
    }
    
    /**
     * Provjerava je li rezervacija otkazana.
     *
     * @return true ako je otkazana, inače false
     */
    public boolean jeOtkazana() {
        return stanje == StanjeRezervacije.OTKAZANA;
    }
    
    @Override
    public String toString() {
        return osoba.getPunoIme() + " - " + oznakaAranzmana + " (" + stanje + ")";
    }
}