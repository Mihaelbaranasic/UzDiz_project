package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.lib.dto;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Data Transfer Object za aranžman.
 * Jednostavni POJO bez poslovne logike - samo za prijenos podataka.
 */
public class AranzmanDTO {
    
    private String oznaka;
    private String naziv;
    private String program;
    private LocalDate pocetniDatum;
    private LocalDate zavrsniDatum;
    private LocalTime vrijemeKretanja;
    private LocalTime vrijemePovratka;
    private double cijena;
    private int minBrojPutnika;
    private int maksBrojPutnika;
    private int brojNocenja;
    private Double doplataZaJednokrevetnuSobu;
    private String prijevoz;
    private int brojDorucka;
    private int brojRuckova;
    private int brojVecera;
    
    public AranzmanDTO() {}
    
    public AranzmanDTO(String oznaka, String naziv, String program,
                      LocalDate pocetniDatum, LocalDate zavrsniDatum,
                      LocalTime vrijemeKretanja, LocalTime vrijemePovratka,
                      double cijena, int minBrojPutnika, int maksBrojPutnika,
                      int brojNocenja, Double doplataZaJednokrevetnuSobu,
                      String prijevoz, int brojDorucka, int brojRuckova, int brojVecera) {
        this.oznaka = oznaka;
        this.naziv = naziv;
        this.program = program;
        this.pocetniDatum = pocetniDatum;
        this.zavrsniDatum = zavrsniDatum;
        this.vrijemeKretanja = vrijemeKretanja;
        this.vrijemePovratka = vrijemePovratka;
        this.cijena = cijena;
        this.minBrojPutnika = minBrojPutnika;
        this.maksBrojPutnika = maksBrojPutnika;
        this.brojNocenja = brojNocenja;
        this.doplataZaJednokrevetnuSobu = doplataZaJednokrevetnuSobu;
        this.prijevoz = prijevoz;
        this.brojDorucka = brojDorucka;
        this.brojRuckova = brojRuckova;
        this.brojVecera = brojVecera;
    }
    
    public String getOznaka() { return oznaka; }
    public void setOznaka(String oznaka) { this.oznaka = oznaka; }
    
    public String getNaziv() { return naziv; }
    public void setNaziv(String naziv) { this.naziv = naziv; }
    
    public String getProgram() { return program; }
    public void setProgram(String program) { this.program = program; }
    
    public LocalDate getPocetniDatum() { return pocetniDatum; }
    public void setPocetniDatum(LocalDate pocetniDatum) { this.pocetniDatum = pocetniDatum; }
    
    public LocalDate getZavrsniDatum() { return zavrsniDatum; }
    public void setZavrsniDatum(LocalDate zavrsniDatum) { this.zavrsniDatum = zavrsniDatum; }
    
    public LocalTime getVrijemeKretanja() { return vrijemeKretanja; }
    public void setVrijemeKretanja(LocalTime vrijemeKretanja) { this.vrijemeKretanja = vrijemeKretanja; }
    
    public LocalTime getVrijemePovratka() { return vrijemePovratka; }
    public void setVrijemePovratka(LocalTime vrijemePovratka) { this.vrijemePovratka = vrijemePovratka; }
    
    public double getCijena() { return cijena; }
    public void setCijena(double cijena) { this.cijena = cijena; }
    
    public int getMinBrojPutnika() { return minBrojPutnika; }
    public void setMinBrojPutnika(int minBrojPutnika) { this.minBrojPutnika = minBrojPutnika; }
    
    public int getMaksBrojPutnika() { return maksBrojPutnika; }
    public void setMaksBrojPutnika(int maksBrojPutnika) { this.maksBrojPutnika = maksBrojPutnika; }
    
    public int getBrojNocenja() { return brojNocenja; }
    public void setBrojNocenja(int brojNocenja) { this.brojNocenja = brojNocenja; }
    
    public Double getDoplataZaJednokrevetnuSobu() { return doplataZaJednokrevetnuSobu; }
    public void setDoplataZaJednokrevetnuSobu(Double doplataZaJednokrevetnuSobu) { 
        this.doplataZaJednokrevetnuSobu = doplataZaJednokrevetnuSobu; 
    }
    
    public String getPrijevoz() { return prijevoz; }
    public void setPrijevoz(String prijevoz) { this.prijevoz = prijevoz; }
    
    public int getBrojDorucka() { return brojDorucka; }
    public void setBrojDorucka(int brojDorucka) { this.brojDorucka = brojDorucka; }
    
    public int getBrojRuckova() { return brojRuckova; }
    public void setBrojRuckova(int brojRuckova) { this.brojRuckova = brojRuckova; }
    
    public int getBrojVecera() { return brojVecera; }
    public void setBrojVecera(int brojVecera) { this.brojVecera = brojVecera; }
}