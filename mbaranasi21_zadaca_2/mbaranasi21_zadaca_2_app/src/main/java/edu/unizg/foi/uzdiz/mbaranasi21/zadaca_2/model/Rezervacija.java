package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.model;

import java.time.LocalDateTime;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.stanje.rezervacija.*;

/**
 * Predstavlja rezervaciju osobe za turistički aranžman.
 * Koristi State pattern za upravljanje stanjima.
 */
public class Rezervacija {
    
    private Osoba osoba;
    private String oznakaAranzmana;
    private LocalDateTime datumVrijemePrijema;
    private RezervacijaStanje stanje;  // ← STATE PATTERN
    private LocalDateTime datumVrijemeOtkaza;
    
    /**
     * Konstruktor za kreiranje nove rezervacije.
     *
     * @param osoba Osoba koja rezervira
     * @param oznakaAranzmana Oznaka aranžmana
     * @param datumVrijemePrijema Datum i vrijeme prijema rezervacije
     * @param stanjeEnum Inicijalno stanje rezervacije (enum)
     */
    public Rezervacija(Osoba osoba, String oznakaAranzmana, 
                      LocalDateTime datumVrijemePrijema, 
                      StanjeRezervacije stanjeEnum) {
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
     * Dohvaća trenutno stanje kao enum (za kompatibilnost).
     */
    public StanjeRezervacije getStanje() {
        String naziv = stanje.getNazivStanja();
        return StanjeRezervacije.valueOf(naziv);
    }
    
    /**
     * Postavlja novo stanje (interno - koristi State pattern).
     */
    public void setStanje(RezervacijaStanje novoStanje) {
        this.stanje = novoStanje;
    }
    
    public boolean primljena() {
        return stanje.primljena(this);
    }
    
    public boolean aktiviraj() {
        return stanje.aktiviraj(this);
    }
    
    public boolean staviNaCekanje() {
        return stanje.stavi_na_cekanje(this);
    }
    
    public boolean odgodi() {
        return stanje.odgodi(this);
    }
    
    public boolean otkazi(LocalDateTime datumVrijeme) {
        boolean uspjeh = stanje.otkazi(this);
        if (uspjeh) {
            this.datumVrijemeOtkaza = datumVrijeme;
        }
        return uspjeh;
    }
    
    public boolean jePrimljena() {
        return stanje instanceof PrimljenaRezervacija;
    }
    
    public boolean jeAktivna() {
        return stanje instanceof AktivnaRezervacija;
    }
    
    public boolean jeNaCekanju() {
        return stanje instanceof NaCekanjuRezervacija;
    }
    
    public boolean jeOtkazana() {
        return stanje instanceof OtkazanaRezervacija;
    }
    
    @Override
    public String toString() {
        return osoba.getIme() + " " + osoba.getPrezime() + 
               " - " + oznakaAranzmana + " (" + stanje.getNazivStanja() + ")";
    }
}