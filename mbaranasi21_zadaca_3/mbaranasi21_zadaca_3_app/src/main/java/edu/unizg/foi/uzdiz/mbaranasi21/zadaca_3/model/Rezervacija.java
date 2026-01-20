package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.model;

import java.time.LocalDateTime;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.stanje.rezervacija.AktivnaRezervacija;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.stanje.rezervacija.NaCekanjuRezervacija;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.stanje.rezervacija.NovaRezervacija;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.stanje.rezervacija.OdgodenaRezervacija;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.stanje.rezervacija.OtkazanaRezervacija;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.stanje.rezervacija.PrimljenaRezervacija;
import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_3.stanje.rezervacija.RezervacijaStanje;

public class Rezervacija {
    
    private Osoba osoba;
    private String oznakaAranzmana;
    private LocalDateTime datumVrijemePrijema;
    private RezervacijaStanje stanje;
    private LocalDateTime datumVrijemeOtkaza;
    private boolean obradjenaGreska = false;
    
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
    
    public StanjeRezervacije getStanje() {
        String naziv = stanje.getNazivStanja();
        return StanjeRezervacije.valueOf(naziv);
    }
    
    public void setStanje(RezervacijaStanje novoStanje) {
        this.stanje = novoStanje;
    }
    
    public void setDatumOtkaza(LocalDateTime datumOtkaza) {
        this.datumVrijemeOtkaza = datumOtkaza;
    }
    
    public boolean isObradjenaGreska() {
        return obradjenaGreska;
    }
    
    public void setObradjenaGreska(boolean obradjenaGreska) {
        this.obradjenaGreska = obradjenaGreska;
    }
    
    public void nova() {
        stanje.nova(this);
    }
    
    public void primljena() {
        stanje.primljena(this);
    }
    
    public void aktiviraj() {
        stanje.aktiviraj(this);
    }
    
    public void staviNaCekanje() {
        stanje.staviNaCekanje(this);
    }
    
    public void odgodi() {
        stanje.odgodi(this);
    }
    
    public void otkazi(LocalDateTime datumVrijeme) {
        stanje.otkazi(this, datumVrijeme);
    }
    
    public boolean jeNova() {
        return stanje.getNazivStanja().equals("NOVA");
    }
    
    public boolean jePrimljena() {
        return stanje.getNazivStanja().equals("PRIMLJENA");
    }
    
    public boolean jeAktivna() {
        return stanje.getNazivStanja().equals("AKTIVNA");
    }
    
    public boolean jeNaCekanju() {
        return stanje.getNazivStanja().equals("NA_CEKANJU");
    }
    
    public boolean jeOdgodena() {
        return stanje.getNazivStanja().equals("ODGOĐENA");
    }
    
    public boolean jeOtkazana() {
        return stanje.getNazivStanja().equals("OTKAZANA");
    }
    
    @Override
    public String toString() {
        return osoba.getIme() + " " + osoba.getPrezime() + 
               " - " + oznakaAranzmana + " (" + stanje.getNazivStanja() + ")";
    }
    
    public Rezervacija kreirajKopiju() {
        Rezervacija kopija = new Rezervacija(
            new Osoba(this.osoba.getIme(), this.osoba.getPrezime()),
            this.oznakaAranzmana,
            this.datumVrijemePrijema,
            this.getStanje()
        );
        
        if (this.datumVrijemeOtkaza != null) {
            kopija.setDatumOtkaza(this.datumVrijemeOtkaza);
        }
        
        kopija.setObradjenaGreska(this.obradjenaGreska);
        
        return kopija;
    }
}