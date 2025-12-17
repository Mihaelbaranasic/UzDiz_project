package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.builder;

import java.time.LocalDate;
import java.time.LocalTime;

import edu.unizg.foi.uzdiz.mbaranasi21.zadaca_2.model.Aranzman;

/**
 * Konkretni builder za kreiranje aranžmana (ConcreteBuilder).
 * Implementira Builder sučelje i konstruira Aranzman objekt s opcionalnim atributima.
 */
public class AranzmanBuilder implements Builder {
    
    private final String oznaka;
    private final String naziv;
    private final LocalDate pocetniDatum;
    private final LocalDate zavrsniDatum;
    private final double cijena;
    private final int minBrojPutnika;
    private final int maksBrojPutnika;
    
    private String program = "";
    private LocalTime vrijemeKretanja = null;
    private LocalTime vrijemePovratka = null;
    private int brojNocenja = 0;
    private Double doplataZaJednokrevetnuSobu = null;
    private String prijevoz = "";
    private int brojDorucka = 0;
    private int brojRuckova = 0;
    private int brojVecera = 0;
    
    /**
     * Konstruktor za kreiranje buildera s obaveznim parametrima.
     *
     * @param oznaka Jedinstvena oznaka aranžmana
     * @param naziv Naziv aranžmana
     * @param pocetniDatum Početni datum
     * @param zavrsniDatum Završni datum
     * @param cijena Cijena u eurima
     * @param minBrojPutnika Minimalni broj putnika
     * @param maksBrojPutnika Maksimalni broj putnika
     */
    public AranzmanBuilder(String oznaka, String naziv, LocalDate pocetniDatum,
                          LocalDate zavrsniDatum, double cijena,
                          int minBrojPutnika, int maksBrojPutnika) {
        this.oznaka = oznaka;
        this.naziv = naziv;
        this.pocetniDatum = pocetniDatum;
        this.zavrsniDatum = zavrsniDatum;
        this.cijena = cijena;
        this.minBrojPutnika = minBrojPutnika;
        this.maksBrojPutnika = maksBrojPutnika;
    }
    
    @Override
    public Builder program(String program) {
        this.program = program;
        return this;
    }
    
    @Override
    public Builder vrijemeKretanja(LocalTime vrijeme) {
        this.vrijemeKretanja = vrijeme;
        return this;
    }
    
    @Override
    public Builder vrijemePovratka(LocalTime vrijeme) {
        this.vrijemePovratka = vrijeme;
        return this;
    }
    
    @Override
    public Builder brojNocenja(int broj) {
        this.brojNocenja = broj;
        return this;
    }
    
    @Override
    public Builder doplataZaJednokrevetnuSobu(Double doplata) {
        this.doplataZaJednokrevetnuSobu = doplata;
        return this;
    }
    
    @Override
    public Builder prijevoz(String prijevoz) {
        this.prijevoz = prijevoz;
        return this;
    }
    
    @Override
    public Builder brojDorucka(int broj) {
        this.brojDorucka = broj;
        return this;
    }
    
    @Override
    public Builder brojRuckova(int broj) {
        this.brojRuckova = broj;
        return this;
    }
    
    @Override
    public Builder brojVecera(int broj) {
        this.brojVecera = broj;
        return this;
    }
    
    public String getOznaka() {
        return oznaka;
    }

    public String getNaziv() {
        return naziv;
    }

    public LocalDate getPocetniDatum() {
        return pocetniDatum;
    }

    public LocalDate getZavrsniDatum() {
        return zavrsniDatum;
    }

    public double getCijena() {
        return cijena;
    }

    public int getMinBrojPutnika() {
        return minBrojPutnika;
    }

    public int getMaksBrojPutnika() {
        return maksBrojPutnika;
    }
    
    @Override
    public Aranzman build() {
        return new Aranzman(oznaka, naziv, program, pocetniDatum, zavrsniDatum,
            vrijemeKretanja, vrijemePovratka, cijena, minBrojPutnika,
            maksBrojPutnika, brojNocenja, doplataZaJednokrevetnuSobu,
            prijevoz, brojDorucka, brojRuckova, brojVecera);
    }
}