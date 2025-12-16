package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.model;

import java.time.LocalDateTime;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.stanje.rezervacija.AktivnaRezervacija;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.stanje.rezervacija.NaCekanjuRezervacija;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.stanje.rezervacija.NovaRezervacija;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.stanje.rezervacija.OdgodenaRezervacija;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.stanje.rezervacija.OtkazanaRezervacija;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.stanje.rezervacija.PrimljenaRezervacija;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.stanje.rezervacija.RezervacijaStanje;

/**
 * Predstavlja rezervaciju osobe za turistički aranžman.
 * Koristi State pattern za upravljanje stanjima.
 */
public class Rezervacija {
    
    private Osoba osoba;
    private String oznakaAranzmana;
    private LocalDateTime datumVrijemePrijema;
    private RezervacijaStanje stanje;
    private LocalDateTime datumVrijemeOtkaza;
    
    /**
     * Konstruktor za kreiranje nove rezervacije.
     */
    public Rezervacija(Osoba osoba, String oznakaAranzmana, 
            LocalDateTime datumVrijemePrijema, StanjeRezervacije stanjeEnum) {
        this.osoba = osoba;
        this.oznakaAranzmana = oznakaAranzmana;
        this.datumVrijemePrijema = datumVrijemePrijema;
        this.datumVrijemeOtkaza = null;
        
        switch (stanjeEnum) {
            case NOVA:
                this.stanje = new NovaRezervacija();
                break;
            case PRIMLJENA:
                this.stanje = new PrimljenaRezervacija();
                break;
            case AKTIVNA:
                this.stanje = new AktivnaRezervacija();
                break;
            case NA_CEKANJU:
                this.stanje = new NaCekanjuRezervacija();
                break;
            case ODGOĐENA:
                this.stanje = new OdgodenaRezervacija();
                break;
            case OTKAZANA:
                this.stanje = new OtkazanaRezervacija();
                break;
            default:
                this.stanje = new NovaRezervacija();
        }
    }
    
    // Getteri
    
    public Osoba getOsoba() {
        return osoba;
    }
    
    public String getOznakaAranzmana() {
        return oznakaAranzmana;
    }
    
    public LocalDateTime getDatumVrijemePrijema() {
        return datumVrijemePrijema;
    }
    
    public LocalDateTime getDatumVrijemeOtkaza() {
        return datumVrijemeOtkaza;
    }
    
    /**
     * Dohvaća trenutno stanje kao enum.
     */
    public StanjeRezervacije getStanje() {
        String naziv = stanje.getNazivStanja();
        return StanjeRezervacije.valueOf(naziv);
    }
    
    // Setteri
    
    /**
     * Postavlja novo stanje (interno za State pattern).
     */
    public void setStanje(RezervacijaStanje novoStanje) {
        this.stanje = novoStanje;
    }
    
    /**
     * Postavlja datum otkazivanja.
     */
    public void setDatumOtkaza(LocalDateTime datumOtkaza) {
        this.datumVrijemeOtkaza = datumOtkaza;
    }
    
    // State prijelazi
    
    /**
     * Postavlja rezervaciju na novu.
     */
    public void nova() {
        stanje.nova(this);
    }
    
    /**
     * Postavlja rezervaciju na primljenu.
     */
    public void primljena() {
        stanje.primljena(this);
    }
    
    /**
     * Aktivira rezervaciju.
     */
    public void aktiviraj() {
        stanje.aktiviraj(this);
    }
    
    /**
     * Postavlja rezervaciju na čekanje.
     */
    public void staviNaCekanje() {
        stanje.staviNaCekanje(this);
    }
    
    /**
     * Odgađa rezervaciju.
     */
    public void odgodi() {
        stanje.odgodi(this);
    }
    
    /**
     * Otkazuje rezervaciju.
     */
    public void otkazi(LocalDateTime datumVrijeme) {
        stanje.otkazi(this, datumVrijeme);
    }
    
    // Provjere stanja
    
    /**
     * Provjerava je li rezervacija nova.
     */
    public boolean jeNova() {
        return stanje.getNazivStanja().equals("NOVA");
    }
    
    /**
     * Provjerava je li rezervacija primljena.
     */
    public boolean jePrimljena() {
        return stanje.getNazivStanja().equals("PRIMLJENA");
    }
    
    /**
     * Provjerava je li rezervacija aktivna.
     */
    public boolean jeAktivna() {
        return stanje.getNazivStanja().equals("AKTIVNA");
    }
    
    /**
     * Provjerava je li rezervacija na čekanju.
     */
    public boolean jeNaCekanju() {
        return stanje.getNazivStanja().equals("NA_CEKANJU");
    }
    
    /**
     * Provjerava je li rezervacija odgođena.
     */
    public boolean jeOdgodena() {
        return stanje.getNazivStanja().equals("ODGOĐENA");
    }
    
    /**
     * Provjerava je li rezervacija otkazana.
     */
    public boolean jeOtkazana() {
        return stanje.getNazivStanja().equals("OTKAZANA");
    }
    
    @Override
    public String toString() {
        return osoba.getIme() + " " + osoba.getPrezime() + 
               " - " + oznakaAranzmana + " (" + stanje.getNazivStanja() + ")";
    }
}