package edu.unizg.foi.uzdiz.mbaranasi21.zadaca_1.model;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Predstavlja turistički aranžman sa svim podacima.
 */
public class Aranzman {
    
    private final String oznaka;
    private final String naziv;
    private final String program;
    private final LocalDate pocetniDatum;
    private final LocalDate zavrsniDatum;
    private final LocalTime vrijemeKretanja;
    private final LocalTime vrijemePovratka;
    private final double cijena;
    private final int minBrojPutnika;
    private final int maksBrojPutnika;
    private final int brojNocenja;
    private final Double doplataZaJednokrevetnuSobu;
    private final String prijevoz;
    private final int brojDorucka;
    private final int brojRuckova;
    private final int brojVecera;
    
    /**
     * Package-private konstruktor - poziva se samo iz AranzmanBuilder-a.
     *
     * @param oznaka Oznaka aranžmana
     * @param naziv Naziv aranžmana
     * @param program Program aranžmana
     * @param pocetniDatum Početni datum
     * @param zavrsniDatum Završni datum
     * @param vrijemeKretanja Vrijeme kretanja
     * @param vrijemePovratka Vrijeme povratka
     * @param cijena Cijena
     * @param minBrojPutnika Min broj putnika
     * @param maksBrojPutnika Max broj putnika
     * @param brojNocenja Broj noćenja
     * @param doplataZaJednokrevetnuSobu Doplata za jednokrevetnu sobu
     * @param prijevoz Vrsta prijevoza
     * @param brojDorucka Broj doručka
     * @param brojRuckova Broj ručkova
     * @param brojVecera Broj večera
     */
    Aranzman(String oznaka, String naziv, String program,
            LocalDate pocetniDatum, LocalDate zavrsniDatum,
            LocalTime vrijemeKretanja, LocalTime vrijemePovratka,
            double cijena, int minBrojPutnika, int maksBrojPutnika,
            int brojNocenja, Double doplataZaJednokrevetnuSobu,
            String prijevoz, int brojDorucka, int brojRuckova, 
            int brojVecera) {
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
    
    public String getOznaka() {
        return oznaka;
    }
    
    public String getNaziv() {
        return naziv;
    }
    
    public String getProgram() {
        return program;
    }
    
    public LocalDate getPocetniDatum() {
        return pocetniDatum;
    }
    
    public LocalDate getZavrsniDatum() {
        return zavrsniDatum;
    }
    
    public LocalTime getVrijemeKretanja() {
        return vrijemeKretanja;
    }
    
    public LocalTime getVrijemePovratka() {
        return vrijemePovratka;
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
    
    public int getBrojNocenja() {
        return brojNocenja;
    }
    
    public Double getDoplataZaJednokrevetnuSobu() {
        return doplataZaJednokrevetnuSobu;
    }
    
    public String getPrijevoz() {
        return prijevoz;
    }
    
    public int getBrojDorucka() {
        return brojDorucka;
    }
    
    public int getBrojRuckova() {
        return brojRuckova;
    }
    
    public int getBrojVecera() {
        return brojVecera;
    }
    
    /**
     * Provjerava preklapa li se ovaj aranžman s drugim aranžmanom.
     *
     * @param drugi Drugi aranžman za provjeru
     * @return true ako se aranžmani preklapaju, inače false
     */
    public boolean preklapa(Aranzman drugi) {
        return !this.pocetniDatum.isAfter(drugi.zavrsniDatum) &&
               !this.zavrsniDatum.isBefore(drugi.pocetniDatum);
    }
    
    @Override
    public String toString() {
        return oznaka + " - " + naziv;
    }
}